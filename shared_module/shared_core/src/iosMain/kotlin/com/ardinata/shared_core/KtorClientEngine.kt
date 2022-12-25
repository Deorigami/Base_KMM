package com.ardinata.shared_core

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual class KtorClientEngine {
    actual fun getClientEngine(config: HttpClientConfig<*>.() -> Unit) : HttpClient = HttpClient(Darwin){
        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }
    actual companion object Factory {
        actual fun build() : KtorClientEngine = KtorClientEngine()
    }
}