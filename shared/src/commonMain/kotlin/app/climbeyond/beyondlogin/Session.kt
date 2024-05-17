package app.climbeyond.beyondlogin

import app.climbeyond.beyondlogin.helpers.BEYOND_LOGIN_SESSION_EXPIRE
import app.climbeyond.beyondlogin.helpers.BEYOND_LOGIN_SESSION_ID
import app.climbeyond.beyondlogin.helpers.BEYOND_LOGIN_SESSION_IDENTITY_ID
import app.climbeyond.beyondlogin.helpers.BEYOND_LOGIN_SESSION_TOKEN
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.helpers.SharedPreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import sh.ory.model.PerformNativeLogoutBody
import sh.ory.model.Session


data class SessionInfo(
        val id: String,
        val token: String,
        val expire: Long
) {
    fun hasNotExpired(): Boolean {
        return expire > Clock.System.now().epochSeconds
    }
}

internal object Session {
    fun checkActive(platform: BeyondLoginPlatform,
            callback: (token: String?, session: Session?, code: Int) -> Unit) {

        CoroutineScope(Dispatchers.Default).launch {
            SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_TOKEN, null)?.let { sessionToken ->

                // Callback here will trigger only for onUploadProgress
                try {
                    val settings = Settings.load(platform)
                    val response = BeyondLogin.getOryApi(settings.kratosUrl, settings.logLevel)
                        .toSession(sessionToken, null)
                    val code = response.status

                    if (response.success) {
                        val session = response.body()

                        storeSave(platform, sessionToken, session)

                        BLLogger.logDebug("Session.checkActive [Code: ${code}] [Id: ${session.id}]")
                        callback(sessionToken, session, code)

                    } else {
                        handleCheckActiveFailed(platform, code)
                        callback(null, null, code)
                    }

                } catch (ex: Exception) {
                    BLLogger.logError(ex.stackTraceToString())
                }

            } ?: run {
                BLLogger.logDebug("Session.checkActive no stored token found")
                callback(null, null, 200)
            }
        }
    }

    fun delete(platform: BeyondLoginPlatform, sessionId: String, callback: (success: Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            val token = SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_TOKEN, null)

            if (token != null) {
                val settings = Settings.load(platform)
                val response = BeyondLogin.getOryApi(settings.kratosUrl, settings.logLevel)
                    .disableMySession(sessionId, token, null)
                val code = response.status

                if (response.success) {
                    deleteSharedPreference(platform)

                    BLLogger.logInfo("Session.delete removed session $sessionId")
                    callback(true)

                } else {
                    BLLogger.logError("Session.delete error removing session [Session" +
                            " $sessionId] [Code ${code}] [Message ${response.response}]")
                    callback(false)
                }

            } else {
                BLLogger.logError("Session.delete no token found to delete given session")
                callback(false)
            }
        }
    }

    fun logout(platform: BeyondLoginPlatform, callback: (success: Boolean) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            val id = SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_ID, null)
            val token = SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_TOKEN, null)

            if (id != null && token != null) {
                val settings = Settings.load(platform)
                val payload = PerformNativeLogoutBody(token)
                val response = BeyondLogin.getOryApi(settings.kratosUrl, settings.logLevel)
                    .performNativeLogout(payload)
                val code = response.status

                if (response.success) {
                    deleteSharedPreference(platform)

                    BLLogger.logInfo("Session.delete removed session $id")
                    callback(true)

                } else {
                    BLLogger.logError("Session.delete error removing session [Session" +
                            " $id] [Code ${code}] [Message ${response.response}]")
                    callback(false)
                }

            } else {
                if (id == null && token == null) {
                    BLLogger.logError("Session.delete no session found")
                    callback(false)

                } else {
                    deleteSharedPreference(platform)

                    BLLogger.logError(
                            "Session.delete Session id or token not found [Session $id] [Token ${token}]")
                    callback(true)
                }
            }
        }
    }

    fun refresh(platform: BeyondLoginPlatform, callback: (token: String?) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_TOKEN, null)?.let { sessionToken ->
                val settings = Settings.load(platform)
                val response = BeyondLogin.getOryApi(settings.kratosUrl, settings.logLevel)
                    .createNativeLoginFlow(true, "aal1", sessionToken)
                val code = response.status

                if (response.success) {
                    storeRefresh(platform, response.body().expiresAt)
                    callback(sessionToken)

                } else {
                    BLLogger.logWarning("onFailure: $code - ${response.headers}")
                    callback(null)
                }

            } ?: run {
                callback(null)
            }
        }
    }

    fun storeHasNotExpired(platform: BeyondLoginPlatform): Boolean {
        val epochMillis =
            SharedPreferenceManager.readLong(platform, BEYOND_LOGIN_SESSION_EXPIRE, -1)

        return (epochMillis > Clock.System.now().epochSeconds)
    }

    fun storeGetSession(platform: BeyondLoginPlatform): SessionInfo? {
        val epochMillis = SharedPreferenceManager.readLong(platform, BEYOND_LOGIN_SESSION_EXPIRE, -1)
        val id = SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_ID, null)
        val token = SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_TOKEN, null)

        if (id != null && token != null) {
            return SessionInfo(id, token, epochMillis)
        }

        return null
    }

    fun storeGetIdentityId(platform: BeyondLoginPlatform): String? {
        return SharedPreferenceManager.read(platform, BEYOND_LOGIN_SESSION_IDENTITY_ID, null)
    }

    internal fun storeSave(platform: BeyondLoginPlatform, token: String, session: Session) {
        SharedPreferenceManager.write(platform, BEYOND_LOGIN_SESSION_ID, session.id)
        SharedPreferenceManager.write(platform, BEYOND_LOGIN_SESSION_TOKEN, token)
        SharedPreferenceManager.write(platform, BEYOND_LOGIN_SESSION_IDENTITY_ID, session.identity.id)
        session.expiresAt?.let {
            SharedPreferenceManager.writeLong(platform, BEYOND_LOGIN_SESSION_EXPIRE,
                    it.toEpochMilliseconds())
        }
    }

    private fun storeRefresh(platform: BeyondLoginPlatform, expires: Instant) {
        SharedPreferenceManager.writeLong(platform, BEYOND_LOGIN_SESSION_EXPIRE,
                expires.toEpochMilliseconds())
    }

    private fun handleCheckActiveFailed(platform: BeyondLoginPlatform, code: Int) {
        when (code) {
            // Service unavailable - do not remove session data because this can be temporary
            503 -> {
                BLLogger.logWarning("Session.checkActive [Code: ${code}] [Message: Service Unavailable]")
            }
            401 -> {
                BLLogger.logWarning("Session.checkActive [Code: ${code}] [Message: Authorization token not valid or expired]")
                deleteSharedPreference(platform)
            }
            else -> {
                BLLogger.logError("Session.checkActive [Code: ${code}] [Message: UNKNOWN ERROR]")
                deleteSharedPreference(platform)
            }
        }
    }

    private fun deleteSharedPreference(platform: BeyondLoginPlatform) {
        SharedPreferenceManager.delete(platform, BEYOND_LOGIN_SESSION_ID)
        SharedPreferenceManager.delete(platform, BEYOND_LOGIN_SESSION_TOKEN)
        SharedPreferenceManager.delete(platform, BEYOND_LOGIN_SESSION_EXPIRE)
        SharedPreferenceManager.delete(platform, BEYOND_LOGIN_SESSION_IDENTITY_ID)
    }
}