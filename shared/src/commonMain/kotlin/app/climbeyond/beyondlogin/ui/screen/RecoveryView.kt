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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.climbeyond.beyondlogin.BeyondLogin
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.helpers.Colors
import app.climbeyond.beyondlogin.helpers.ToastBar
import app.climbeyond.beyondlogin.ui.ControllerView
import app.climbeyond.beyondlogin.ui.component.Elements
import climbeyond.beyondlogin.generated.resources.Res
import climbeyond.beyondlogin.generated.resources.beyond_login_arrow_back
import climbeyond.beyondlogin.generated.resources.beyond_login_desc_show_password
import climbeyond.beyondlogin.generated.resources.beyond_login_done
import climbeyond.beyondlogin.generated.resources.beyond_login_eye
import climbeyond.beyondlogin.generated.resources.beyond_login_field_email_fill
import climbeyond.beyondlogin.generated.resources.beyond_login_field_password_fill
import climbeyond.beyondlogin.generated.resources.beyond_login_icon_email
import climbeyond.beyondlogin.generated.resources.beyond_login_icon_lock
import climbeyond.beyondlogin.generated.resources.beyond_login_login
import climbeyond.beyondlogin.generated.resources.beyond_login_login_button
import climbeyond.beyondlogin.generated.resources.beyond_login_login_email
import climbeyond.beyondlogin.generated.resources.beyond_login_login_no_account
import climbeyond.beyondlogin.generated.resources.beyond_login_login_no_account_register
import climbeyond.beyondlogin.generated.resources.beyond_login_navigate_back
import climbeyond.beyondlogin.generated.resources.beyond_login_recovery
import climbeyond.beyondlogin.generated.resources.beyond_login_recovery_button
import climbeyond.beyondlogin.generated.resources.beyond_login_recovery_change_password_button
import climbeyond.beyondlogin.generated.resources.beyond_login_recovery_header
import climbeyond.beyondlogin.generated.resources.beyond_login_recovery_new_password
import climbeyond.beyondlogin.generated.resources.beyond_login_success_return
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import sh.ory.model.RecoveryFlowState
import sh.ory.model.UiText
import sh.ory.model.UpdateRecoveryFlowBody
import sh.ory.model.UpdateSettingsFlowBody


class RecoveryView(private val self: BeyondLogin) : ControllerView.RequireView {
    private var errorMessage = mutableStateOf("")
    private val subView = mutableStateOf(VIEWS.EMAIL)
    private val confirmationText = mutableStateOf("")
    private val digitsEdit: Array<((digit: String) -> Unit)?> = arrayOf(null, null, null, null, null, null)
    private val digits = Array(6) { "" }

    private var passwordVisible: MutableState<KeyboardType> = mutableStateOf(KeyboardType.Password)
    private val resetButtonEnabled = mutableStateOf(true)

    private var settingsFlow: String? = null
    private var settingsSessionToken: String? = null
    private var password: String = ""

    var email: String = ""

    enum class VIEWS {
        EMAIL,
        CONFIRMATION,
        RESET,
        SUCCESS
    }

    @Serializable
    data class RecoveryBody(
            override val email: String,
            override val method: UpdateRecoveryFlowBody.Method,
            override val csrfToken: String? = null,
            override val code: String? = null,
            override val transientPayload: String? = null,
            ) : UpdateRecoveryFlowBody

