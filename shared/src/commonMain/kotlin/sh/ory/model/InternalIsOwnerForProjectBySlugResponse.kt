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
 * @param projectId ProjectID is the project's ID.
 */
@Serializable

data class InternalIsOwnerForProjectBySlugResponse (

    /* ProjectID is the project's ID. */
    @SerialName(value = "project_id") @Required val projectId: kotlin.String

)
