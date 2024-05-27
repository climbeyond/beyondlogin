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
import kotlinx.serialization.json.JsonObject

/**
 * 
 *
 * @param id 
 * @param text The message text. Written in american english.
 * @param type The message type. info Info error Error success Success
 * @param context The message's context. Useful when customizing messages.
 */
@Serializable

data class UiText (

    @SerialName(value = "id") @Required val id: kotlin.Long,

    /* The message text. Written in american english. */
    @SerialName(value = "text") @Required val text: kotlin.String,

    /* The message type. info Info error Error success Success */
    @SerialName(value = "type") @Required val type: UiText.Type,

    /* The message's context. Useful when customizing messages. */
    @SerialName(value = "context") val context: JsonObject? = null

) {

    /**
     * The message type. info Info error Error success Success
     *
     * Values: INFO,ERROR,SUCCESS
     */
    @Serializable
    enum class Type(val value: kotlin.String) {
        @SerialName(value = "info") INFO("info"),
        @SerialName(value = "error") ERROR("error"),
        @SerialName(value = "success") SUCCESS("success");
    }
}

