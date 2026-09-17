package app.climbeyond.beyondlogin

import androidx.compose.runtime.mutableStateOf
import app.climbeyond.beyondlogin.ui.ControllerView
import org.openapitools.client.apis.FrontendApi


class ViewService(val settings: Settings.Data, val listener: Listener) : SettingsListener {

    interface Listener {
        fun loginActive(token: String)
        fun loginSuccess(data: SessionInfo, appSuccess: (success: String) -> Unit,
                appFailure: (message: String) -> Unit)
        fun registerSuccess(data: SessionInfo, appSuccess: (failure: String) -> Unit,
                appFailure: (message: String) -> Unit)
        fun registerError()
        fun loggedClose(message: String)
        fun logOut(success: Boolean)
        fun unknownException(message: String)
        fun closeBeyondLogin()

        /**
         * Only called on platforms where BeyondLogin does not perform native Google Sign-In
         * itself (currently iOS). Implement using your own Google Sign-In integration: generate
         * a nonce, perform the sign-in, then invoke [callback] with the resulting ID token and
         * that same nonce. Invoke [callback] with (null, null) if the user cancels or it fails.
         * The default implementation does this, meaning Google Sign-In will fail on platforms
         * that don't override it.
         */
        fun requestGoogleIdToken(callback: (idToken: String?, nonce: String?) -> Unit) {
            callback(null, null)
        }
    }

    val currentView = mutableStateOf(ControllerView.Screen.SPLASH)

    internal var oryFlowId: String = "-"
    internal var orySession: org.openapitools.client.models.Session? = null

    private var cachedOryApi: FrontendApi? = null

    override fun onSettingsChange() {

    }

    fun getOryApi(): FrontendApi {
        return cachedOryApi ?: run {
            val api = BeyondLogin.getOryApi(settings.kratosUrl, settings.logLevel)
            cachedOryApi = api
            api
        }
    }

    fun setSession(session: org.openapitools.client.models.Session?) {
        orySession = session
        currentView.value = ControllerView.Screen.SESSION
    }
}
