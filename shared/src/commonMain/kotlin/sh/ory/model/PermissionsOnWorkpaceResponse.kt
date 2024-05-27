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
 * Get Permissions on Project Request Parameters
 *
 * @param permissions 
 */
@Serializable

data class PermissionsOnWorkpaceResponse (

    @SerialName(value = "permissions") val permissions: kotlin.collections.Map<kotlin.String, kotlin.Boolean>? = null

)

