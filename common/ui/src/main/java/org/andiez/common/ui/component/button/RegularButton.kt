package org.andiez.common.ui.component.button

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.andiez.common.ui.component.button.BaseFilledButton
import org.andiez.common.ui.component.button.ButtonState
import org.andiez.common.ui.theme.ActivePrimary
import org.andiez.common.ui.theme.ActiveSecondary
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.common.ui.theme.InactivePrimary
import org.andiez.common.ui.theme.InactiveSecondary

@Composable
fun PrimaryRegularButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    buttonState: ButtonState = ButtonState.NORMAL,
) {
    BaseFilledButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (buttonState == ButtonState.DISABLED) InactivePrimary else ActivePrimary,
            contentColor = if (buttonState == ButtonState.DISABLED) InactiveSecondary else BackgroundPrimary,
        ),
        text = text,
        modifier = modifier,
        buttonState = buttonState,
    )
}

@Composable
fun TertiaryRegularButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    buttonState: ButtonState = ButtonState.NORMAL,
) {
    BaseFilledButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (buttonState == ButtonState.DISABLED) InactiveSecondary else ActiveSecondary,
            contentColor = if (buttonState == ButtonState.DISABLED) InactivePrimary else ActivePrimary,
        ),
        text = text,
        modifier = modifier,
        buttonState = buttonState,
    )
}