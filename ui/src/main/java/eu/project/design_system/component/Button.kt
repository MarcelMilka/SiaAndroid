package eu.project.design_system.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import eu.project.design_system.theme.Opacity
import eu.project.design_system.theme.Radius
import eu.project.design_system.theme.SiaTheme
import eu.project.design_system.theme.SiaTypography

enum class FilledButtonType { Primary, Secondary }
enum class ButtonSize { Medium, Small }
enum class ButtonState { Enabled, Disabled, Loading, Error, Success }

@Composable
fun FilledButton(
    onClick: () -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    type: FilledButtonType,
    size: ButtonSize,
    state: ButtonState,
    fullWidth: Boolean = false,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(buttonHeight(size))
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth()),
        enabled = state == ButtonState.Enabled,
        shape = buttonShape(size),
        border = BorderStroke(
            color = when(type) {
                FilledButtonType.Primary -> Transparent
                FilledButtonType.Secondary -> SiaTheme.color.border.regular
                               },
            width = when(type) {
                FilledButtonType.Primary -> 0.dp
                FilledButtonType.Secondary -> 1.dp
            }
        ),

        colors = ButtonColors(
            containerColor = when(type) {
                FilledButtonType.Primary -> SiaTheme.color.button.primary
                FilledButtonType.Secondary -> SiaTheme.color.button.secondary
            },
            contentColor = when(type) {
                FilledButtonType.Primary -> SiaTheme.color.text.onPrimary
                FilledButtonType.Secondary -> SiaTheme.color.text.onSecondary
            },
            disabledContainerColor = when(type) {
                FilledButtonType.Primary -> SiaTheme.color.button.primary.copy(alpha = Opacity.Disabled.value)
                FilledButtonType.Secondary -> SiaTheme.color.button.secondary.copy(alpha = Opacity.Disabled.value)
            },
            disabledContentColor = when(type) {
                FilledButtonType.Primary -> SiaTheme.color.text.onPrimary.copy(alpha = Opacity.Disabled.value)
                FilledButtonType.Secondary -> SiaTheme.color.text.onSecondary.copy(alpha = Opacity.Disabled.value)
            }
        )
    ) {
        AnimatedContent(targetState = state) {
            when (it) {
                ButtonState.Enabled, ButtonState.Disabled -> {
                    Text(
                        text = label,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = buttonTextStyle(size)
                    )
                }
                ButtonState.Loading -> {
                    LoadingIndicator(modifier = Modifier.size(20.dp), type = LoadingIndicatorType.Secondary)
                }
                ButtonState.Error -> {
                    Icon(
                        imageVector = Icons.Rounded.Error,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
                ButtonState.Success -> {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

private fun buttonShape(buttonSize: ButtonSize): RoundedCornerShape {
    return when (buttonSize) {
        ButtonSize.Medium -> RoundedCornerShape(Radius.R16.value)
        ButtonSize.Small -> RoundedCornerShape(Radius.R12.value)
    }
}

private fun buttonHeight(buttonSize: ButtonSize): Dp {
    return when (buttonSize) {
        ButtonSize.Medium -> 56.dp
        ButtonSize.Small -> 40.dp
    }
}

@Composable
private fun buttonTextStyle(buttonSize: ButtonSize): TextStyle {
    return when (buttonSize) {
        ButtonSize.Medium -> SiaTypography.titleMedium
        ButtonSize.Small -> SiaTypography.labelLarge
    }
}