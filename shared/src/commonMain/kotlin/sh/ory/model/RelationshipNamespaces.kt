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

import sh.ory.model.Namespace

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Relationship Namespace List
 *
 * @param namespaces 
 */
@Serializable

data class RelationshipNamespaces (

    @SerialName(value = "namespaces") val namespaces: kotlin.Array<Namespace>? = null

)

