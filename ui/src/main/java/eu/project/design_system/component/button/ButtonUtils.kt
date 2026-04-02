package eu.project.design_system.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import eu.project.design_system.theme.Radius
import eu.project.design_system.theme.SiaTypography
import eu.project.design_system.theme.Space

enum class ButtonSize { Medium, Small }
enum class ButtonState { Enabled, Disabled, Loading, Error, Success }

internal fun buttonShape(size: ButtonSize): RoundedCornerShape = when (size) {
    ButtonSize.Medium -> RoundedCornerShape(Radius.R16.value)
    ButtonSize.Small -> RoundedCornerShape(Radius.R12.value)
}

internal fun buttonHeight(size: ButtonSize): Dp = when (size) {
    ButtonSize.Medium -> 56.dp
    ButtonSize.Small -> 40.dp
}

internal fun buttonTextStyle(size: ButtonSize): TextStyle = when (size) {
    ButtonSize.Medium -> SiaTypography.titleMedium
    ButtonSize.Small -> SiaTypography.labelLarge
}

internal fun buttonContentPadding(size: ButtonSize): PaddingValues = when (size) {
    ButtonSize.Medium -> PaddingValues(horizontal = Space.S24.value, vertical = Space.S16.value)
    ButtonSize.Small -> PaddingValues(horizontal = Space.S16.value, vertical = Space.S8.value)
}