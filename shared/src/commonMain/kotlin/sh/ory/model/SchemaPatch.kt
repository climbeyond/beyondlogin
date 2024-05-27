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
 * @param `data` The json schema
 * @param name The user defined schema name
 */
@Serializable

data class SchemaPatch (

    /* The json schema */
    @SerialName(value = "data") @Required val `data`: kotlin.String,

    /* The user defined schema name */
    @SerialName(value = "name") @Required val name: kotlin.String

)

