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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.climbeyond.beyondlogin.BeyondLogin
import app.climbeyond.beyondlogin.GoogleSignInResult
import app.climbeyond.beyondlogin.Session
import app.climbeyond.beyondlogin.SessionInfo
import app.climbeyond.beyondlogin.Settings
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.helpers.Colors
import app.climbeyond.beyondlogin.helpers.ToastBar
import app.climbeyond.beyondlogin.googlePlatformConfigured
import app.climbeyond.beyondlogin.requestGoogleIdToken
import app.climbeyond.beyondlogin.requestGoogleIdTokenSilently
import app.climbeyond.beyondlogin.ui.ControllerView
import app.climbeyond.beyondlogin.ui.component.Elements
import climbeyond.beyondlogin.generated.resources.Res
import climbeyond.beyondlogin.generated.resources.beyond_login_arrow_back
import climbeyond.beyondlogin.generated.resources.beyond_login_desc_show_password
import climbeyond.beyondlogin.generated.resources.beyond_login_eye
import climbeyond.beyondlogin.generated.resources.beyond_login_field_email_fill
import climbeyond.beyondlogin.generated.resources.beyond_login_field_password_fill
import climbeyond.beyondlogin.generated.resources.beyond_login_icon_email
import climbeyond.beyondlogin.generated.resources.beyond_login_icon_google
import climbeyond.beyondlogin.generated.resources.beyond_login_icon_lock
import climbeyond.beyondlogin.generated.resources.beyond_login_login
import climbeyond.beyondlogin.generated.resources.beyond_login_login_button_code
import climbeyond.beyondlogin.generated.resources.beyond_login_login_button_email
import climbeyond.beyondlogin.generated.resources.beyond_login_login_code_sent
import climbeyond.beyondlogin.generated.resources.beyond_login_login_divider_or
import climbeyond.beyondlogin.generated.resources.beyond_login_login_email
import climbeyond.beyondlogin.generated.resources.beyond_login_login_google
import climbeyond.beyondlogin.generated.resources.beyond_login_login_header
import climbeyond.beyondlogin.generated.resources.beyond_login_login_no_account
import climbeyond.beyondlogin.generated.resources.beyond_login_login_no_account_register
import climbeyond.beyondlogin.generated.resources.beyond_login_login_password
import climbeyond.beyondlogin.generated.resources.beyond_login_navigate_back
import climbeyond.beyondlogin.generated.resources.beyond_login_recovery_lost_password
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.openapitools.client.infrastructure.ApiClient
import org.openapitools.client.infrastructure.HttpResponse
import org.openapitools.client.models.ErrorGeneric
import org.openapitools.client.models.LoginFlow
import org.openapitools.client.models.SuccessfulNativeLogin
import org.openapitools.client.models.UiNodeInputAttributes
import org.openapitools.client.models.UpdateLoginFlowWithCodeMethod
import org.openapitools.client.models.UpdateLoginFlowWithOidcMethod
import org.openapitools.client.models.UpdateLoginFlowWithPasswordMethod


class LoginView(private val self: BeyondLogin) : ControllerView.RequireView {
    private var errorMessage = mutableStateOf("")
    private var passwordVisible: MutableState<KeyboardType> = mutableStateOf(KeyboardType.Password)

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val codeView = mutableStateOf(false)
    private val googleButtonEnabled = mutableStateOf(true)

    private fun googleOidcAvailable(): Boolean =
        self.settings.googleOidcEnabled && googlePlatformConfigured(self.settings)

    private val digitsEdit: Array<MutableState<String>> = arrayOf(
        mutableStateOf(""),
        mutableStateOf(""),
        mutableStateOf(""),
        mutableStateOf(""),
        mutableStateOf(""),
        mutableStateOf(""),
    )

