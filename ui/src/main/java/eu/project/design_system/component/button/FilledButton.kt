package eu.project.design_system.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import eu.project.design_system.TestTag
import eu.project.design_system.theme.Opacity
import eu.project.design_system.theme.SiaTheme

/**
 * A filled button with support for multiple visual types, sizes, and interactive states.
 *
 * ## State → enabled mapping
 * | State    | Clickable |
 * |----------|-----------|
 * | Enabled  | ✅        |
 * | Disabled | ❌        |
 * | Loading  | ❌        |
 * | Error    | ❌        |
 * | Success  | ❌        |
 *
 * @param testTag Required for UI testing. See [TestTag.Component].
 */

enum class FilledButtonType { Primary, Secondary }

@Composable
fun FilledButton(
    onClick: () -> Unit,
    label: String,
    type: FilledButtonType,
    size: ButtonSize,
    state: ButtonState,
    testTag: String,
    modifier: Modifier = Modifier,
    fullWidth: Boolean = false
) {
    val isEnabled = state == ButtonState.Enabled

    Button(
        onClick = onClick,
        modifier = Modifier
            .height(buttonHeight(size))
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth())
            .testTag(testTag)
            .semantics { contentDescription = label }
            .then(modifier),
        enabled = isEnabled,
        shape = buttonShape(size),
        border = when (type) {
            FilledButtonType.Primary -> null
            FilledButtonType.Secondary -> BorderStroke(
                width = 1.dp,
                color = SiaTheme.color.border.regular
            )
        },
        contentPadding = buttonContentPadding(size),
        colors = ButtonDefaults.buttonColors(
            containerColor = when (type) {
                FilledButtonType.Primary -> SiaTheme.color.button.primary
                FilledButtonType.Secondary -> SiaTheme.color.button.secondary
            },
            contentColor = when (type) {
                FilledButtonType.Primary -> SiaTheme.color.text.onPrimary
                FilledButtonType.Secondary -> SiaTheme.color.text.onSecondary
            },
            disabledContainerColor = when (type) {
                FilledButtonType.Primary -> SiaTheme.color.button.primary.copy(alpha = Opacity.Disabled.value)
                FilledButtonType.Secondary -> SiaTheme.color.button.secondary.copy(alpha = Opacity.Disabled.value)
            },
            disabledContentColor = when (type) {
                FilledButtonType.Primary -> SiaTheme.color.text.onPrimary.copy(alpha = Opacity.Disabled.value)
                FilledButtonType.Secondary -> SiaTheme.color.text.onSecondary.copy(alpha = Opacity.Disabled.value)
            }
        )
    ) {
        ButtonAnimatedContent(
            label = label,
            state = state,
            size = size,
            testTag = testTag
        )
    }
}