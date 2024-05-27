package app.climbeyond.beyondlogin.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import app.climbeyond.beyondlogin.BeyondLogin
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.ui.screen.LoginView
import app.climbeyond.beyondlogin.ui.screen.RecoveryView
import app.climbeyond.beyondlogin.ui.screen.SplashView
import app.climbeyond.beyondlogin.ui.screen.RegisterView
import app.climbeyond.beyondlogin.ui.screen.SessionView


data object ControllerView {
    internal interface RequireView {
        @Composable
        fun View()
    }

    enum class Screen {
        SPLASH,
        LOGIN,
        REGISTER,
        RECOVERY,
        SESSION
    }

    @Composable
    internal fun View(self: BeyondLogin) {
        val view = remember { self.viewService.currentView }

        BLLogger.logDebug("Init ControllerView [View: ${view.value.name}] [FlowId: ${self.viewService.oryFlowId}]")

        when (view.value) {
            Screen.SPLASH -> SplashView(self).View()
            Screen.LOGIN -> LoginView(self).View()
            Screen.REGISTER -> RegisterView(self).View()
            Screen.RECOVERY -> RecoveryView(self).View()
            Screen.SESSION -> SessionView(self).View()
        }
    }
}