    @Composable
    override fun View() {
        val coroutine = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            if (googleOidcAvailable()) {
                trySilentGoogleLogin(coroutine)
            }
        }

        Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
        ) {
            Header()
            if (codeView.value) {
                ContentCode(coroutine)
            } else {
                Content(coroutine)
            }
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
                    if (codeView.value) {
                        codeView.value = false
                        return@clickable
                    }

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

        if (googleOidcAvailable()) {
            Elements.GoogleButton(
                stringResource(Res.string.beyond_login_login_google),
                Res.drawable.beyond_login_icon_google,
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 60.dp),
                buttonEnabled = googleButtonEnabled
            ) {
                doLoginGoogle(coroutine, googleButtonEnabled)
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(color = Colors.divider, modifier = Modifier
                    .weight(1f)
                    .height(1.dp))
                Text(stringResource(Res.string.beyond_login_login_divider_or),
                    Modifier.padding(start = 10.dp, end = 10.dp),
                    color = Colors.text_white,
                    fontSize = 14.sp)
                Divider(color = Colors.divider, modifier = Modifier
                    .weight(1f)
                    .height(1.dp))
            }
        }

        Elements.EditText(
            stringResource(Res.string.beyond_login_login_email),
            Modifier.padding(start = 20.dp, end = 20.dp, top = if (googleOidcAvailable()) 20.dp else 60.dp),
            email,
            fieldType = mutableStateOf(KeyboardType.Email),
            leadingIcon = leadingEmailIcon,
            valueChange = {
                if (errorMessage.value.isNotEmpty()) {
                    errorMessage.value = ""
                }
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
            password,
            passwordVisible,
            focusNext = false,
            leadingIcon = leadingPassIcon,
            trailingIcon = trailingPassIcon,
            valueChange = {
                if (errorMessage.value.isNotEmpty()) {
                    errorMessage.value = ""
                }
            }
        ) {
            keyboardController?.hide()
        }

        val buttonText = if (self.settings.codeLogin && password.value.isEmpty()) {
            stringResource(Res.string.beyond_login_login_button_code)
        } else {
            stringResource(Res.string.beyond_login_login_button_email)
        }
        Elements.IconButton(buttonText,
            Res.drawable.beyond_login_login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            buttonEnabled = loginButtonEnabled
        ) {
            keyboardController?.hide()

            if (email.value.isEmpty()) {
                errorMessage.value = fieldEmailFill
                return@IconButton
            }
            if (password.value.isEmpty()) {
                if (self.settings.codeLogin) {
                    requestCodeLogin(coroutine, email.value, loginButtonEnabled)
                } else {
                    errorMessage.value = fieldPasswordFill
                }
                return@IconButton
            }

            doLoginEmail(coroutine, email.value, password.value, loginButtonEnabled)
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
    private fun ContentCode(coroutine: CoroutineScope) {
        val focusManager = LocalFocusManager.current
        val callback = { digits: String ->
            handleLoginCode(coroutine, digits)
        }

        Text(stringResource(Res.string.beyond_login_login_code_sent),
            Modifier
                .padding(top = 60.dp, start = 40.dp, end = 40.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Colors.text_white,
            fontSize = 14.sp)

        Row(
            Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Elements.DigitField(coroutine, digitsEdit, focusManager, 0, callback)
            Elements.DigitField(coroutine, digitsEdit, focusManager, 1, callback)
            Elements.DigitField(coroutine, digitsEdit, focusManager, 2, callback)
            Elements.DigitField(coroutine, digitsEdit, focusManager, 3, callback)
            Elements.DigitField(coroutine, digitsEdit, focusManager, 4, callback)
            Elements.DigitField(coroutine, digitsEdit, focusManager, 5, callback)
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

    private fun requestCodeLogin(
        coroutine: CoroutineScope, email: String, loginButtonEnabled: MutableState<Boolean>
    ) {
        coroutine.launch {
            val body = UpdateLoginFlowWithCodeMethod(
                identifier = email,
                method = "code"
            )

            self.viewService.getOryApi()
                .updateLoginFlow(self.viewService.oryFlowId,
                    body, null, null)

            codeView.value = true
            loginButtonEnabled.value = true
        }
    }

    private fun doLoginEmail(
        coroutine: CoroutineScope, email: String, password: String,
        loginButtonEnabled: MutableState<Boolean>
    ) {
        // Disable button until some failure result - success keeps button disabled
        loginButtonEnabled.value = false

        // clear possible error
        errorMessage.value = ""

        coroutine.launch {
            try {
                val body = UpdateLoginFlowWithPasswordMethod(
                    identifier = email,
                    method = "password",
                    password = password
                )

                val response = self.viewService.getOryApi()
                    .updateLoginFlow(self.viewService.oryFlowId,
                        body, null, null)

                if (response.success) {
                    val body = response.body()

                    body.sessionToken?.let { token ->
                        handleLoginSuccess(coroutine, token, body) {
                            loginButtonEnabled.value = true
                        }
                    }

                } else {
                    loginButtonEnabled.value = true
                    handleLoginError(response, response.response.bodyAsText())
                }

            } catch (ex: Exception) {
                BLLogger.logWarning("LoginView.doLogin exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }

    private fun doLoginGoogle(
        coroutine: CoroutineScope, googleButtonEnabled: MutableState<Boolean>
    ) {
        // Disable button until some failure result - success keeps button disabled
        googleButtonEnabled.value = false

        // clear possible error
        errorMessage.value = ""

        coroutine.launch {
            try {
                submitGoogleSignIn(coroutine, requestGoogleIdToken(self), googleButtonEnabled)

            } catch (ex: Exception) {
                googleButtonEnabled.value = true
                BLLogger.logWarning("LoginView.doLoginGoogle exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }

    /**
     * Warms up the platform's credential provider as soon as the login screen appears, and
     * transparently signs the user back in if there's already an authorized Google account for
     * this app on the device - without showing an account picker. A cancellation/error here just
     * means no authorized account is available yet, so it's ignored: the Google button remains
     * available for an explicit, picker-driven sign-in.
     */
    private fun trySilentGoogleLogin(coroutine: CoroutineScope) {
        coroutine.launch {
            googleButtonEnabled.value = false

            try {
                val result = requestGoogleIdTokenSilently(self)
                if (result is GoogleSignInResult.Success) {
                    submitGoogleSignIn(coroutine, result, googleButtonEnabled)
                    return@launch
                }

            } catch (ex: Exception) {
                BLLogger.logDebug("LoginView.trySilentGoogleLogin exception: $ex")
            }

            googleButtonEnabled.value = true
        }
    }

    private suspend fun submitGoogleSignIn(
        coroutine: CoroutineScope,
        result: GoogleSignInResult,
        googleButtonEnabled: MutableState<Boolean>
    ) {
        when (result) {
            is GoogleSignInResult.Success -> {
                val body = UpdateLoginFlowWithOidcMethod(
                    method = "oidc",
                    provider = "google",
                    idToken = result.idToken,
                    idTokenNonce = result.nonce
                )

                val response = self.viewService.getOryApi()
                    .updateLoginFlow(self.viewService.oryFlowId,
                        body, null, null)

                val bodyText = response.response.bodyAsText()

                // A 2xx status here doesn't guarantee a completed login - Ory answers a social
                // sign-in whose email collides with an existing, differently-authenticated
                // account with a fresh LoginFlow (asking the user to sign in with that other
                // method first) instead of a session, so the body may not be a
                // SuccessfulNativeLogin at all even though the request "succeeded".
                val loginBody = if (response.success) {
                    try {
                        ApiClient.JSON_DEFAULT.decodeFromString<SuccessfulNativeLogin>(bodyText)
                    } catch (ex: Exception) {
                        null
                    }
                } else null

                val token = loginBody?.sessionToken
                if (token != null) {
                    handleLoginSuccess(coroutine, token, loginBody) {
                        googleButtonEnabled.value = true
                    }
                } else {
                    googleButtonEnabled.value = true
                    handleLoginError(response, bodyText)
                }
            }

            GoogleSignInResult.Cancelled -> {
                googleButtonEnabled.value = true
            }

            is GoogleSignInResult.Error -> {
                googleButtonEnabled.value = true
                errorMessage.value = result.message
            }
        }
    }

    private fun handleLoginCode(coroutine: CoroutineScope, code: String) {
        coroutine.launch {
            try {
                val body = UpdateLoginFlowWithCodeMethod(
                    identifier = email.value,
                    method = "code",
                    code = code,
                )

                val response = self.viewService.getOryApi()
                    .updateLoginFlow(self.viewService.oryFlowId,
                        body, null, null)

                if (response.success) {
                    val body = response.body()

                    body.sessionToken?.let { token ->
                        handleLoginSuccess(coroutine, token, body)
                    }

                } else {
                    handleLoginError(response, response.response.bodyAsText())
                }

            } catch (ex: Exception) {
                BLLogger.logWarning("LoginView.handleLoginCode exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }

    private fun prefillIdentifier(flow: LoginFlow) {
        flow.ui.nodes.firstOrNull {
            (it.attributes as? UiNodeInputAttributes)?.name == "identifier"
        }?.let {
            ((it.attributes as UiNodeInputAttributes).value as? JsonPrimitive)?.contentOrNull?.let { identifier ->
                email.value = identifier
            }
        }
    }

    private suspend fun handleLoginError(response: HttpResponse<SuccessfulNativeLogin>, bodyText: String) {
        // Always log the raw server response so the actual reason is visible without having to
        // separately enable Settings.ApiLogLevel.BODY to inspect the wire traffic
        BLLogger.logWarning("LoginView.handleLoginError: ${response.status} -- $bodyText")

        // Flow has expired
        if (response.status == 410) {
            ToastBar.showMessage("Login expired - refreshing", true)
            init(self)
            return
        }

        try {
            // Bad request 400 - invalid credentials and maybe some other flow validation errors
            val errorResponse = ApiClient.JSON_DEFAULT.decodeFromString<LoginFlow>(bodyText)

            // Ory re-issues a fresh flow (dropping the oidc node) when a social sign-in's email
            // matches an existing account that doesn't have that provider linked yet - switch to
            // it so the next password/code submit lands on the flow Kratos will actually link
            // the provider against, instead of the already-consumed oidc flow.
            if (errorResponse.id != self.viewService.oryFlowId) {
                self.viewService.oryFlowId = errorResponse.id
                prefillIdentifier(errorResponse)
            }

            errorResponse.ui.messages?.forEach {
                errorMessage.value = it.text
            }
            errorResponse.ui.nodes.forEach {
                it.messages.forEach { error ->
                    errorMessage.value = error.text
                }
            }

        } catch (ex: Exception) {
            // Not a LoginFlow body - e.g. Ory's generic error envelope for a flow that could no
            // longer be found, or a server/administrator-side rejection (see the logged body
            // above for the exact reason)
            val message = try {
                ApiClient.JSON_DEFAULT.decodeFromString<ErrorGeneric>(bodyText).error.let {
                    it.reason ?: it.message
                }
            } catch (ex2: Exception) {
                null
            }

            ToastBar.showMessage(message ?: "Login failed (${response.status})", true)
        }
    }

    private fun handleLoginSuccess(
        coroutine: CoroutineScope,
        token: String,
        body: SuccessfulNativeLogin,
        appFailure: (() -> Unit)? = null,
    ) {
        Session.storeSave(self.platform, token, body.session)

        coroutine.launch(Dispatchers.Main) {
            val expires = body.session.expiresAt?.toEpochMilliseconds() ?: -1
            self.viewService.listener.loginSuccess(
                SessionInfo(body.session.id, token, expires),
                { success ->
                    // Tell the client that we are now logged and BeyondLogin should close
                    self.viewService.listener.loggedClose(success)
                },
                { failure ->
                    errorMessage.value = failure
                    appFailure?.invoke()
                })
        }
    }

    companion object {

        suspend fun init(self: BeyondLogin) {
            BLLogger.logDebug("LoginView.init")

            try {
                val result = self.viewService.getOryApi()
                    .createNativeLoginFlow(false, "aal1", null)

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