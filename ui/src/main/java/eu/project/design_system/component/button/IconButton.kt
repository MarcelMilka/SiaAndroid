package eu.project.design_system.component.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import eu.project.design_system.theme.Opacity
import eu.project.design_system.theme.SiaTheme

enum class IconButtonType { Primary, Secondary, Destructive }
enum class IconButtonState { Enabled, Disabled }

@Composable
fun IconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    type: IconButtonType,
    state: IconButtonState,
    testTag: String,
    modifier: Modifier = Modifier
) {
    val contentColor = when (type) {
        IconButtonType.Primary -> SiaTheme.color.text.primary
        IconButtonType.Secondary -> SiaTheme.color.text.secondary
        IconButtonType.Destructive -> SiaTheme.color.text.destructive
    }

    IconButton(
        onClick = onClick,
        modifier = modifier
            .testTag(testTag)
            .semantics { contentDescription = label },
        enabled = state == IconButtonState.Enabled,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = contentColor,
            disabledContentColor = contentColor.copy(alpha = Opacity.Disabled.value)
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}