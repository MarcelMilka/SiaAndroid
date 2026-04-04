package eu.project.design_system.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import eu.project.design_system.theme.SiaTheme

@Composable
fun ContentHolder(
    testTag: String,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SiaTheme.color.surface.background)
            .testTag(testTag)
    ) {
        content()
    }
}