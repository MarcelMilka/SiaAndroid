package eu.project.authenticate.welcomeScreen.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.project.auth.authn.AuthnManager
import eu.project.auth.result.SignInWithGoogleResult
import eu.project.authenticate.welcomeScreen.intent.WelcomeScreenIntent
import eu.project.authenticate.welcomeScreen.state.WelcomeScreenState
import eu.project.common.crashlytics.CrashlyticsManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
internal class WelcomeScreenViewModel @Inject constructor(
    private val authnManager: AuthnManager,
    private val crashlyticsManager: CrashlyticsManager
): ViewModel() {

    private val _screenState = MutableStateFlow<WelcomeScreenState>(WelcomeScreenState.Idle)
    val screenState = _screenState.asStateFlow()

    fun handleIntent(intent: WelcomeScreenIntent) {
        when (intent) {
            WelcomeScreenIntent.ClickContinueWithGoogle -> handleContinueWithGoogle()
        }
    }

    private fun handleContinueWithGoogle() {
        viewModelScope.launch {

            _screenState.update { WelcomeScreenState.Pending }

            val result = withContext(Dispatchers.IO) {
                authnManager.signInWithGoogle()
            }

            val state = when(result) {
                SignInWithGoogleResult.Success -> {
                    WelcomeScreenState.Success
                }
                SignInWithGoogleResult.Failure.Cancelled -> {
                    WelcomeScreenState.Idle
                }
                is SignInWithGoogleResult.Failure.Unknown -> {
                    crashlyticsManager.recordException(result.throwable)
                    WelcomeScreenState.Idle
                }
            }
            _screenState.update { state }
        }
    }
}