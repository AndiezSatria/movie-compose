package org.andiez.common.ui.component.button

import androidx.annotation.DrawableRes
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.andiez.common.ui.theme.BackgroundPrimary
import org.andiez.common.ui.theme.ContentPrimary

/**
 * Created by AndiezSatria on 02/10/2023.
 */

@Composable
fun DropDownButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    onClick: () -> Unit,
) {
    BaseSideOutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BackgroundPrimary,
            contentColor = ContentPrimary,
        ),
        text = text,
        borderColor = ContentPrimary,
        modifier = modifier,
        icon = icon,
    )
}