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

import sh.ory.model.IdentityCredentialsType

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Credentials represents a specific credential type
 *
 * @param config 
 * @param createdAt CreatedAt is a helper struct field for gobuffalo.pop.
 * @param identifiers Identifiers represents a list of unique identifiers this credential type matches.
 * @param type 
 * @param updatedAt UpdatedAt is a helper struct field for gobuffalo.pop.
 * @param version Version refers to the version of the credential. Useful when changing the config schema.
 */
@Serializable

data class IdentityCredentials (

    @SerialName(value = "config") val config: kotlin.String? = null,

    /* CreatedAt is a helper struct field for gobuffalo.pop. */
    @SerialName(value = "created_at") val createdAt: kotlinx.datetime.Instant? = null,

    /* Identifiers represents a list of unique identifiers this credential type matches. */
    @SerialName(value = "identifiers") val identifiers: kotlin.Array<kotlin.String>? = null,

    @SerialName(value = "type") val type: IdentityCredentialsType? = null,

    /* UpdatedAt is a helper struct field for gobuffalo.pop. */
    @SerialName(value = "updated_at") val updatedAt: kotlinx.datetime.Instant? = null,

    /* Version refers to the version of the credential. Useful when changing the config schema. */
    @SerialName(value = "version") val version: kotlin.Long? = null

)
