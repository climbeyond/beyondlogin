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

import sh.ory.model.IdentityPatch

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Patch Identities Body
 *
 * @param identities Identities holds the list of patches to apply  required
 */
@Serializable

data class PatchIdentitiesBody (

    /* Identities holds the list of patches to apply  required */
    @SerialName(value = "identities") val identities: kotlin.Array<IdentityPatch>? = null

)

