package eu.project.design_system.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import eu.project.design_system.theme.Opacity
import eu.project.design_system.theme.SiaTheme

enum class OutlinedButtonType { Secondary, Destructive }

@Composable
fun OutlinedButton(
    onClick: () -> Unit,
    label: String,
    type: OutlinedButtonType,
    size: ButtonSize,
    state: ButtonState,
    testTag: String,
    modifier: Modifier = Modifier,
    fullWidth: Boolean = false,
    leftAligned: Boolean = false
) {
    val contentColor = when (type) {
        OutlinedButtonType.Secondary -> SiaTheme.color.text.secondary
        OutlinedButtonType.Destructive -> SiaTheme.color.text.destructive
    }

    val borderColor = when (type) {
        OutlinedButtonType.Secondary -> SiaTheme.color.border.regular
        OutlinedButtonType.Destructive -> SiaTheme.color.border.destructive
    }

    val isFullWidth = fullWidth || leftAligned

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .height(buttonHeight(size))
            .then(if (isFullWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth())
            .testTag(testTag)
            .semantics { contentDescription = label }
            .then(modifier),
        enabled = state == ButtonState.Enabled,
        shape = buttonShape(size),
        contentPadding = buttonContentPadding(size),
        border = BorderStroke(
            width = 1.dp,
            color = if (state == ButtonState.Enabled) borderColor else borderColor.copy(alpha = Opacity.Disabled.value)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
            disabledContentColor = contentColor.copy(alpha = Opacity.Disabled.value)
        )
    ) {
        ButtonAnimatedContent(
            label = label,
            state = state,
            size = size,
            testTag = testTag,
            horizontalArrangement = if (leftAligned) Arrangement.Start else Arrangement.Center
        )
    }
}