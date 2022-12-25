package com.ardinata.shared_core

import io.ktor.client.*

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class KtorClientEngine {
    fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient
    companion object Factory {
        fun build() : KtorClientEngine
    }
}