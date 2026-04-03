package eu.project.design_system.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import eu.project.design_system.TestTag
import eu.project.design_system.component.button.ButtonState
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testTag = "test_dialog"
    private val headline = "Delete Item"
    private val supportingText = "Are you sure you want to delete this item?"
    private val confirmLabel = "Delete"
    private val dismissLabel = "Cancel"



//- Visibility -------------------------------------------------------------------------------------

    @Test
    fun whenStateIsVisible_contentIsDisplayed() {
        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule.onNodeWithText(headline).assertIsDisplayed()
        composeTestRule.onNodeWithText(supportingText).assertIsDisplayed()
        composeTestRule.onNodeWithText(confirmLabel).assertIsDisplayed()
        composeTestRule.onNodeWithText(dismissLabel).assertIsDisplayed()
    }

    @Test
    fun whenStateIsHidden_contentIsNotDisplayed() {
        composeTestRule.setContent {
            Dialog(
                state = DialogState.Hidden,
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule.onNodeWithText(headline).assertDoesNotExist()
        composeTestRule.onNodeWithText(supportingText).assertDoesNotExist()
        composeTestRule.onNodeWithText(confirmLabel).assertDoesNotExist()
        composeTestRule.onNodeWithText(dismissLabel).assertDoesNotExist()
    }



//- Click behavior ---------------------------------------------------------------------------------

    @Test
    fun whenConfirmClicked_onConfirmIsInvoked() {
        var confirmed = false

        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = { confirmed = true },
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogConfirmButton(testTag))
            .performClick()

        Assert.assertTrue(confirmed)
    }

    @Test
    fun whenDismissClicked_onDismissIsInvoked() {
        var dismissed = false

        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = { dismissed = true },
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogDismissButton(testTag))
            .performClick()

        Assert.assertTrue(dismissed)
    }



//- Dismissable ------------------------------------------------------------------------------------

    @Test
    fun whenDismissable_onDismissRequestInvokesOnDismiss() {
        var dismissed = false

        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(dismissable = true),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = { dismissed = true },
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogDismissButton(testTag))
            .performClick()

        Assert.assertTrue(dismissed)
    }



//- Button states ----------------------------------------------------------------------------------

    @Test
    fun whenConfirmButtonStateIsEnabled_confirmButtonIsEnabled() {
        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(confirmButtonState = ButtonState.Enabled),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogConfirmButton(testTag))
            .assertIsEnabled()
    }

    @Test
    fun whenConfirmButtonStateIsDisabled_confirmButtonIsNotEnabled() {
        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(confirmButtonState = ButtonState.Disabled),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogConfirmButton(testTag))
            .assertIsNotEnabled()
    }

    @Test
    fun whenConfirmButtonStateIsDisabled_onConfirmIsNotInvoked() {
        var confirmed = false

        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(confirmButtonState = ButtonState.Disabled),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = { confirmed = true },
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogConfirmButton(testTag))
            .performClick()

        Assert.assertFalse(confirmed)
    }

    @Test
    fun whenDismissButtonStateIsEnabled_dismissButtonIsEnabled() {
        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(dismissButtonState = ButtonState.Enabled),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogDismissButton(testTag))
            .assertIsEnabled()
    }

    @Test
    fun whenDismissButtonStateIsDisabled_dismissButtonIsNotEnabled() {
        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(dismissButtonState = ButtonState.Disabled),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = {},
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogDismissButton(testTag))
            .assertIsNotEnabled()
    }

    @Test
    fun whenDismissButtonStateIsDisabled_onDismissIsNotInvoked() {
        var dismissed = false

        composeTestRule.setContent {
            Dialog(
                state = DialogState.Visible(dismissButtonState = ButtonState.Disabled),
                headline = headline,
                supportingText = supportingText,
                dismissButtonLabel = dismissLabel,
                onDismiss = { dismissed = true },
                confirmButtonLabel = confirmLabel,
                onConfirm = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.dialogDismissButton(testTag))
            .performClick()

        Assert.assertFalse(dismissed)
    }
}