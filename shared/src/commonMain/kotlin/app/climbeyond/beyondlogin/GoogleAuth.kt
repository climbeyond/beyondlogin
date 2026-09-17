package app.climbeyond.beyondlogin

internal sealed class GoogleSignInResult {
    data class Success(val idToken: String, val nonce: String) : GoogleSignInResult()
    data object Cancelled : GoogleSignInResult()
    data class Error(val message: String) : GoogleSignInResult()
}

/**
 * Obtains a Google ID token to submit to Kratos' native OIDC login/registration method.
 *
 * On Android this is handled internally via Credential Manager, using
 * [Settings.Data.googleServerClientId]. On iOS, BeyondLogin does not embed a Google SDK - this
 * delegates to [ViewService.Listener.requestGoogleIdToken], which the host app implements using
 * its own Google Sign-In integration.
 */
internal expect suspend fun requestGoogleIdToken(self: BeyondLogin): GoogleSignInResult

/**
 * Silent variant used to warm up the platform's credential provider and to transparently sign
 * back in a user who has already used Google sign-in on this device, without showing an account
 * picker. Android-only - on iOS this returns [GoogleSignInResult.Cancelled] immediately, since
 * silently triggering the host app's own Google Sign-In integration on screen load isn't
 * appropriate there.
 */
internal expect suspend fun requestGoogleIdTokenSilently(self: BeyondLogin): GoogleSignInResult

/**
 * Whether the platform-specific piece of Google sign-in configuration BeyondLogin itself needs
 * is present, used to decide whether to show the Google button at all. Android needs
 * [Settings.Data.googleServerClientId] to call Credential Manager. iOS delegates entirely to the
 * host app's own Google Sign-In integration (see [requestGoogleIdToken]) - there's nothing for
 * BeyondLogin to check there, so [Settings.Data.googleOidcEnabled] alone decides it.
 */
internal expect fun googlePlatformConfigured(settings: Settings.Data): Boolean
