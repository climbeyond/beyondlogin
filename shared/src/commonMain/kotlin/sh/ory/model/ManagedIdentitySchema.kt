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
 * Together the name and identity uuid are a unique index constraint. This prevents a user from having schemas with the same name. This also allows schemas to have the same name across the system.
 *
 * @param blobName The gcs file name  This is a randomly generated name which is used to uniquely identify the file on the blob storage
 * @param blobUrl The publicly accessible url of the schema
 * @param createdAt The Schema's Creation Date
 * @param id The schema's ID.
 * @param name The schema name  This is set by the user and is for them to easily recognise their schema
 * @param updatedAt Last Time Schema was Updated
 * @param contentHash The Content Hash  Contains a hash of the schema's content.
 */
@Serializable

data class ManagedIdentitySchema (

    /* The gcs file name  This is a randomly generated name which is used to uniquely identify the file on the blob storage */
    @SerialName(value = "blob_name") @Required val blobName: kotlin.String,

    /* The publicly accessible url of the schema */
    @SerialName(value = "blob_url") @Required val blobUrl: kotlin.String,

    /* The Schema's Creation Date */
    @SerialName(value = "created_at") @Required val createdAt: kotlinx.datetime.Instant,

    /* The schema's ID. */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* The schema name  This is set by the user and is for them to easily recognise their schema */
    @SerialName(value = "name") @Required val name: kotlin.String,

    /* Last Time Schema was Updated */
    @SerialName(value = "updated_at") @Required val updatedAt: kotlinx.datetime.Instant,

    /* The Content Hash  Contains a hash of the schema's content. */
    @SerialName(value = "content_hash") val contentHash: kotlin.String? = null

)

