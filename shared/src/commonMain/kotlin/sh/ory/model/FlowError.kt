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
 * @param id ID of the error container.
 * @param createdAt CreatedAt is a helper struct field for gobuffalo.pop.
 * @param error 
 * @param updatedAt UpdatedAt is a helper struct field for gobuffalo.pop.
 */
@Serializable

data class FlowError (

    /* ID of the error container. */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* CreatedAt is a helper struct field for gobuffalo.pop. */
    @SerialName(value = "created_at") val createdAt: kotlinx.datetime.Instant? = null,

    @SerialName(value = "error") val error: kotlin.String? = null,

    /* UpdatedAt is a helper struct field for gobuffalo.pop. */
    @SerialName(value = "updated_at") val updatedAt: kotlinx.datetime.Instant? = null

)
