package eu.project.scaffold

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import eu.project.authenticate.welcomeScreen.impl.welcomeImpl
import eu.project.common.navigation.Navigation
import eu.project.design_system.theme.SiaTheme
import eu.project.floatingActionButton.impl.floatingActionButtonImpl
import eu.project.home.impl.homeImpl
import eu.project.saved.exportResult.impl.exportResultImpl
import eu.project.saved.exportWords.impl.exportWordsImpl
import eu.project.saved.savedWords.impl.savedWordsImpl
import eu.project.topBar.impl.topBarImpl
import eu.project.transcribe.selectAudio.impl.selectAudioImpl

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ApplicationScaffold(startRoute: Navigation) {

    val controller = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = SiaTheme.color.surface.background,
        contentWindowInsets = WindowInsets.statusBars,
        floatingActionButtonPosition = FabPosition.Center,
        topBar = {
            topBarImpl(controller)
        },
        floatingActionButton = {
            floatingActionButtonImpl(controller)
        },
        content = { paddingValues ->

            NavHost(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding()
                ),
                navController = controller,
                startDestination = startRoute,
                builder = {

                    this.navigation<Navigation.Unauthenticated.RouteUnauthenticated>(
                        startDestination = Navigation.Unauthenticated.WelcomeScreen
                    ) {
                        this.welcomeImpl(controller)
                    }

                    this.navigation<Navigation.Authenticated.RouteAuthenticated>(
                        startDestination = Navigation.Authenticated.HomeScreen
                    ) {
                        this.homeImpl(controller)
                    }

                    this.composable<Navigation.InitializationErrorScreen> {}









                    this.navigation<Navigation.Saved.RouteSaved>(startDestination = Navigation.Saved.SavedWordsScreen) {

                        this.savedWordsImpl(controller)

                        this.exportWordsImpl(controller)

                        this.exportResultImpl(controller)
                    }

                    this.navigation<Navigation.Transcribe.RouteTranscribe>(startDestination = Navigation.Transcribe.SelectAudioScreen) {

                        this.selectAudioImpl()
                    }
                }
            )
        }
    )
}