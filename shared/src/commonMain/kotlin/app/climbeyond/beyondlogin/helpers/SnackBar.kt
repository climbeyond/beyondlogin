package app.climbeyond.beyondlogin.helpers

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

expect class ToastBarWrap
expect suspend fun showToastBar(show: ToastBarWrap)


object ToastBar {
    private val showSnackBar = MutableSharedFlow<CheeseToast?>(
            replay = 0,
            extraBufferCapacity = 2,
            onBufferOverflow = BufferOverflow.SUSPEND)

    val event : SharedFlow<CheeseToast?> = showSnackBar.asSharedFlow()

    suspend fun show(data: CheeseToast, delay: Long) {
        delay(delay)
        showSnackBar.tryEmit(data)
    }

    fun showMessage(message: String, lengthLong: Boolean = false) {
        showSnackBar.tryEmit(CheeseToast(message = message, lengthLong = lengthLong))
    }

    fun showMessage(messageId: Int, lengthLong: Boolean = false) {
        showSnackBar.tryEmit(CheeseToast(messageId = messageId, lengthLong = lengthLong))
    }

    data class CheeseToast(
            var message: String? = null,
            var messageId: Int? = null,
            val lengthLong: Boolean = false)
}