package com.eyedea.animax

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform