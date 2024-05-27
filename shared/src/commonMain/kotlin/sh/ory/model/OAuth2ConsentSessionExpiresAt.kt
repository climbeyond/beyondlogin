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
 * 
 *
 * @param accessToken 
 * @param authorizeCode 
 * @param idToken 
 * @param parContext 
 * @param refreshToken 
 */
@Serializable

data class OAuth2ConsentSessionExpiresAt (

    @SerialName(value = "access_token") val accessToken: kotlinx.datetime.Instant? = null,

    @SerialName(value = "authorize_code") val authorizeCode: kotlinx.datetime.Instant? = null,

    @SerialName(value = "id_token") val idToken: kotlinx.datetime.Instant? = null,

    @SerialName(value = "par_context") val parContext: kotlinx.datetime.Instant? = null,

    @SerialName(value = "refresh_token") val refreshToken: kotlinx.datetime.Instant? = null

)

