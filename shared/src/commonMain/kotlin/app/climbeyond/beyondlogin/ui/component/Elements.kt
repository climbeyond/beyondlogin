package app.climbeyond.beyondlogin.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.climbeyond.beyondlogin.helpers.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource


object Elements {

    @Composable
    fun IconButton(text: String, icon: DrawableResource, bgColor: Color = Colors.button_basic,
            iconHorizontal: Boolean = true, modifier: Modifier = Modifier,
            buttonEnabled: MutableState<Boolean> = mutableStateOf(true),
            click: () -> Unit) {

        Button(
                click,
                modifier.height(48.dp),
                enabled = buttonEnabled.value,
                shape = RoundedCornerShape(3.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = bgColor)
        ) {
            Box(
                    Modifier.padding(20.dp, 10.dp),
                    contentAlignment = Alignment.Center
            ) {
                if (iconHorizontal) {
                    Row(
                            verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                                imageVector = vectorResource(icon),
                                contentDescription = "drawable icons",
                                tint = Colors.drawable_tint_white,
                        )
                        Text(
                                text = text,
                                Modifier.padding(start = 5.dp),
                                color = Colors.text_white,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                        )
                    }
                } else {
                    Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                                imageVector = vectorResource(icon),
                                contentDescription = "drawable icons",
                                tint = Colors.drawable_tint_white,
                        )
                        Text(
                                text = text,
                                color = Colors.text_white,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    fun editTextIcon(res: DrawableResource, contentDesc: StringResource? = null,
            tint: Color = Colors.drawable_tint_white,
            click: (() -> Unit)? = null):
            @Composable (() -> Unit) = {

        val modifier = click?.let {
            Modifier.clickable(onClick = it)
        } ?: Modifier

        val description = contentDesc?.let { stringResource(contentDesc) } ?: ""
        Icon(vectorResource(res), description, modifier.height(18.dp).width(18.dp), tint)
    }

    @Composable
    fun Button(text: String, modifier: Modifier,
            buttonEnabled: MutableState<Boolean> = mutableStateOf(true),
            bgColor: Color = Colors.button_basic,
            click: () -> Unit) {

        Button(
                click,
                modifier.height(48.dp),
                enabled = buttonEnabled.value,
                shape = RoundedCornerShape(3.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = bgColor)
        ) {
            Box(
                    Modifier
                        .padding(20.dp, 10.dp),
                    contentAlignment = Alignment.Center
            ) {
                Text(
                        text = text.uppercase(),
                        color = Colors.text_white,
                        textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    fun EditText(
        label: String,
        modifier: Modifier,
        fieldText: MutableState<String>,
        fieldType: MutableState<KeyboardType> = mutableStateOf(KeyboardType.Text),
        focusNext: Boolean = false,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        closeOnDone: Boolean = false,
        lines: Int = 1,
        textCenter: Boolean = false,
        valueChange: ((value: String) -> Unit)? = null,
        done: ((value: String) -> Unit)? = null,
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = fieldText.value,
            onValueChange = { change: String ->
                fieldText.value = change
                valueChange?.invoke(change)
            },
            modifier = modifier.fillMaxWidth(),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = if (fieldType.value == KeyboardType.Password
                || fieldType.value == KeyboardType.NumberPassword
            )
                PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                imeAction = if (focusNext) ImeAction.Next else ImeAction.Done,
                keyboardType = fieldType.value
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (closeOnDone) {
                        keyboardController?.hide()
                    }
                }
            ),
            singleLine = (lines == 1),
            minLines = lines,
            maxLines = lines,
            label = { Text(label) },
            textStyle = LocalTextStyle.current.copy(
                textAlign = if (textCenter) TextAlign.Center else TextAlign.Start
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Colors.text_green,
                unfocusedBorderColor = Colors.white,
                focusedLabelColor = Colors.white,
                cursorColor = Colors.white,
                textColor = Colors.text_white,
                unfocusedLabelColor = Colors.text_gray,
                errorBorderColor = Colors.button_error
            )
        )
    }

    @Composable
    fun RecoveryEditText(
        modifier: Modifier,
        fieldText: MutableState<String>,
        fieldType: MutableState<KeyboardType> = mutableStateOf(KeyboardType.Text),
        focusNext: Boolean = false,
        leadingIcon: @Composable (() -> Unit)? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        closeOnDone: Boolean = false,
        valueChange: ((value: String) -> Unit)? = null,
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        OutlinedTextField(
            value = fieldText.value,
            onValueChange = { change: String ->
                valueChange?.invoke(change)
            },
            modifier = modifier.fillMaxWidth(),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = if (fieldType.value == KeyboardType.Password
                || fieldType.value == KeyboardType.NumberPassword
            )
                PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                imeAction = if (focusNext) ImeAction.Next else ImeAction.Done,
                keyboardType = fieldType.value
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (closeOnDone) {
                        keyboardController?.hide()
                    }
                }
            ),
            singleLine = true,
            minLines = 1,
            maxLines = 1,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Colors.text_green,
                unfocusedBorderColor = Colors.white,
                focusedLabelColor = Colors.white,
                cursorColor = Colors.white,
                textColor = Colors.text_white,
                unfocusedLabelColor = Colors.text_gray,
                errorBorderColor = Colors.button_error
            )
        )
    }

    @Composable
    fun Banner(text: String, modifier: Modifier = Modifier) {
        Box(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Colors.bg_primary)
                    .border(BorderStroke(1.dp, Colors.button_error), RoundedCornerShape(5.dp))
                    .padding(10.dp)
                    .then(modifier),
                contentAlignment = Alignment.Center
        ) {
            Text(
                    text = text.uppercase(),
                    color = Colors.text_white,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
            )
        }
    }
}

