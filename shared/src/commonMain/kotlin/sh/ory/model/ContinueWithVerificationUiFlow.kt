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
 * @param id The ID of the verification flow
 * @param verifiableAddress The address that should be verified in this flow
 * @param url The URL of the verification flow
 */
@Serializable

data class ContinueWithVerificationUiFlow (

    /* The ID of the verification flow */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* The address that should be verified in this flow */
    @SerialName(value = "verifiable_address") @Required val verifiableAddress: kotlin.String,

    /* The URL of the verification flow */
    @SerialName(value = "url") val url: kotlin.String? = null

)
