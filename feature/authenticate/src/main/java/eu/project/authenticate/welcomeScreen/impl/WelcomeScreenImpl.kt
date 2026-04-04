package eu.project.authenticate.welcomeScreen.impl

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import eu.project.authenticate.welcomeScreen.screen.WelcomeScreen
import eu.project.authenticate.welcomeScreen.state.WelcomeScreenState
import eu.project.authenticate.welcomeScreen.vm.WelcomeScreenViewModel
import eu.project.common.navigation.Navigation

fun NavGraphBuilder.welcomeImpl(controller: NavHostController) {
    composable<Navigation.Unauthenticated.WelcomeScreen> {

        val viewModel = hiltViewModel<WelcomeScreenViewModel>()
        val state by viewModel.screenState.collectAsStateWithLifecycle()

        LaunchedEffect(state) {
            if (state is WelcomeScreenState.Success) {
                controller.navigate(Navigation.Authenticated.RouteAuthenticated) {
                    popUpTo(Navigation.Unauthenticated.RouteUnauthenticated) { inclusive = true }
                }
            }
        }

        WelcomeScreen(
            state = state,
            handleIntent = { intent ->
                viewModel.handleIntent(intent)
            }
        )
    }
}