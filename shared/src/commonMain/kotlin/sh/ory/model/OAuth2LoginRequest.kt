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

import sh.ory.model.OAuth2Client
import sh.ory.model.OAuth2ConsentRequestOpenIDConnectContext

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param challenge ID is the identifier (\"login challenge\") of the login request. It is used to identify the session.
 * @param client 
 * @param requestUrl RequestURL is the original OAuth 2.0 Authorization URL requested by the OAuth 2.0 client. It is the URL which initiates the OAuth 2.0 Authorization Code or OAuth 2.0 Implicit flow. This URL is typically not needed, but might come in handy if you want to deal with additional request parameters.
 * @param skip Skip, if true, implies that the client has requested the same scopes from the same user previously. If true, you can skip asking the user to grant the requested scopes, and simply forward the user to the redirect URL.  This feature allows you to update / set session information.
 * @param subject Subject is the user ID of the end-user that authenticated. Now, that end user needs to grant or deny the scope requested by the OAuth 2.0 client. If this value is set and `skip` is true, you MUST include this subject type when accepting the login request, or the request will fail.
 * @param oidcContext 
 * @param requestedAccessTokenAudience 
 * @param requestedScope 
 * @param sessionId SessionID is the login session ID. If the user-agent reuses a login session (via cookie / remember flag) this ID will remain the same. If the user-agent did not have an existing authentication session (e.g. remember is false) this will be a new random value. This value is used as the \"sid\" parameter in the ID Token and in OIDC Front-/Back- channel logout. It's value can generally be used to associate consecutive login requests by a certain user.
 */
@Serializable

data class OAuth2LoginRequest (

    /* ID is the identifier (\"login challenge\") of the login request. It is used to identify the session. */
    @SerialName(value = "challenge") @Required val challenge: kotlin.String,

    @SerialName(value = "client") @Required val client: OAuth2Client,

    /* RequestURL is the original OAuth 2.0 Authorization URL requested by the OAuth 2.0 client. It is the URL which initiates the OAuth 2.0 Authorization Code or OAuth 2.0 Implicit flow. This URL is typically not needed, but might come in handy if you want to deal with additional request parameters. */
    @SerialName(value = "request_url") @Required val requestUrl: kotlin.String,

    /* Skip, if true, implies that the client has requested the same scopes from the same user previously. If true, you can skip asking the user to grant the requested scopes, and simply forward the user to the redirect URL.  This feature allows you to update / set session information. */
    @SerialName(value = "skip") @Required val skip: kotlin.Boolean,

    /* Subject is the user ID of the end-user that authenticated. Now, that end user needs to grant or deny the scope requested by the OAuth 2.0 client. If this value is set and `skip` is true, you MUST include this subject type when accepting the login request, or the request will fail. */
    @SerialName(value = "subject") @Required val subject: kotlin.String,

    @SerialName(value = "oidc_context") val oidcContext: OAuth2ConsentRequestOpenIDConnectContext? = null,

    @SerialName(value = "requested_access_token_audience") val requestedAccessTokenAudience: kotlin.Array<kotlin.String>? = null,

    @SerialName(value = "requested_scope") val requestedScope: kotlin.Array<kotlin.String>? = null,

    /* SessionID is the login session ID. If the user-agent reuses a login session (via cookie / remember flag) this ID will remain the same. If the user-agent did not have an existing authentication session (e.g. remember is false) this will be a new random value. This value is used as the \"sid\" parameter in the ID Token and in OIDC Front-/Back- channel logout. It's value can generally be used to associate consecutive login requests by a certain user. */
    @SerialName(value = "session_id") val sessionId: kotlin.String? = null

)

