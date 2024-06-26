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
 * Error response
 *
 * @param debug Debug contains debug information. This is usually not available and has to be enabled.
 * @param error Name is the error name.
 * @param errorDescription Description contains further information on the nature of the error.
 * @param message Message contains the error message.
 * @param statusCode Code represents the error status code (404, 403, 401, ...).
 */
@Serializable

data class GenericErrorContent (

    /* Debug contains debug information. This is usually not available and has to be enabled. */
    @SerialName(value = "debug") val debug: kotlin.String? = null,

    /* Name is the error name. */
    @SerialName(value = "error") val error: kotlin.String? = null,

    /* Description contains further information on the nature of the error. */
    @SerialName(value = "error_description") val errorDescription: kotlin.String? = null,

    /* Message contains the error message. */
    @SerialName(value = "message") val message: kotlin.String? = null,

    /* Code represents the error status code (404, 403, 401, ...). */
    @SerialName(value = "status_code") val statusCode: kotlin.Long? = null

)

