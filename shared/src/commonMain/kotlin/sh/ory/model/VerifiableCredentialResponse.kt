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
 * @param credentialDraft00 
 * @param format 
 */
@Serializable

data class VerifiableCredentialResponse (

    @SerialName(value = "credential_draft_00") val credentialDraft00: kotlin.String? = null,

    @SerialName(value = "format") val format: kotlin.String? = null

)
