/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package sh.ory.model

import sh.ory.model.AuthenticatorAssuranceLevel

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * A singular authenticator used during authentication / login.
 *
 * @param aal 
 * @param completedAt When the authentication challenge was completed.
 * @param method 
 * @param provider OIDC or SAML provider id used for authentication
 */
@Serializable

data class SessionAuthenticationMethod (

    @SerialName(value = "aal") val aal: AuthenticatorAssuranceLevel? = null,

    /* When the authentication challenge was completed. */
    @SerialName(value = "completed_at") val completedAt: kotlinx.datetime.Instant? = null,

    @SerialName(value = "method") val method: SessionAuthenticationMethod.Method? = null,

    /* OIDC or SAML provider id used for authentication */
    @SerialName(value = "provider") val provider: kotlin.String? = null

) {

    /**
     * 
     *
     * Values: LINK_RECOVERY,CODE_RECOVERY,PASSWORD,TOTP,OIDC,WEBAUTHN,LOOKUP_SECRET,V0_PERIOD6_LEGACY_SESSION
     */
    @Serializable
    enum class Method(val value: kotlin.String) {
        @SerialName(value = "link_recovery") LINK_RECOVERY("link_recovery"),
        @SerialName(value = "code_recovery") CODE_RECOVERY("code_recovery"),
        @SerialName(value = "password") PASSWORD("password"),
        @SerialName(value = "totp") TOTP("totp"),
        @SerialName(value = "oidc") OIDC("oidc"),
        @SerialName(value = "webauthn") WEBAUTHN("webauthn"),
        @SerialName(value = "lookup_secret") LOOKUP_SECRET("lookup_secret"),
        @SerialName(value = "v0.6_legacy_session") V0_PERIOD6_LEGACY_SESSION("v0.6_legacy_session");
    }
}
