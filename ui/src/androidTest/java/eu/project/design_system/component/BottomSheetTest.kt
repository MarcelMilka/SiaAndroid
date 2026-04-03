package eu.project.design_system.component

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class BottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testTag = "test_bottom_sheet"
    private val testContentTag = "test_bottom_sheet_content"
    private val testData = "Test Content"



//- Visibility -------------------------------------------------------------------------------------

    @Test
    fun whenStateIsShown_contentIsDisplayed() {
        composeTestRule.setContent {
            BottomSheet(
                state = BottomSheetState.Shown(testData),
                onDismissRequest = {},
                testTag = testTag
            ) { data ->
                Text(
                    text = data,
                    modifier = Modifier.testTag(testContentTag)
                )
            }
        }

        composeTestRule.onNodeWithTag(testContentTag).assertIsDisplayed()
        composeTestRule.onNodeWithText(testData).assertIsDisplayed()
    }



//- Dismiss behavior -------------------------------------------------------------------------------

    @SuppressLint("CheckResult")
    @Test
    fun whenDismissed_onDismissRequestIsInvoked() {
        var dismissed = false

        composeTestRule.setContent {
            BottomSheet(
                state = BottomSheetState.Shown(testData),
                onDismissRequest = { dismissed = true },
                testTag = testTag
            ) { data ->
                Text(text = data)
            }
        }

        composeTestRule.onNodeWithTag(testTag).performTouchInput { this.swipeDown() }

        composeTestRule.runOnIdle {
            assertTrue(dismissed)
        }
    }



//- Data passing -----------------------------------------------------------------------------------

    @Test
    fun whenStateIsShownWithData_dataIsPassedToContent() {
        val specificData = "Specific Test Data"
        var receivedData: String? = null

        composeTestRule.setContent {
            BottomSheet(
                state = BottomSheetState.Shown(specificData),
                onDismissRequest = {},
                testTag = testTag
            ) { data ->
                receivedData = data
                Text(text = data)
            }
        }

        composeTestRule.runOnIdle {
            assertEquals(specificData, receivedData)
        }
    }



//- State transitions ------------------------------------------------------------------------------

    @Test
    fun whenStateChangesFromHiddenToShown_contentBecomesVisible() {
        var state: BottomSheetState<String> by mutableStateOf(BottomSheetState.Hidden)

        composeTestRule.setContent {
            BottomSheet(
                state = state,
                onDismissRequest = {},
                testTag = testTag
            ) { data ->
                Text(
                    text = data,
                    modifier = Modifier.testTag(testContentTag)
                )
            }
        }

        composeTestRule.onNodeWithTag(testContentTag).assertDoesNotExist()

        state = BottomSheetState.Shown(testData)

        composeTestRule.onNodeWithTag(testContentTag).assertIsDisplayed()
    }

    @Test
    fun whenStateChangesFromShownToHidden_contentBecomesInvisible() {
        var state: BottomSheetState<String> by mutableStateOf(BottomSheetState.Shown(testData))

        composeTestRule.setContent {
            BottomSheet(
                state = state,
                onDismissRequest = {},
                testTag = testTag
            ) { data ->
                Text(
                    text = data,
                    modifier = Modifier.testTag(testContentTag)
                )
            }
        }

        composeTestRule.onNodeWithTag(testContentTag).assertIsDisplayed()

        state = BottomSheetState.Hidden

        composeTestRule.onNodeWithTag(testTag).assertDoesNotExist()
        composeTestRule.onNodeWithTag(testContentTag).assertDoesNotExist()
    }
}