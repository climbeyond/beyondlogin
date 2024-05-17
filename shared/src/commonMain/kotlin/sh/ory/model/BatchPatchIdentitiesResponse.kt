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

import sh.ory.model.IdentityPatchResponse

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Patch identities response
 *
 * @param identities The patch responses for the individual identities.
 */
@Serializable

data class BatchPatchIdentitiesResponse (

    /* The patch responses for the individual identities. */
    @SerialName(value = "identities") val identities: kotlin.Array<IdentityPatchResponse>? = null

)
