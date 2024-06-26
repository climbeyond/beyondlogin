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
 * Perform Native Logout Request Body
 *
 * @param sessionToken The Session Token  Invalidate this session token.
 */
@Serializable

data class PerformNativeLogoutBody (

    /* The Session Token  Invalidate this session token. */
    @SerialName(value = "session_token") @Required val sessionToken: kotlin.String

)

