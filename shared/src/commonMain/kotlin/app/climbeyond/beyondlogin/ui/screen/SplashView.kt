package app.climbeyond.beyondlogin.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.climbeyond.beyondlogin.BeyondLogin
import app.climbeyond.beyondlogin.Session
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.helpers.Colors
import app.climbeyond.beyondlogin.ui.ControllerView
import app.climbeyond.beyondlogin.ui.component.Elements
import climbeyond.beyondlogin.generated.resources.Res
import climbeyond.beyondlogin.generated.resources.beyond_login_logo
import climbeyond.beyondlogin.generated.resources.beyond_login_splash_502
import climbeyond.beyondlogin.generated.resources.beyond_login_splash_503
import climbeyond.beyondlogin.generated.resources.beyond_login_splash_logo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource


class SplashView(private val self: BeyondLogin) : ControllerView.RequireView {
    private val errorMessage: MutableState<String?> = mutableStateOf(null)

    private fun initialize(coroutine: CoroutineScope) {
        // Create /sessions/whoami API call to check if there is valid session
        // Load new view on response or show error in loading screen
        Session.checkActive(self.platform) { token, orySession, code ->
            token?.let {
                // Token is good
                coroutine.launch {
                    self.viewService.setSession(orySession)
                    self.viewService.listener.loginActive(token)
                    SessionView.init(self)
                }

            } ?: run {
                coroutine.launch(Dispatchers.Default) {
                    if (!handleResponseCode(code)) {
                        LoginView.init(self)
                    }
                }
            }
        }
    }

    private suspend fun handleResponseCode(code: Int): Boolean {
        return when (code) {
            // whoami call succeed but there is no active session
            200 -> {
                false
            }
            // Token not valid or expired
            401 -> {
                false
            }
            502 -> {
                // Bad Gateway
                errorMessage.value = getString(Res.string.beyond_login_splash_502)
                true
            }
            503 -> {
                // Service Unavailable
                errorMessage.value = getString(Res.string.beyond_login_splash_503)
                true
            }
            else -> {
                // No handler for this code, thus log for later implementation and continue
                BLLogger.logError("SplashView.handleResponseCode: handler not defined [code: $code]")
                false
            }
        }
    }

    @Composable
    override fun View() {
        val coroutine = rememberCoroutineScope()

        Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
        ) {
            Icon(vectorResource(Res.drawable.beyond_login_logo),
                    stringResource(Res.string.beyond_login_splash_logo),
                    tint = Colors.drawable_tint_white)

            errorMessage.value?.let { message ->
                Elements.Banner(message,
                        Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp))
            }
        }

        LaunchedEffect(Unit) {
            initialize(coroutine)
        }
    }
}