package eu.project.design_system.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import eu.project.design_system.ContentDescription
import eu.project.design_system.TestTag
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class OutlinedButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()



//- State renders correct content ------------------------------------------------------------------

    @Test
    fun whenStateIsEnabled_labelIsDisplayed() {
        composeTestRule.setContent {
            OutlinedButton(
                onClick = {},
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = ButtonState.Enabled,
                testTag = "test_outlined_button"
            )
        }

        composeTestRule
            .onNodeWithText("Outlined")
            .assertIsDisplayed()
    }

    @Test
    fun whenStateIsDisabled_labelIsDisplayed() {
        composeTestRule.setContent {
            OutlinedButton(
                onClick = {},
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = ButtonState.Disabled,
                testTag = "test_outlined_button"
            )
        }

        composeTestRule
            .onNodeWithText("Outlined")
            .assertIsDisplayed()
    }

    @Test
    fun whenStateIsLoading_loadingIndicatorIsDisplayed() {
        composeTestRule.setContent {
            OutlinedButton(
                onClick = {},
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = ButtonState.Loading,
                testTag = "test_outlined_button"
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.Component.loadingIndicator("test_outlined_button"))
            .assertIsDisplayed()
    }

    @Test
    fun whenStateIsError_errorIconIsDisplayed() {
        composeTestRule.setContent {
            OutlinedButton(
                onClick = {},
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = ButtonState.Error,
                testTag = "test_outlined_button"
            )
        }

        composeTestRule
            .onNodeWithContentDescription(ContentDescription.Icon.ERROR)
            .assertIsDisplayed()
    }

    @Test
    fun whenStateIsSuccess_successIconIsDisplayed() {
        composeTestRule.setContent {
            OutlinedButton(
                onClick = {},
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = ButtonState.Success,
                testTag = "test_outlined_button"
            )
        }

        composeTestRule
            .onNodeWithContentDescription(ContentDescription.Icon.SUCCESS)
            .assertIsDisplayed()
    }



//- Enabled policy ---------------------------------------------------------------------------------

    @Test
    fun whenStateIsEnabled_buttonIsEnabled() {
        composeTestRule.setContent {
            OutlinedButton(
                onClick = {},
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = ButtonState.Enabled,
                testTag = "test_outlined_button"
            )
        }

        composeTestRule
            .onNodeWithTag("test_outlined_button")
            .assertIsEnabled()
    }



//- Click behavior ---------------------------------------------------------------------------------

    @Test
    fun whenStateIsEnabled_onClickIsInvoked() {
        var clicked = false

        composeTestRule.setContent {
            OutlinedButton(
                onClick = { clicked = true },
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = ButtonState.Enabled,
                testTag = "test_outlined_button"
            )
        }

        composeTestRule
            .onNodeWithTag("test_outlined_button")
            .performClick()

        assertTrue(clicked)
    }

    @Test
    fun whenStateIsNotEnabled_onClickIsNotInvoked() {
        val nonEnabledStates = listOf(
            ButtonState.Disabled,
            ButtonState.Loading,
            ButtonState.Error,
            ButtonState.Success
        )

        var currentState by mutableStateOf(nonEnabledStates.first())
        var clicked = false

        composeTestRule.setContent {
            OutlinedButton(
                onClick = { clicked = true },
                label = "Outlined",
                type = OutlinedButtonType.Secondary,
                size = ButtonSize.Medium,
                state = currentState,
                testTag = "test_outlined_button"
            )
        }

        nonEnabledStates.forEach { state ->
            clicked = false
            currentState = state

            composeTestRule
                .onNodeWithTag("test_outlined_button")
                .performClick()

            assertFalse("onClick should not fire in state $state", clicked)
        }
    }



//- Width behavior ---------------------------------------------------------------------------------

    @Test
    fun whenFullWidthIsTrue_buttonFillsMaxWidth() {
        composeTestRule.setContent {
            Box(modifier = Modifier.width(300.dp)) {
                OutlinedButton(
                    onClick = {},
                    label = "Outlined",
                    type = OutlinedButtonType.Secondary,
                    size = ButtonSize.Medium,
                    state = ButtonState.Enabled,
                    testTag = "test_outlined_button",
                    fullWidth = true
                )
            }
        }

        composeTestRule
            .onNodeWithTag("test_outlined_button")
            .assertWidthIsEqualTo(300.dp)
    }
}