    @Serializable
    data class SettingsBody(
            override val method: String,
            override val password: String,
    ): UpdateSettingsFlowBody {

        override val traits: String
            get() = ""
        override val csrfToken: String?
            get() = null
        override val transientPayload: String?
            get() = null
        override val flow: String?
            get() = null
        override val link: String?
            get() = null
        override val unlink: String?
            get() = null
        override val upstreamParameters: String?
            get() = null
        override val totpCode: String?
            get() = null
        override val totpUnlink: Boolean?
            get() = null
        override val webauthnRegister: String?
            get() = null
        override val webauthnRegisterDisplayname: String?
            get() = null
        override val webauthnRemove: String?
            get() = null
        override val lookupSecretConfirm: Boolean?
            get() = null
        override val lookupSecretDisable: Boolean?
            get() = null
        override val lookupSecretRegenerate: Boolean?
            get() = null
        override val lookupSecretReveal: Boolean?
            get() = null
        override val passkeyRemove: String?
            get() = null
        override val passkeySettingsRegister: String?
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
            Header(coroutine)
            Content(coroutine)
            Footer(coroutine)
        }
    }

    @Composable
    private fun ColumnScope.Header(coroutine: CoroutineScope) {
        Icon(
            vectorResource(Res.drawable.beyond_login_arrow_back),
            stringResource(Res.string.beyond_login_navigate_back),
            Modifier
                .padding(top = 30.dp, start = 30.dp)
                .clickable {
                    coroutine.launch {
                        LoginView.init(self)
                    }
                },
            tint = Colors.drawable_tint_white
        )

        Text(
            stringResource(Res.string.beyond_login_recovery_header).uppercase(),
            Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 50.dp),
            color = Colors.text_white,
            fontWeight = FontWeight.Light,
            fontSize = 22.sp
        )

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
        when (subView.value) {
            VIEWS.EMAIL -> subEmail(coroutine)
            VIEWS.CONFIRMATION -> subConfirmation(coroutine)
            VIEWS.RESET -> subReset(coroutine)
            VIEWS.SUCCESS -> subSuccess(coroutine)
        }

        if (errorMessage.value.isNotEmpty()) {
            Text(
                errorMessage.value,
                Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 40.dp, start = 40.dp, end = 40.dp),
                color = Colors.text_error,
                fontSize = 14.sp
            )
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
            Text(
                stringResource(Res.string.beyond_login_login_no_account),
                Modifier
                    .align(Alignment.CenterVertically)
                    .height(48.dp)
                    .wrapContentHeight(),
                color = Colors.text_white,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

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
                Text(
                    stringResource(Res.string.beyond_login_login_no_account_register),
                    Modifier.padding(start = 10.dp, end = 10.dp),
                    color = Colors.text_highlight,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    @Composable
    private fun subEmail(coroutine: CoroutineScope) {
        val fieldEmailFill = stringResource(Res.string.beyond_login_field_email_fill)
        val keyboardController = LocalSoftwareKeyboardController.current
        val recoveryButtonEnabled = remember { mutableStateOf(true) }

        val leadingEmailIcon = Elements.editTextIcon(Res.drawable.beyond_login_icon_email)
        Elements.EditText(stringResource(Res.string.beyond_login_login_email),
            Modifier.padding(start = 20.dp, end = 20.dp, top = 60.dp),
            leadingIcon = leadingEmailIcon,
            trailingIcon = null, valueChange = {
                errorMessage.value = ""
                email = it
            })

        Elements.IconButton(
            stringResource(Res.string.beyond_login_recovery_button),
            Res.drawable.beyond_login_recovery,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            buttonEnabled = recoveryButtonEnabled
        ) {

            keyboardController?.hide()

            if (email.isEmpty()) {
                errorMessage.value = fieldEmailFill
                return@IconButton
            }

            requestRecovery(coroutine, email, recoveryButtonEnabled)
        }
    }

    @Composable
    private fun subConfirmation(coroutine: CoroutineScope) {
        val focusManager = LocalFocusManager.current

        Text(confirmationText.value,
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
            digitField(coroutine, focusManager, 0)
            digitField(coroutine, focusManager, 1)
            digitField(coroutine, focusManager, 2)
            digitField(coroutine, focusManager, 3)
            digitField(coroutine, focusManager, 4)
            digitField(coroutine, focusManager, 5)
        }
    }

    @Composable
    private fun digitField(coroutine: CoroutineScope, focusManager: FocusManager, index: Int) {
        Elements.EditText("", Modifier
            .padding(horizontal = 3.dp)
            .width(50.dp)
            .onFocusChanged {
                if (it.isFocused) {
                    digitsEdit[index]?.invoke("")
                    digits[index] = ""
                }
            },
            mutableStateOf(KeyboardType.Number),
            textCenter = true,
            valueChange = {
                digits[index] = it

                val digits = digits.joinToString(separator = "")

                if (digits.length == 6) {
                    handleRecoveryCode(coroutine, focusManager, digits)
                } else {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            })
    }

    @Composable
    private fun subReset(coroutine: CoroutineScope) {
        val keyboardController = LocalSoftwareKeyboardController.current
        val fieldPasswordFill = stringResource(Res.string.beyond_login_field_password_fill)

        val leadingPassIcon = Elements.editTextIcon(Res.drawable.beyond_login_icon_lock)
        val trailingPassIcon = Elements.editTextIcon(Res.drawable.beyond_login_eye,
                Res.string.beyond_login_desc_show_password) {
            passwordVisible.value = if (passwordVisible.value == KeyboardType.Text)
                KeyboardType.Password else KeyboardType.Text
        }
        Elements.EditText(stringResource(Res.string.beyond_login_recovery_new_password),
                Modifier.padding(start = 20.dp, end = 20.dp, top = 60.dp),
                passwordVisible, focusNext = false,
                leadingIcon = leadingPassIcon,
                trailingIcon = trailingPassIcon,
                valueChange = {
                    errorMessage.value = ""
                    password = it
                }
        ) { _ ->
            keyboardController?.hide()
        }

        Elements.IconButton(stringResource(Res.string.beyond_login_recovery_change_password_button),
                Res.drawable.beyond_login_icon_lock,
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp, top = 50.dp),
                buttonEnabled = resetButtonEnabled) {

            keyboardController?.hide()

            if (password.isEmpty()) {
                errorMessage.value = fieldPasswordFill
                return@IconButton
            }

            handlePasswordChange(coroutine, password, resetButtonEnabled)
        }
    }

    @Composable
    private fun subSuccess(coroutine: CoroutineScope) {
        Column(
                Modifier
                    .padding(top = 60.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(vectorResource(Res.drawable.beyond_login_done), "Success",
                    Modifier.height(72.dp).width(72.dp), tint = Colors.text_green)

            Text(stringResource(Res.string.beyond_login_success_return),
                    Modifier.padding(top = 30.dp, start = 30.dp, end = 30.dp),
                    color = Colors.text_white)
        }

        Elements.IconButton(stringResource(Res.string.beyond_login_login_button),
                Res.drawable.beyond_login_login,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 50.dp)) {

            coroutine.launch {
                LoginView.init(self)
            }
        }
    }

    private fun requestRecovery(coroutine: CoroutineScope, email: String,
            loginButtonEnabled: MutableState<Boolean>) {

        // Disable button until some failure result - success keeps button disabled
        loginButtonEnabled.value = false

        val method = RecoveryBody(email, method = UpdateRecoveryFlowBody.Method.CODE)

        coroutine.launch {
            try {
                val response = self.viewService.getOryApi()
                    .updateRecoveryFlow(self.viewService.oryFlowId, method, null, null)

                if (response.success) {
                    confirmationText.value = response.body().ui.messages?.let {
                        if (it.isNotEmpty()) it[0].text else ""
                    } ?: ""

                    errorMessage.value = ""
                    subView.value = VIEWS.CONFIRMATION

                } else {
                    loginButtonEnabled.value = true

                    response.body().ui.nodes.forEach { node ->
                        node.messages.forEach { message ->
                            if (message.type == UiText.Type.ERROR) {
                                errorMessage.value = message.text
                            }
                        }
                    }
                }

            } catch (ex: Exception) {
                BLLogger.logError("RecoveryView.requestRecovery exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }

    private fun handleRecoveryCode(coroutine: CoroutineScope, focusManager: FocusManager,
            code: String) {
        val method = RecoveryBody(email = "", method = UpdateRecoveryFlowBody.Method.CODE, code = code)

        coroutine.launch {
            try {
                val response = self.viewService.getOryApi()
                    .updateRecoveryFlow(self.viewService.oryFlowId, method, null, null)

                val body = response.body()

                if (response.success) {
                    if (body.state == RecoveryFlowState.PASSED_CHALLENGE) {
                        var sessionToken = ""
                        var flowId = ""

                        body.continueWith?.forEach {
                            val action = it["action"]?.jsonPrimitive?.content

                            if (action == "set_ory_session_token") {
                                sessionToken = it["ory_session_token"]?.jsonPrimitive?.content ?: "--"
                            }
                            if (action == "show_settings_ui") {
                                flowId = it["flow"]?.jsonObject?.get("id")?.jsonPrimitive?.content ?: "--"
                            }
                        }

                        coroutine.launch {
                            val flow = self.viewService.getOryApi().getSettingsFlow(flowId, sessionToken)

                            if (flow.success) {
                                settingsFlow = flowId
                                settingsSessionToken = sessionToken
                                errorMessage.value = ""
                                subView.value = VIEWS.RESET
                            }
                        }
                        return@launch
                    }
                }

                body.ui.messages?.forEach { node ->
                    errorMessage.value = node.text
                }

                focusManager.clearFocus()
                digitsEdit.forEach { it?.invoke("") }
                digits.fill("")

            } catch (ex: Exception) {
                BLLogger.logError("RecoveryView.handleRecoveryCode exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }

    private fun handlePasswordChange(coroutine: CoroutineScope, password: String,
            resetButtonEnabled: MutableState<Boolean>) {

        resetButtonEnabled.value = false

        coroutine.launch {
            settingsFlow?.let { settingsFlow ->
                val settingsBody = SettingsBody(method = "password", password = password)
                val result = self.viewService.getOryApi()
                    .updateSettingsFlow(settingsFlow, settingsBody, settingsSessionToken)

                if (result.success) {
                    errorMessage.value = ""
                    subView.value = VIEWS.SUCCESS

                } else {
                    result.body().ui.nodes.forEach { node ->
                        node.messages.forEach { message ->
                            if (message.type == UiText.Type.ERROR) {
                                errorMessage.value = message.text
                            }
                        }
                    }

                    resetButtonEnabled.value = true
                }
            }
        }
    }

    companion object {

        suspend fun init(self: BeyondLogin) {
            BLLogger.logDebug("RecoveryView.init")

            try {
                val result = self.viewService.getOryApi().createNativeRecoveryFlow()

                if (result.success) {
                    self.viewService.oryFlowId = result.provider.body(result.response).id
                    self.viewService.currentView.value = ControllerView.Screen.RECOVERY

                } else {
                    val statusCode = result.response.status.value

                    BLLogger.logWarning("onFailure: $statusCode - ${result.body()}")
                }

            } catch (ex: Exception) {
                BLLogger.logError("RecoveryView.init exception: $ex")
                ToastBar.showMessage(ex.message ?: "Unknown login error", true)
            }
        }
    }
}