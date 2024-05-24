package app.climbeyond.beyondlogin

import androidx.compose.runtime.Composable
import app.climbeyond.beyondlogin.exception.BeyondException
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.ui.ControllerView
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sh.ory.api.FrontendApi

expect class BeyondLoginPlatform
expect fun beyondLoginBuildTime(): String

class BeyondLogin(internal val platform: BeyondLoginPlatform, viewListener: ViewService.Listener) {

    interface LogsListener {
        fun debug(tag: String, message: String)
        fun verbose(tag: String, message: String)
        fun info(tag: String, message: String)
        fun warning(tag: String, message: String)
        fun error(tag: String, message: String)
    }

    internal val settings = Settings.load(platform)
    internal var viewService: ViewService = ViewService(Settings.load(platform), viewListener)

    @Composable
    fun View() {
        ControllerView.View(this)
    }

    fun requestView(view: ControllerView.Screen) {
        viewService.currentView.value = view
    }

    /**
     * Delete session is one exists
     */
    fun sessionDelete(sessionId: String, callback: (success: Boolean) -> Unit) {
        BLLogger.logDebug("Call: BeyondLogin.sessionDelete")

        try {
            Settings.load(platform)
            Session.delete(platform, sessionId) { success ->
                CoroutineScope(Dispatchers.Main).launch {
                    callback(success)
                }
            }

        } catch (ex: BeyondException) {
            CoroutineScope(Dispatchers.Main).launch {
                callback(false)
            }
        }
    }

    /**
     * Logout current active session
     */
    fun sessionLogout(callback: (success: Boolean) -> Unit) {
        BLLogger.logDebug("Call: BeyondLogin.sessionLogout")

        try {
            Settings.load(platform)
            Session.logout(platform) { success ->
                CoroutineScope(Dispatchers.Main).launch {
                    callback(success)
                }
            }

        } catch (ex: BeyondException) {
            CoroutineScope(Dispatchers.Main).launch {
                callback(false)
            }
        }
    }

    companion object {
        /**
         * Check if there is stored session token and it's valid
         */
        fun sessionHasActive(platform: BeyondLoginPlatform, callback: (token: String?) -> Unit) {
            BLLogger.logDebug("Call: BeyondLogin.sessionHasActive")

            try {
                Settings.load(platform)
                Session.checkActive(platform) { token, _, _ ->
                    CoroutineScope(Dispatchers.Main).launch {
                        callback(token)
                    }
                }

            } catch (ex: BeyondException) {
                CoroutineScope(Dispatchers.Main).launch {
                    callback(null)
                }
            }
        }

        /**
         * Refresh current session if possible
         */
        fun sessionRefresh(platform: BeyondLoginPlatform, callback: (token: String?) -> Unit) {
            BLLogger.logDebug("Call: BeyondLogin.sessionRefresh")

            try {
                Settings.load(platform)
                Session.refresh(platform) { token ->
                    CoroutineScope(Dispatchers.Main).launch {
                        callback(token)
                    }
                }

            } catch (ex: BeyondException) {
                CoroutineScope(Dispatchers.Main).launch {
                    callback(null)
                }
            }
        }

        /**
         * Check if stored session exists and it has not expired
         */
        fun storeSessionHasNotExpired(platform: BeyondLoginPlatform): Boolean {
            BLLogger.logDebug("Call: BeyondLogin.storeSessionHasNotExpired")

            return Session.storeHasNotExpired(platform)
        }

        /**
         * Check if stored session exists and return id and token
         */
        fun storeGetSession(platform: BeyondLoginPlatform): SessionInfo? {
            BLLogger.logDebug("Call: BeyondLogin.storeGetSession")

            return Session.storeGetSession(platform)
        }

        /**
         * Get stored identity id
         */
        fun storeGetIdentityId(platform: BeyondLoginPlatform): String? {
            BLLogger.logDebug("Call: BeyondLogin.storeGetIdentityId")

            return Session.storeGetIdentityId(platform)
        }

        fun getOryApi(kratosUrl: String,
                logLevel: Settings.ApiLogLevel = Settings.ApiLogLevel.INFO): FrontendApi {

            return FrontendApi(kratosUrl, httpClientConfig = {
                val doLogLevel = when (logLevel) {
                    Settings.ApiLogLevel.INFO -> LogLevel.INFO
                    Settings.ApiLogLevel.BODY -> LogLevel.BODY
                    Settings.ApiLogLevel.HEADERS -> LogLevel.HEADERS
                    Settings.ApiLogLevel.ALL -> LogLevel.ALL
                }

                it.install(Logging) {
                    logger = object: Logger {
                        override fun log(message: String) {
                            if (doLogLevel == LogLevel.INFO) {
                                BLLogger.logInfo(message.lines().joinToString(" -- "))
                            } else {
                                BLLogger.logInfo(message)
                            }
                        }
                    }
                    // Set logging level by init defined value
                    level = when(doLogLevel) {
                        LogLevel.ALL -> LogLevel.ALL
                        LogLevel.BODY -> LogLevel.BODY
                        else -> LogLevel.INFO
                    }
                }
            })
        }
    }
}
