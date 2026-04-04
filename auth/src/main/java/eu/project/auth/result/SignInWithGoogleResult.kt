package eu.project.auth.result

sealed interface SignInWithGoogleResult {
    data object Success: SignInWithGoogleResult
    sealed interface Failure: SignInWithGoogleResult {
        data object Cancelled: Failure
        data class Unknown(val throwable: Throwable): Failure
    }
}