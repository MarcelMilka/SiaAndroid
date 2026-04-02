package eu.project.design_system.component.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class IconButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testIcon = Icons.Default.Add
    private val testLabel = "Add Item"
    private val testTag = "test_icon_button"



//- State renders correct content ------------------------------------------------------------------

    @Test
    fun whenStateIsEnabled_iconIsDisplayed() {
        composeTestRule.setContent {
            IconButton(
                onClick = {},
                icon = testIcon,
                label = testLabel,
                type = IconButtonType.Primary,
                state = IconButtonState.Enabled,
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithContentDescription(testLabel)
            .assertIsDisplayed()
    }

    @Test
    fun whenStateIsDisabled_iconIsDisplayed() {
        composeTestRule.setContent {
            IconButton(
                onClick = {},
                icon = testIcon,
                label = testLabel,
                type = IconButtonType.Primary,
                state = IconButtonState.Disabled,
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithContentDescription(testLabel)
            .assertIsDisplayed()
    }



//- Enabled policy ---------------------------------------------------------------------------------

    @Test
    fun whenStateIsEnabled_buttonIsEnabled() {
        composeTestRule.setContent {
            IconButton(
                onClick = {},
                icon = testIcon,
                label = testLabel,
                type = IconButtonType.Primary,
                state = IconButtonState.Enabled,
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(testTag)
            .assertIsEnabled()
    }



//- Click behavior ---------------------------------------------------------------------------------

    @Test
    fun whenStateIsEnabled_onClickIsInvoked() {
        var clicked = false

        composeTestRule.setContent {
            IconButton(
                onClick = { clicked = true },
                icon = testIcon,
                label = testLabel,
                type = IconButtonType.Primary,
                state = IconButtonState.Enabled,
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(testTag)
            .performClick()

        assertTrue(clicked)
    }

    @Test
    fun whenStateIsDisabled_onClickIsNotInvoked() {
        var clicked = false

        composeTestRule.setContent {
            IconButton(
                onClick = { clicked = true },
                icon = testIcon,
                label = testLabel,
                type = IconButtonType.Primary,
                state = IconButtonState.Disabled,
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(testTag)
            .performClick()

        assertFalse("onClick should not fire when state is Disabled", clicked)
    }
}