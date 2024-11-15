package app.climbeyond.beyondlogin.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.climbeyond.beyondlogin.BeyondLogin
import app.climbeyond.beyondlogin.Session
import app.climbeyond.beyondlogin.SessionInfo
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.helpers.Colors
import app.climbeyond.beyondlogin.helpers.ToastBar
import app.climbeyond.beyondlogin.ui.ControllerView
import app.climbeyond.beyondlogin.ui.component.Elements
import climbeyond.beyondlogin.generated.resources.Res
import climbeyond.beyondlogin.generated.resources.beyond_login_arrow_back
import climbeyond.beyondlogin.generated.resources.beyond_login_desc_show_password
import climbeyond.beyondlogin.generated.resources.beyond_login_eye
import climbeyond.beyondlogin.generated.resources.beyond_login_field_email_fill
import climbeyond.beyondlogin.generated.resources.beyond_login_field_password_fill
import climbeyond.beyondlogin.generated.resources.beyond_login_icon_email
import climbeyond.beyondlogin.generated.resources.beyond_login_icon_lock
import climbeyond.beyondlogin.generated.resources.beyond_login_login
import climbeyond.beyondlogin.generated.resources.beyond_login_login_button
import climbeyond.beyondlogin.generated.resources.beyond_login_login_email
import climbeyond.beyondlogin.generated.resources.beyond_login_login_header
import climbeyond.beyondlogin.generated.resources.beyond_login_login_no_account
import climbeyond.beyondlogin.generated.resources.beyond_login_login_no_account_register
import climbeyond.beyondlogin.generated.resources.beyond_login_login_password
import climbeyond.beyondlogin.generated.resources.beyond_login_navigate_back
import climbeyond.beyondlogin.generated.resources.beyond_login_recovery_lost_password
import io.ktor.util.reflect.typeInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import sh.ory.model.LoginFlow
import sh.ory.model.UpdateLoginFlowBody


class LoginView(private val self: BeyondLogin) : ControllerView.RequireView {
    private var errorMessage = mutableStateOf("")
    private var passwordVisible: MutableState<KeyboardType> = mutableStateOf(KeyboardType.Password)

    var email: String = ""
    var password: String = ""

    @Serializable
    data class LoginBody(
            override val method: String,
            override val identifier: String,
            override val password: String
    ) : UpdateLoginFlowBody {

        override val csrfToken: String
            get() = ""
        override val provider: String
            get() = ""
        override val totpCode: String
            get() = ""
        override val lookupSecret: String
            get() = ""
        override val passwordIdentifier: String?
            get() = null
        override val transientPayload: String?
            get() = null
        override val idToken: String?
            get() = null
        override val idTokenNonce: String?
            get() = null
        override val traits: String?
            get() = null
        override val upstreamParameters: String?
            get() = null
        override val webauthnLogin: String?
            get() = null
        override val code: String?
            get() = null
        override val resend: String?
            get() = null
        override val passkeyLogin: String?
            get() = null
    }

