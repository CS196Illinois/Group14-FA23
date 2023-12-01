package com.example.cs124h_dining_app.data.source

import com.example.cs124h_dining_app.models.MenuRequest
import com.example.cs124h_dining_app.models.SourceMenuItem
import kotlinx.coroutines.sync.Mutex
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import kotlinx.serialization.json.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.sync.withLock

object DiningMenuDataSource {
    private val accessMutex = Mutex()
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                connectTimeout = 60_000
                socketTimeout = 60_000
            }
        }
        /*
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("Logger KTOR => $message")
                }
            }
            level = LogLevel.ALL
        }
        */
    }

    suspend fun fetchMenu(req: MenuRequest): List<SourceMenuItem> = accessMutex.withLock {
        val response: HttpResponse = this.client.post("https://web.housing.illinois.edu/DiningMenus/api/DiningMenu/GetOption/") {
            contentType(ContentType.Application.Json)
            setBody(req)
        }

        // for some dumb reason the response tries to escape all special characters which messes with the parsing
        var query: String = response.bodyAsText()
        query = query.replace("\\\\", "\\") // fix backslash doubling
        query = query.replace("\\\"", "\"") // remove extra backslash from quotes

        return Json.decodeFromString<List<SourceMenuItem>>(query.substring(1, query.length - 1)) // get rid of quotes at beginning and end
    }
}