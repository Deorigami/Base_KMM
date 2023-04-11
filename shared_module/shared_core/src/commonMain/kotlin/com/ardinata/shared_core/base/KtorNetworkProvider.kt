package com.ardinata.shared_core.base

import com.ardinata.shared_core.KtorClientEngine
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorNetworkProvider {
    val client = KtorClientEngine.build().getClientEngine {
        defaultRequest {
            url("https://jsonplaceholder.typicode.com/")
            headers {

            }
        }
        install(HttpTimeout){
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }
        install(ResponseObserver){
            onResponse {

            }
        }
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
}

suspend inline fun <reified T> clientGet(
    url: String,
    requestBuilder: (HttpRequestBuilder) -> Unit = {}
) : BaseRespondDto<T> = KtorNetworkProvider.client.get{
    url(url)
    requestBuilder.invoke(this)
}.body()