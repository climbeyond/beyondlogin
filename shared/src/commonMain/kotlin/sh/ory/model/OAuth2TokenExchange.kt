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


import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * OAuth2 Token Exchange Result
 *
 * @param accessToken The access token issued by the authorization server.
 * @param expiresIn The lifetime in seconds of the access token. For example, the value \"3600\" denotes that the access token will expire in one hour from the time the response was generated.
 * @param idToken To retrieve a refresh token request the id_token scope.
 * @param refreshToken The refresh token, which can be used to obtain new access tokens. To retrieve it add the scope \"offline\" to your access token request.
 * @param scope The scope of the access token
 * @param tokenType The type of the token issued
 */
@Serializable

data class OAuth2TokenExchange (

    /* The access token issued by the authorization server. */
    @SerialName(value = "access_token") val accessToken: kotlin.String? = null,

    /* The lifetime in seconds of the access token. For example, the value \"3600\" denotes that the access token will expire in one hour from the time the response was generated. */
    @SerialName(value = "expires_in") val expiresIn: kotlin.Long? = null,

    /* To retrieve a refresh token request the id_token scope. */
    @SerialName(value = "id_token") val idToken: kotlin.String? = null,

    /* The refresh token, which can be used to obtain new access tokens. To retrieve it add the scope \"offline\" to your access token request. */
    @SerialName(value = "refresh_token") val refreshToken: kotlin.String? = null,

    /* The scope of the access token */
    @SerialName(value = "scope") val scope: kotlin.String? = null,

    /* The type of the token issued */
    @SerialName(value = "token_type") val tokenType: kotlin.String? = null

)

