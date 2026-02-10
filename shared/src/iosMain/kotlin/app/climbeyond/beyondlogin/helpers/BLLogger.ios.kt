package app.climbeyond.beyondlogin.helpers

import platform.Foundation.NSThread

actual fun getThread(): String {
    if (NSThread.currentThread.isMainThread) {
        return "main"
    }

    return NSThread.currentThread().name?.takeIf { it.isNotEmpty() }
        ?: NSThread.currentThread().description?.slice(11 until 23)
        ?: "[unknown]"
}