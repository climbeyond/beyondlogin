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

import sh.ory.model.UiText

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * This might include a label and other information that can optionally be used to render UIs.
 *
 * @param label 
 */
@Serializable

data class UiNodeMeta (

    @SerialName(value = "label") val label: UiText? = null

)

