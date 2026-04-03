package eu.project.sia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.project.auth.authn.AuthnManager
import eu.project.common.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Manages application startup logic. Attempts to restore user session via AuthnManager, determines authentication
 * status, and sets the appropriate start destination (authenticated/unauthenticated route or initialization error screen).
 */
@HiltViewModel
internal class ApplicationViewModel @Inject constructor(
    private val authnManager: AuthnManager
): ViewModel() {

    private var _applicationStartupState =
        MutableStateFlow<ApplicationStartupState>(ApplicationStartupState.Pending)

    val applicationStartupState = _applicationStartupState.asStateFlow()

    init { setStartupState() }

    private fun setStartupState() {
        viewModelScope.launch {

            runCatching {
                authnManager.restoreSession()

                val isSignedIn = authnManager.isSignedIn()

                val startDestination = when(isSignedIn) {
                    true -> Navigation.Authenticated.RouteAuthenticated
                    false -> Navigation.Unauthenticated.RouteUnauthenticated
                }

                _applicationStartupState.update {
                    ApplicationStartupState.Ready(startDestination)
                }
            }
                .onFailure {
                    _applicationStartupState.update {
                        ApplicationStartupState.Ready(
                            Navigation.InitializationErrorScreen
                        )
                    }
                }
        }
    }
}