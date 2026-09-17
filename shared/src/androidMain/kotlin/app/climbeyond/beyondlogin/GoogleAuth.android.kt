package app.climbeyond.beyondlogin

import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import app.climbeyond.beyondlogin.helpers.BLLogger
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import java.security.MessageDigest
import java.security.SecureRandom

internal actual suspend fun requestGoogleIdToken(self: BeyondLogin): GoogleSignInResult =
    getGoogleIdCredential(self, filterByAuthorizedAccounts = false)

internal actual suspend fun requestGoogleIdTokenSilently(self: BeyondLogin): GoogleSignInResult =
    getGoogleIdCredential(self, filterByAuthorizedAccounts = true)

internal actual fun googlePlatformConfigured(settings: Settings.Data): Boolean =
    !settings.googleServerClientId.isNullOrEmpty()

private suspend fun getGoogleIdCredential(
    self: BeyondLogin, filterByAuthorizedAccounts: Boolean
): GoogleSignInResult {
    val clientId = self.settings.googleServerClientId
    if (clientId.isNullOrEmpty()) {
        BLLogger.logError("GoogleAuth.requestGoogleIdToken: Settings.Data.googleServerClientId is not configured")
        return GoogleSignInResult.Error("Google sign-in is not configured")
    }

    val nonce = sha256Hex(randomHex(16))

    val option = GetGoogleIdOption.Builder()
        .setServerClientId(clientId)
        .setNonce(nonce)
        .setFilterByAuthorizedAccounts(filterByAuthorizedAccounts)
        .build()

    val request = GetCredentialRequest.Builder()
        .addCredentialOption(option)
        .build()

    return try {
        val response = CredentialManager.create(self.platform.context)
            .getCredential(self.platform.context, request)

        val credential = GoogleIdTokenCredential.createFrom(response.credential.data)
        GoogleSignInResult.Success(credential.idToken, nonce)

    } catch (ex: GetCredentialCancellationException) {
        GoogleSignInResult.Cancelled

    } catch (ex: GetCredentialException) {
        // No authorized account is the expected outcome of the silent warm-up call for most
        // users - only surface it as a warning for the explicit, button-triggered request
        if (filterByAuthorizedAccounts) {
            BLLogger.logDebug("GoogleAuth.requestGoogleIdTokenSilently exception: $ex")
        } else {
            BLLogger.logWarning("GoogleAuth.requestGoogleIdToken exception: $ex")
        }
        GoogleSignInResult.Error(ex.message ?: "Google sign-in failed")
    }
}

private fun randomHex(byteCount: Int): String {
    val bytes = ByteArray(byteCount)
    SecureRandom().nextBytes(bytes)
    return bytes.joinToString("") { "%02x".format(it) }
}

private fun sha256Hex(input: String): String {
    return MessageDigest.getInstance("SHA-256")
        .digest(input.toByteArray())
        .joinToString("") { "%02x".format(it) }
}
