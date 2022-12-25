package com.ardinata.shared_core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform