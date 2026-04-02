package eu.project.design_system.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import eu.project.design_system.TestTag
import eu.project.design_system.component.button.ButtonSize
import eu.project.design_system.component.button.ButtonState
import eu.project.design_system.component.button.TextButton
import eu.project.design_system.component.button.TextButtonType
import eu.project.design_system.theme.SiaTheme

sealed class DialogState {
    data object Hidden: DialogState()
    data class Visible(
        val dismissable: Boolean = true,
        val confirmButtonState: ButtonState = ButtonState.Enabled,
        val dismissButtonState: ButtonState = ButtonState.Enabled
    ): DialogState()
}

@Composable
fun Dialog(
    state: DialogState,

    headline: String,
    supportingText: String,

    dismissButtonLabel: String,
    onDismiss: () -> Unit,

    confirmButtonLabel: String,
    onConfirm: () -> Unit,

    testTag: String
) {
    if (state is DialogState.Visible) {
        AlertDialog(
            containerColor = SiaTheme.color.surface.background,
            onDismissRequest = { if (state.dismissable) { onDismiss() } },
            title = {
                Text(
                    text = headline,
                    style = SiaTheme.typography.headlineSmall,
                    color = SiaTheme.color.text.primary
                )
            },
            text = {
                Text(
                    text = supportingText,
                    style = SiaTheme.typography.bodyMedium,
                    color = SiaTheme.color.text.secondary
                )
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirm,
                    label = confirmButtonLabel,
                    type = TextButtonType.Destructive,
                    size = ButtonSize.Small,
                    state = state.confirmButtonState,
                    testTag = TestTag.Component.dialogConfirmButton(testTag),
                    fullWidth = true
                )
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss,
                    label = dismissButtonLabel,
                    type = TextButtonType.Primary,
                    size = ButtonSize.Small,
                    state = state.dismissButtonState,
                    testTag = TestTag.Component.dialogDismissButton(testTag),
                    fullWidth = true
                )
            }
        )
    }
}