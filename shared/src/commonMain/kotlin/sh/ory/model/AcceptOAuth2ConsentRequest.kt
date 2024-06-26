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

import sh.ory.model.AcceptOAuth2ConsentRequestSession

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param context 
 * @param grantAccessTokenAudience 
 * @param grantScope 
 * @param handledAt 
 * @param remember Remember, if set to true, tells ORY Hydra to remember this consent authorization and reuse it if the same client asks the same user for the same, or a subset of, scope.
 * @param rememberFor RememberFor sets how long the consent authorization should be remembered for in seconds. If set to `0`, the authorization will be remembered indefinitely.
 * @param session 
 */
@Serializable

data class AcceptOAuth2ConsentRequest (

    @SerialName(value = "context") val context: kotlin.String? = null,

    @SerialName(value = "grant_access_token_audience") val grantAccessTokenAudience: kotlin.Array<kotlin.String>? = null,

    @SerialName(value = "grant_scope") val grantScope: kotlin.Array<kotlin.String>? = null,

    @SerialName(value = "handled_at") val handledAt: kotlinx.datetime.Instant? = null,

    /* Remember, if set to true, tells ORY Hydra to remember this consent authorization and reuse it if the same client asks the same user for the same, or a subset of, scope. */
    @SerialName(value = "remember") val remember: kotlin.Boolean? = null,

    /* RememberFor sets how long the consent authorization should be remembered for in seconds. If set to `0`, the authorization will be remembered indefinitely. */
    @SerialName(value = "remember_for") val rememberFor: kotlin.Long? = null,

    @SerialName(value = "session") val session: AcceptOAuth2ConsentRequestSession? = null

)

