package eu.project.design_system.component

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CheckboxTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testTag = "test_checkbox"



//- State renders correct content ------------------------------------------------------------------

    @Test
    fun whenStateIsSelected_checkboxIsOn() {
        composeTestRule.setContent {
            Checkbox(
                state = CheckboxState.Selected,
                onToggle = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(testTag)
            .assertIsOn()
    }

    @Test
    fun whenStateIsUnselected_checkboxIsOff() {
        composeTestRule.setContent {
            Checkbox(
                state = CheckboxState.Unselected,
                onToggle = {},
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(testTag)
            .assertIsOff()
    }



//- Click behavior ---------------------------------------------------------------------------------

    @Test
    fun whenClicked_onToggleIsInvoked() {
        var toggled = false

        composeTestRule.setContent {
            Checkbox(
                state = CheckboxState.Unselected,
                onToggle = { toggled = true },
                testTag = testTag
            )
        }

        composeTestRule
            .onNodeWithTag(testTag)
            .performClick()

        assertTrue(toggled)
    }
}