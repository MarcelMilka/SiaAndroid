package eu.project.design_system.component

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import eu.project.design_system.theme.SiaTheme

enum class CheckboxState { Selected, Unselected }

@Composable
fun Checkbox(
    state: CheckboxState,
    onToggle: () -> Unit,
    testTag: String
) {
    val isSelected = state == CheckboxState.Selected

    val checkboxColors = CheckboxDefaults.colors(
        checkedColor = SiaTheme.color.button.primary,
        uncheckedColor = SiaTheme.color.text.secondary,
        checkmarkColor = SiaTheme.color.text.onPrimary
    )

    Checkbox(
        modifier = Modifier
            .testTag(testTag),
        checked = isSelected,
        onCheckedChange = { onToggle() },
        colors = checkboxColors
    )
}