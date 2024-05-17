package app.climbeyond.beyondlogin.helpers

import android.app.Activity
import android.widget.Toast
import app.climbeyond.beyondlogin.BeyondLogin


actual class ToastBarWrap(val data: Any, val ctx: Activity)

actual suspend fun showToastBar(show: ToastBarWrap) {
    when (show.data) {
        is ToastBar.CheeseToast -> {
            val length = if (show.data.lengthLong) {
                Toast.LENGTH_LONG
            } else {
                Toast.LENGTH_SHORT
            }

            if (show.data.message != null) {
                Toast.makeText(show.ctx, show.data.message, length).show()
            } else {
                show.data.messageId?.let {
                    Toast.makeText(show.ctx, show.ctx.getString(it), length).show()
                } ?: BLLogger.logError("ToastBar.android.showToastBar no data given")
            }
        }

        is String -> {
            Toast.makeText(show.ctx, show.data, Toast.LENGTH_SHORT).show()
        }

        is Int -> {
            // If Integer given then we trust that it's reference for R.string.* items
            Toast.makeText(show.ctx, show.ctx.getString(show.data), Toast.LENGTH_SHORT).show()
        }

        else -> {
            BLLogger.logError("ToastBar message type invalid: ${show.data.javaClass.name}")
        }
    }
}