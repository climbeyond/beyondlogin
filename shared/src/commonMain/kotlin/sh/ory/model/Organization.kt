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
 * B2B SSO Organization
 *
 * @param createdAt The organization's creation date.
 * @param domains 
 * @param id The organization's ID.
 * @param label The organization's human-readable label.
 * @param projectId The project's ID.
 * @param updatedAt The last time the organization was updated.
 */
@Serializable

data class Organization (

    /* The organization's creation date. */
    @SerialName(value = "created_at") @Required val createdAt: kotlinx.datetime.Instant,

    @SerialName(value = "domains") @Required val domains: kotlin.Array<kotlin.String>,

    /* The organization's ID. */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* The organization's human-readable label. */
    @SerialName(value = "label") @Required val label: kotlin.String,

    /* The project's ID. */
    @SerialName(value = "project_id") @Required val projectId: kotlin.String,

    /* The last time the organization was updated. */
    @SerialName(value = "updated_at") @Required val updatedAt: kotlinx.datetime.Instant

)

