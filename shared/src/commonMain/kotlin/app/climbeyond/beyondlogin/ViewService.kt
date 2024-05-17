package app.climbeyond.beyondlogin

import androidx.compose.runtime.mutableStateOf
import app.climbeyond.beyondlogin.ui.ControllerView
import sh.ory.api.FrontendApi


class ViewService(val settings: Settings.Data, val listener: Listener) : SettingsListener {

    interface Listener {
        fun loginActive(token: String)
        fun loginSuccess(data: SessionInfo, appSuccess: (success: String) -> Unit,
                appFailure: (message: String) -> Unit)
        fun loginError()
        fun registerSuccess(data: SessionInfo, appSuccess: (failure: String) -> Unit,
                appFailure: (message: String) -> Unit)
        fun registerError()
        fun loggedClose(message: String)
        fun logOut(success: Boolean)
        fun unknownException(message: String)
    }

    interface ViewListener {
        fun close()
    }

    val currentView = mutableStateOf(ControllerView.Screen.SPLASH)
    var listenerView: ViewListener? = null

    internal var oryFlowId: String = "-"
    internal var orySession: sh.ory.model.Session? = null

    override fun onSettingsChange() {

    }

    fun getOryApi(): FrontendApi {
        return BeyondLogin.getOryApi(settings.kratosUrl, settings.logLevel)
    }

    fun setSession(session: sh.ory.model.Session?) {
        orySession = session
        currentView.value = ControllerView.Screen.SESSION
    }
}
