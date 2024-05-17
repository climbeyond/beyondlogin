package app.climbeyond.beyondlogin.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.climbeyond.beyondlogin.BeyondLogin
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.helpers.Colors
import app.climbeyond.beyondlogin.helpers.ToastBar
import app.climbeyond.beyondlogin.ui.ControllerView
import app.climbeyond.beyondlogin.ui.component.Elements
import beyondlogin.shared.generated.resources.Res
import beyondlogin.shared.generated.resources.beyond_login_arrow_back
import beyondlogin.shared.generated.resources.beyond_login_logout
import beyondlogin.shared.generated.resources.beyond_login_session_account_id
import beyondlogin.shared.generated.resources.beyond_login_session_email
import beyondlogin.shared.generated.resources.beyond_login_session_header
import beyondlogin.shared.generated.resources.beyond_login_session_logout
import beyondlogin.shared.generated.resources.beyond_login_session_session_expire
import kotlinx.serialization.json.jsonPrimitive
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

class SessionView(private val self: BeyondLogin) : ControllerView.RequireView {
    private val accountId = mutableStateOf("")
    private val sessionExpire = mutableStateOf("")
    private val accountEmail = mutableStateOf("")

    init {
        self.viewService.orySession?.let { session ->
            BLLogger.logDebug(
                    "SessionView.init [${session.id}] [Issued: ${session.issuedAt}] [Expires: ${session.expiresAt}]")

            accountId.value = session.id
            sessionExpire.value = session.expiresAt.toString()
            accountEmail.value =
                session.identity.traits?.get("email")?.jsonPrimitive?.content ?: ""

        } ?: run {
            BLLogger.logError("SessionView - session not found")
        }
    }

    @Composable
    override fun View() {
        Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
        ) {
            Icon(vectorResource(Res.drawable.beyond_login_arrow_back),
                    stringResource(Res.string.beyond_login_session_email),
                    Modifier
                        .padding(top = 30.dp, start = 30.dp)
                        .clickable {
                            self.viewService.let {
                                it.listenerView?.close()
                            }
                        },
                    tint = Colors.drawable_tint_white)

            Text(stringResource(Res.string.beyond_login_session_header).uppercase(),
                    Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 50.dp),
                    color = Colors.text_white,
                    fontWeight = FontWeight.Light,
                    fontSize = 22.sp)

            Divider(
                    color = Colors.divider,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 40.dp)
                        .width(50.dp)
                        .height(3.dp)
            )

            Text(stringResource(Res.string.beyond_login_session_account_id),
                    Modifier
                        .align(alignment = Alignment.Start)
                        .padding(top = 50.dp, start = 30.dp),
                    color = Colors.text_white,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp)

            Text(accountId.value,
                    Modifier
                        .align(alignment = Alignment.Start)
                        .padding(top = 10.dp, start = 30.dp),
                    color = Colors.text_white,
                    fontSize = 16.sp)

            Text(stringResource(Res.string.beyond_login_session_session_expire),
                    Modifier
                        .align(alignment = Alignment.Start)
                        .padding(top = 20.dp, start = 30.dp),
                    color = Colors.text_white,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp)

            Text(sessionExpire.value,
                    Modifier
                        .align(alignment = Alignment.Start)
                        .padding(top = 10.dp, start = 30.dp),
                    color = Colors.text_white,
                    fontSize = 16.sp)

            Text(stringResource(Res.string.beyond_login_session_email),
                    Modifier
                        .align(alignment = Alignment.Start)
                        .padding(top = 20.dp, start = 30.dp),
                    color = Colors.text_white,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp)

            Text(accountEmail.value,
                    Modifier
                        .align(alignment = Alignment.Start)
                        .padding(top = 10.dp, start = 30.dp),
                    color = Colors.text_white,
                    fontSize = 16.sp)

            Spacer(Modifier.weight(1f))

            Box(
                    Modifier
                        .padding(bottom = 40.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
            ) {
                Elements.IconButton(stringResource(Res.string.beyond_login_session_logout),
                        Res.drawable.beyond_login_logout, bgColor = Colors.button_error) {

                    self.sessionLogout { success ->
                        if (success) {
                            self.viewService.listener.logOut(success)

                        } else {
                            ToastBar.showMessage("Error logging out", true)
                        }
                    }
                }
            }
        }
    }

    companion object {

        fun init(self: BeyondLogin) {
            self.requestView(ControllerView.Screen.SESSION)
        }
    }
}