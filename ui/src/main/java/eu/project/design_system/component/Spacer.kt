package eu.project.design_system.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import eu.project.design_system.theme.Space

@Composable
fun HorizontalSpacer(space: Space) {
    Spacer(modifier = Modifier.width(space.value))
}

@Composable
fun VerticalSpacer(space: Space) {
    Spacer(modifier = Modifier.height(space.value))
}