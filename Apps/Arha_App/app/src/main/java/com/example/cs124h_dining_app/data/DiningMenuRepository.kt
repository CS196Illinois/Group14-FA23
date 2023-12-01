package com.example.cs124h_dining_app.data

import com.example.cs124h_dining_app.data.source.DiningMenuDataSource
import com.example.cs124h_dining_app.models.MenuItem
import com.example.cs124h_dining_app.models.MenuRequest
import com.example.cs124h_dining_app.models.SourceMenuItem
import com.example.cs124h_dining_app.models.UserStats
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileWriter
import android.content.Context


object DiningMenuRepository {

    private val source = DiningMenuDataSource
    private var cachedMenuData = mutableMapOf<MenuRequest, Map<String, Map<String, List<MenuItem>>>>() // [MenuRequest][Dining Period][Cafe] -> ListOfMenuItems
    // If the current request has not been processed, this function tells the data source to fetch the data and parses it
    private suspend fun refreshData(req: MenuRequest) {
        var items: List<SourceMenuItem>
        withContext(Dispatchers.IO) {
            items = source.fetchMenu(req)
        }
        var mapByMeal = mutableMapOf<String, MutableMap<String, MutableList<MenuItem>>>()
        for (item in items)
            mapByMeal.getOrPut(item.meal, { mutableMapOf() }).getOrPut(item.cafe, { mutableListOf() }).add(
                MenuItem(item.name, item.course, item.traits ?: "")
            )
        cachedMenuData[req] =  mapByMeal.entries.associate { (k, v) -> Pair(k, v.entries.associate { (k, v) -> Pair(k, v.toList()) }) }
    }

    // Asynchronously executes the callback for the menu request with the returned info
    @JvmStatic
    fun getData(req: MenuRequest, callback: (Map<String, Map<String, List<MenuItem>>>) -> Any?) {
        CoroutineScope(Dispatchers.Default).launch {
            if (!(cachedMenuData.containsKey(req))) {
                refreshData(req)
            }
            check(cachedMenuData[req] != null)
            callback.invoke(cachedMenuData[req]!!)
        }
    }

    @JvmStatic
    fun submitStats(data: UserStats, context: Context) {
        try {
            val json = Json.encodeToString(data)
            println(json)
            val file: File = File(context.filesDir, "JSONData")
            val fileWriter = FileWriter(file)
            fileWriter.write(json)
            fileWriter.flush()
            fileWriter.close()

        } catch (e: IOException) {
            println(e)
        }
    }

}