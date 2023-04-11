package com.eyedea.shared_sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform