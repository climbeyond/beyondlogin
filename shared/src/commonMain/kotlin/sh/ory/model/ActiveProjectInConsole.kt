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
 * The Active Project ID
 *
 * @param projectId The Active Project ID  format: uuid
 */
@Serializable

data class ActiveProjectInConsole (

    /* The Active Project ID  format: uuid */
    @SerialName(value = "project_id") val projectId: kotlin.String? = null

)

