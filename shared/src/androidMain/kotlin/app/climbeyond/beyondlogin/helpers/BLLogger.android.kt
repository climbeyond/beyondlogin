package app.climbeyond.beyondlogin.helpers

actual fun getThread(): String {
    return Thread.currentThread().name
}