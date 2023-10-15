package org.andiez.common.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.andiez.common.ui.R

val fonts = FontFamily(
    Font(R.font.poppins_bold),
    Font(R.font.poppins_bold, weight = FontWeight.Bold),
    Font(R.font.poppins_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.poppins_medium, weight = FontWeight.Medium),
    Font(R.font.poppins_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
)