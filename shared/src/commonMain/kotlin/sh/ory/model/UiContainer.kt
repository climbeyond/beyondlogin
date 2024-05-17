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

import sh.ory.model.UiNode
import sh.ory.model.UiText

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Container represents a HTML Form. The container can work with both HTTP Form and JSON requests
 *
 * @param action Action should be used as the form action URL `<form action=\"{{ .Action }}\" method=\"post\">`.
 * @param method Method is the form method (e.g. POST)
 * @param nodes 
 * @param messages 
 */
@Serializable

data class UiContainer (

    /* Action should be used as the form action URL `<form action=\"{{ .Action }}\" method=\"post\">`. */
    @SerialName(value = "action") @Required val action: kotlin.String,

    /* Method is the form method (e.g. POST) */
    @SerialName(value = "method") @Required val method: kotlin.String,

    @SerialName(value = "nodes") @Required val nodes: kotlin.Array<UiNode>,

    @SerialName(value = "messages") val messages: kotlin.Array<UiText>? = null

)
