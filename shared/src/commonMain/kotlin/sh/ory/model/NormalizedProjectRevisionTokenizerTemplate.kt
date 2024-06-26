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
 * @param claimsMapperUrl Claims mapper URL
 * @param createdAt The Project's Revision Creation Date
 * @param id The revision ID.
 * @param jwksUrl JSON Web Key URL
 * @param key The unique key of the template
 * @param projectRevisionId The Revision's ID this schema belongs to
 * @param ttl Token time to live
 * @param updatedAt Last Time Project's Revision was Updated
 */
@Serializable

data class NormalizedProjectRevisionTokenizerTemplate (

    /* Claims mapper URL */
    @SerialName(value = "claims_mapper_url") val claimsMapperUrl: kotlin.String? = null,

    /* The Project's Revision Creation Date */
    @SerialName(value = "created_at") val createdAt: kotlinx.datetime.Instant? = null,

    /* The revision ID. */
    @SerialName(value = "id") val id: kotlin.String? = null,

    /* JSON Web Key URL */
    @SerialName(value = "jwks_url") val jwksUrl: kotlin.String? = null,

    /* The unique key of the template */
    @SerialName(value = "key") val key: kotlin.String? = null,

    /* The Revision's ID this schema belongs to */
    @SerialName(value = "project_revision_id") val projectRevisionId: kotlin.String? = null,

    /* Token time to live */
    @SerialName(value = "ttl") val ttl: kotlin.String? = "1m",

    /* Last Time Project's Revision was Updated */
    @SerialName(value = "updated_at") val updatedAt: kotlinx.datetime.Instant? = null

)

