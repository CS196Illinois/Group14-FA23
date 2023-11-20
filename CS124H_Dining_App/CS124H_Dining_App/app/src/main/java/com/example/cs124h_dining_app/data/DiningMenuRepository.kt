package com.example.cs124h_dining_app.data

import com.example.cs124h_dining_app.data.source.DiningMenuDataSource
import com.example.cs124h_dining_app.models.MenuItem
import com.example.cs124h_dining_app.models.MenuRequest
import com.example.cs124h_dining_app.models.SourceMenuItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DiningMenuRepository(private val source: DiningMenuDataSource) {

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

    // Checks if we have cached results of the request and if so returns it, otherwise updates the cache and returns the result
    fun getData(req: MenuRequest): Map<String, Map<String, List<MenuItem>>> {
        return if (cachedMenuData.containsKey(req))
            cachedMenuData[req]!!
        else {
            runBlocking {
                withContext(Dispatchers.IO) {
                    refreshData(req)
                }
            }
            cachedMenuData[req]!!
        }
    }

}