    @Composable
    override fun View() {
        val coroutine = rememberCoroutineScope()

        Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
        ) {
            Header()
            Content(coroutine)
            Footer(coroutine)
        }
    }

    @Composable
    private fun ColumnScope.Header() {
        Icon(vectorResource(Res.drawable.beyond_login_arrow_back),
            stringResource(Res.string.beyond_login_navigate_back),
            Modifier
                .padding(top = 30.dp, start = 30.dp)
                .clickable {
                    self.viewService.let {
                        self.viewService.listener.closeBeyondLogin()
                    }
                },
            tint = Colors.drawable_tint_white)

        Text(stringResource(Res.string.beyond_login_login_header).uppercase(),
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
    }

    @Composable
    private fun ColumnScope.Content(coroutine: CoroutineScope) {
        val fieldEmailFill = stringResource(Res.string.beyond_login_field_email_fill)
        val fieldPasswordFill = stringResource(Res.string.beyond_login_field_password_fill)

        val keyboardController = LocalSoftwareKeyboardController.current

        val loginButtonEnabled = remember { mutableStateOf(true) }
        val leadingEmailIcon = remember {
            Elements.editTextIcon(Res.drawable.beyond_login_icon_email)
        }

        Elements.EditText(
            stringResource(Res.string.beyond_login_login_email),
            Modifier.padding(start = 20.dp, end = 20.dp, top = 60.dp),
            leadingIcon = leadingEmailIcon,
            valueChange = {
                if (errorMessage.value.isNotEmpty()) {
                    errorMessage.value = ""
                }
                email = it
            })

        val leadingPassIcon = Elements.editTextIcon(Res.drawable.beyond_login_icon_lock)
        val trailingPassIcon = Elements.editTextIcon(
            Res.drawable.beyond_login_eye,
            Res.string.beyond_login_desc_show_password
        ) {
            passwordVisible.value = if (passwordVisible.value == KeyboardType.Text)
                KeyboardType.Password else KeyboardType.Text
        }
        Elements.EditText(
            stringResource(Res.string.beyond_login_login_password),
            Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
            passwordVisible,
            focusNext = false,
            leadingIcon = leadingPassIcon,
            trailingIcon = trailingPassIcon,
            valueChange = {
                if (errorMessage.value.isNotEmpty()) {
                    errorMessage.value = ""
                }
                password = it
            }
        ) {
            keyboardController?.hide()
        }

        Elements.IconButton(stringResource(Res.string.beyond_login_login_button),
            Res.drawable.beyond_login_login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            buttonEnabled = loginButtonEnabled
        ) {
            keyboardController?.hide()

            if (email.isEmpty()) {
                errorMessage.value = fieldEmailFill
                return@IconButton
            }
            if (password.isEmpty()) {
                errorMessage.value = fieldPasswordFill
                return@IconButton
            }

            doLogin(coroutine, email, password, loginButtonEnabled)
        }

        if (errorMessage.value.isNotEmpty()) {
            Text(errorMessage.value,
                Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 40.dp, start = 40.dp, end = 40.dp),
                color = Colors.text_error,
                fontSize = 14.sp)
        }

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(stringResource(Res.string.beyond_login_recovery_lost_password),
                Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterVertically)
                    .height(48.dp)
                    .wrapContentHeight()
                    .clickable {
                        coroutine.launch {
                            RecoveryView.init(self)
                        }
                    },
                color = Colors.text_white,
                fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
    }

    @Composable
    private fun ColumnScope.Footer(coroutine: CoroutineScope) {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(stringResource(Res.string.beyond_login_login_no_account),
                Modifier
                    .align(Alignment.CenterVertically)
                    .height(48.dp)
                    .wrapContentHeight(),
                color = Colors.text_white,
                fontSize = 18.sp,
                textAlign = TextAlign.Center)

            Box(
                Modifier
                    .padding(start = 10.dp)
                    .height(48.dp)
                    .clickable {
                        coroutine.launch {
                            RegisterView.init(self)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(Res.string.beyond_login_login_no_account_register),
                    Modifier.padding(start = 10.dp, end = 10.dp),
                    color = Colors.text_highlight,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    private fun doLogin(coroutine: CoroutineScope, email: String, password: String,
            loginButtonEnabled: MutableState<Boolean>) {

        // Disable button until some failure result - success keeps button disabled
        loginButtonEnabled.value = false

        // clear possible error
        errorMessage.value = ""

        val method = LoginBody("password", email, password)

        coroutine.launch {
            try {
                val response = self.viewService.getOryApi()
                    .updateLoginFlow(self.viewService.oryFlowId, method, null, null)

                if (response.success) {
                    val body = response.body()

                    body.sessionToken?.let { token ->
                        Session.storeSave(self.platform, token, body.session)

                        coroutine.launch(Dispatchers.Main) {
                            val expires = body.session.expiresAt?.toEpochMilliseconds() ?: -1
                            self.viewService.listener.loginSuccess(
                                SessionInfo(body.session.id, token, expires),
                                { success ->
                                    // Tell client that we are now logged and BeyondLogin should close
                                    self.viewService.listener.loggedClose(success)
                                },
                                { failure ->
                                    loginButtonEnabled.value = true
                                    errorMessage.value = failure
                                })
                        }
                    } ?: {
                        CoroutineScope(Dispatchers.Main).launch {
                            loginButtonEnabled.value = false
                            self.viewService.listener.unknownException("LoginView success no sessionToken")
                        }
                    }

                } else {
                    loginButtonEnabled.value = true
                    self.viewService.listener.loginError()

                    val errorResponse = response.typedBody<LoginFlow>(typeInfo<LoginFlow>())
                    errorResponse.ui.messages?.forEach {
                        errorMessage.value = it.text
                    }
                }

            } catch (ex: Exception) {
                BLLogger.logWarning("LoginView.doLogin exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }

    companion object {

        suspend fun init(self: BeyondLogin) {
            BLLogger.logDebug("LoginView.init")

            try {
                val result = self.viewService.getOryApi().createNativeLoginFlow(false, "aal1", null)

                if (result.success) {
                    self.viewService.oryFlowId = result.body().id
                    self.viewService.currentView.value = ControllerView.Screen.LOGIN

                } else {
                    val statusCode = result.response.status.value

                    BLLogger.logWarning("onFailure: $statusCode - ${result.body()}")
                }

            } catch (ex: Exception) {
                BLLogger.logWarning("LoginView.init exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }
}