package app.climbeyond.beyondlogin

import android.content.Context


actual data class BeyondLoginPlatform(val context: Context)

actual fun beyondLoginBuildTime(): String {
    return BuildConfig.BUILD_TIME
}
