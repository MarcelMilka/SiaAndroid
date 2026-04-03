package eu.project.design_system.component.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import eu.project.design_system.theme.Opacity
import eu.project.design_system.theme.SiaTheme

enum class TextButtonType { Primary, Secondary, Destructive }

@Composable
fun TextButton(
    onClick: () -> Unit,
    label: String,
    type: TextButtonType,
    size: ButtonSize,
    state: ButtonState,
    testTag: String,
    modifier: Modifier = Modifier,
    fullWidth: Boolean = false,
    leftAligned: Boolean = false
) {
    val contentColor = when (type) {
        TextButtonType.Primary -> SiaTheme.color.text.primary
        TextButtonType.Secondary -> SiaTheme.color.text.secondary
        TextButtonType.Destructive -> SiaTheme.color.text.destructive
    }

    TextButton(
        onClick = onClick,
        modifier = Modifier
            .height(buttonHeight(size))
            .then(if (fullWidth) Modifier.fillMaxWidth() else Modifier.wrapContentWidth())
            .testTag(testTag)
            .semantics { contentDescription = label }
            .then(modifier),
        enabled = state == ButtonState.Enabled,
        shape = buttonShape(size),
        contentPadding = buttonContentPadding(size),
        colors = ButtonDefaults.textButtonColors(
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