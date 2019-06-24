package main

import kotlinx.coroutines.Dispatchers

actual fun getPlatformName() = "Android"

internal actual val ApplicationDispatcher = Dispatchers.Default