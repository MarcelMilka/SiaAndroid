package eu.project.design_system.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import eu.project.design_system.theme.Space

@Composable
fun SplitContent(
    testTag: String,
    upperVerticalArrangement: Arrangement.Vertical,
    lowerVerticalArrangement: Arrangement.Vertical,
    upperContent: @Composable () -> Unit,
    lowerContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(testTag),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Space.S16.value,
                    vertical = Space.S24.value
                )
                .weight(1f),
            verticalArrangement = upperVerticalArrangement,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            upperContent()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Space.S16.value,
                    vertical = Space.S24.value
                )
                .weight(1f),
            verticalArrangement = lowerVerticalArrangement,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            lowerContent()
        }
    }
}