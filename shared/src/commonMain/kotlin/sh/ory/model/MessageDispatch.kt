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
 * MessageDispatch represents an attempt of sending a courier message It contains the status of the attempt (failed or successful) and the error if any occured
 *
 * @param createdAt CreatedAt is a helper struct field for gobuffalo.pop.
 * @param id The ID of this message dispatch
 * @param messageId The ID of the message being dispatched
 * @param status The status of this dispatch Either \"failed\" or \"success\" failed CourierMessageDispatchStatusFailed success CourierMessageDispatchStatusSuccess
 * @param updatedAt UpdatedAt is a helper struct field for gobuffalo.pop.
 * @param error 
 */
@Serializable

data class MessageDispatch (

    /* CreatedAt is a helper struct field for gobuffalo.pop. */
    @SerialName(value = "created_at") @Required val createdAt: kotlinx.datetime.Instant,

    /* The ID of this message dispatch */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* The ID of the message being dispatched */
    @SerialName(value = "message_id") @Required val messageId: kotlin.String,

    /* The status of this dispatch Either \"failed\" or \"success\" failed CourierMessageDispatchStatusFailed success CourierMessageDispatchStatusSuccess */
    @SerialName(value = "status") @Required val status: MessageDispatch.Status,

    /* UpdatedAt is a helper struct field for gobuffalo.pop. */
    @SerialName(value = "updated_at") @Required val updatedAt: kotlinx.datetime.Instant,

    @SerialName(value = "error") val error: kotlin.String? = null

) {

    /**
     * The status of this dispatch Either \"failed\" or \"success\" failed CourierMessageDispatchStatusFailed success CourierMessageDispatchStatusSuccess
     *
     * Values: FAILED,SUCCESS
     */
    @Serializable
    enum class Status(val value: kotlin.String) {
        @SerialName(value = "failed") FAILED("failed"),
        @SerialName(value = "success") SUCCESS("success");
    }
}
