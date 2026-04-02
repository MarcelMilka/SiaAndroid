package eu.project.design_system.component.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import eu.project.design_system.ContentDescription
import eu.project.design_system.TestTag
import eu.project.design_system.component.LoadingIndicator
import eu.project.design_system.component.LoadingIndicatorType
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

@Composable
internal fun ButtonAnimatedContent(
    label: String,
    state: ButtonState,
    size: ButtonSize,
    testTag: String,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        AnimatedContent(
            targetState = state,
            label = TestTag.Component.animatedContent(testTag)
        ) { currentState ->
            when (currentState) {
                ButtonState.Enabled, ButtonState.Disabled -> {
                    Text(
                        text = label,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = buttonTextStyle(size)
                    )
                }
                ButtonState.Loading -> {
                    LoadingIndicator(
                        modifier = Modifier.size(20.dp),
                        type = LoadingIndicatorType.Secondary,
                        testTag = TestTag.Component.loadingIndicator(testTag)
                    )
                }
                ButtonState.Error -> {
                    Icon(
                        imageVector = Icons.Rounded.Error,
                        contentDescription = ContentDescription.Icon.ERROR,
                        modifier = Modifier.size(20.dp)
                    )
                }
                ButtonState.Success -> {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = ContentDescription.Icon.SUCCESS,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}