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
 * @param jwt 
 * @param proofType 
 */
@Serializable

data class VerifiableCredentialProof (

    @SerialName(value = "jwt") val jwt: kotlin.String? = null,

    @SerialName(value = "proof_type") val proofType: kotlin.String? = null

)
