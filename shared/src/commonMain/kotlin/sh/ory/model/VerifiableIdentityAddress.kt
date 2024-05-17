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
 * VerifiableAddress is an identity's verifiable address
 *
 * @param status VerifiableAddressStatus must not exceed 16 characters as that is the limitation in the SQL Schema
 * @param `value` The address value  example foo@user.com
 * @param verified Indicates if the address has already been verified
 * @param via VerifiableAddressType must not exceed 16 characters as that is the limitation in the SQL Schema
 * @param createdAt When this entry was created
 * @param id The ID
 * @param updatedAt When this entry was last updated
 * @param verifiedAt 
 */
@Serializable

data class VerifiableIdentityAddress (

    /* VerifiableAddressStatus must not exceed 16 characters as that is the limitation in the SQL Schema */
    @SerialName(value = "status") @Required val status: kotlin.String,

    /* The address value  example foo@user.com */
    @SerialName(value = "value") @Required val `value`: kotlin.String,

    /* Indicates if the address has already been verified */
    @SerialName(value = "verified") @Required val verified: kotlin.Boolean,

    /* VerifiableAddressType must not exceed 16 characters as that is the limitation in the SQL Schema */
    @SerialName(value = "via") @Required val via: kotlin.String,

    /* When this entry was created */
    @SerialName(value = "created_at") val createdAt: kotlinx.datetime.Instant? = null,

    /* The ID */
    @SerialName(value = "id") val id: kotlin.String? = null,

    /* When this entry was last updated */
    @SerialName(value = "updated_at") val updatedAt: kotlinx.datetime.Instant? = null,

    @SerialName(value = "verified_at") val verifiedAt: kotlinx.datetime.Instant? = null

)
