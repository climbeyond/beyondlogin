package app.climbeyond.beyondlogin.helpers

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


object ToastBar {
    private val showSnackBar = MutableSharedFlow<CheeseToast?>(
        replay = 0,
        extraBufferCapacity = 2,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    val event : SharedFlow<CheeseToast?> = showSnackBar.asSharedFlow()

    fun showMessage(message: String, success: Boolean = false) {
        showSnackBar.tryEmit(CheeseToast(message = message, success = success))
    }

    data class CheeseToast(
        val message: String,
        val success: Boolean,
    )
}