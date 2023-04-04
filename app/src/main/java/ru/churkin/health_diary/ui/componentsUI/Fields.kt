package ru.churkin.health_diary.ui.componentsUI

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.churkin.health_diary.R

@Composable
fun UserField(
    value: String,
    title: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Default,
    onValue: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h4
        )
        TextField(
            value = value,
            shape = RoundedCornerShape(20.dp),
            textStyle = TextStyle(
                color = MaterialTheme.colors.onBackground,
                fontFamily = FontFamily(Font(R.font.zen_maru_gothic_bold)),
                fontSize = 18.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onBackground,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            onValueChange = onValue
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoField(
    text: String,
    modifier: Modifier = Modifier,
    @StringRes label: Int? = null,
    placeholder: String? = null,
    isFocus: Boolean = false,
    isButton: Boolean = false,
    iconPainter: Int = R.drawable.ic_baseline_question_mark_24,
    iconTint: Color = MaterialTheme.colors.primary,
    height: Int = 56,
    isEnabled: Boolean = true,
    isSingleLine: Boolean = false,
    validateForce: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Default,
    textStyle: TextStyle = MaterialTheme.typography.h6,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.colors.onBackground,
        cursorColor = MaterialTheme.colors.primary,
        focusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
    ),
    onClickButton: (() -> Unit)? = null,
    validateFn: ((String) -> String?)? = null,
    hasFocus: ((Boolean) -> Unit)? = null,
    validateAfter: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    onInvalid: ((Boolean) -> Unit)? = null
) {
    val errorMessage: String? =
        if (!validateForce && text.isEmpty()) null else validateFn?.invoke(text)
    val customTextSelectionColors = TextSelectionColors(
        handleColor = MaterialTheme.colors.primary,
        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.4f)
    )
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(isEnabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(
        color = textColor,
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.zen_maru_gothic_regular))
    ))

    SideEffect {
        onInvalid?.invoke(errorMessage != null)
    }

    Column(modifier = modifier.alpha(if (isEnabled) 1f else 0.5f)) {
        if (label != null) {
            Text(
                text = stringResource(id = label),
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
            ) {
                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .then(
                            if (errorMessage != null) {
                                Modifier.border(
                                    width = 1.dp,
                                    color = MaterialTheme.colors.error,
                                    shape = RoundedCornerShape(10.dp)
                                )
                            } else {
                                Modifier
                            }
                        )
                ) {
                    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                        BasicTextField(
                            modifier = Modifier
                                .background(
                                    colors.backgroundColor(isEnabled).value,
                                    RoundedCornerShape(10.dp)
                                )
                                .defaultMinSize(minHeight = height.dp)
                                .fillMaxWidth()
                                .then(
                                    if (isFocus) {
                                        Modifier
                                            .onFocusChanged {
                                                hasFocus?.invoke(it.hasFocus)
                                                if (!it.hasFocus) validateAfter?.invoke()
                                            }
                                    } else Modifier
                                ),
                            value = text,
                            enabled = isEnabled,
                            onValueChange = onValueChange,
                            singleLine = isSingleLine,
                            visualTransformation = visualTransformation,
                            cursorBrush = SolidColor(MaterialTheme.colors.primary),
                            textStyle = mergedTextStyle,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = keyboardType,
                                imeAction = imeAction
                            ),
                            keyboardActions = keyboardActions,
                            decorationBox = { innerTextField ->
                                TextFieldDefaults.TextFieldDecorationBox(
                                    value = text,
                                    visualTransformation = VisualTransformation.None,
                                    innerTextField = innerTextField,
                                    placeholder = {
                                        placeholder?.let {
                                            Text(
                                                text = it,
                                                style = MaterialTheme.typography.h6,
                                                color = MaterialTheme.colors.onBackground
                                            )
                                        }
                                    },
                                    label = null,
                                    leadingIcon = null,
                                    trailingIcon = null,
                                    singleLine = isSingleLine,
                                    enabled = isEnabled,
                                    isError = false,
                                    interactionSource = remember { MutableInteractionSource() },
                                    colors = colors
                                )
                            }
                        )
                    }
                }
            }
            if (isButton) {
                Spacer(modifier = Modifier.width(16.dp))
                RegisterButton(
                    iconPainter = iconPainter,
                    iconTint = iconTint,
                ) {
                    if (onClickButton != null) {
                        onClickButton()
                    }
                }
            }
        }
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.h4
            )
        }
    }
}

@Composable
fun DataField(
    text: String,
    @StringRes label: Int,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    iconTint: Color = MaterialTheme.colors.primary,
    onIconClick: () -> Unit
) {
    Column(modifier = modifier.alpha(if (isEnabled) 1f else 0.5f)) {
        Text(
            text = stringResource(id = label),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = MaterialTheme.colors.onSurface.copy(alpha = TextFieldDefaults.BackgroundOpacity),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .then(if (isEnabled) Modifier.clickable { onIconClick() } else Modifier),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = icon),
                tint = iconTint,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}