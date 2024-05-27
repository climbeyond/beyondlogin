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

import sh.ory.model.UiContainer

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Used to verify an out-of-band communication channel such as an email address or a phone number.  For more information head over to: https://www.ory.sh/docs/kratos/self-service/flows/verify-email-account-activation
 *
 * @param id ID represents the request's unique ID. When performing the verification flow, this represents the id in the verify ui's query parameter: http://<selfservice.flows.verification.ui_url>?request=<id>  type: string format: uuid
 * @param state State represents the state of this request:  choose_method: ask the user to choose a method (e.g. verify your email) sent_email: the email has been sent to the user passed_challenge: the request was successful and the verification challenge was passed.
 * @param type The flow type can either be `api` or `browser`.
 * @param ui 
 * @param active Active, if set, contains the registration method that is being used. It is initially not set.
 * @param expiresAt ExpiresAt is the time (UTC) when the request expires. If the user still wishes to verify the address, a new request has to be initiated.
 * @param issuedAt IssuedAt is the time (UTC) when the request occurred.
 * @param requestUrl RequestURL is the initial URL that was requested from Ory Kratos. It can be used to forward information contained in the URL's path or query for example.
 * @param returnTo ReturnTo contains the requested return_to URL.
 * @param transientPayload TransientPayload is used to pass data from the verification flow to hooks and email templates
 */
@Serializable

data class VerificationFlow (

    /* ID represents the request's unique ID. When performing the verification flow, this represents the id in the verify ui's query parameter: http://<selfservice.flows.verification.ui_url>?request=<id>  type: string format: uuid */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* State represents the state of this request:  choose_method: ask the user to choose a method (e.g. verify your email) sent_email: the email has been sent to the user passed_challenge: the request was successful and the verification challenge was passed. */
    @SerialName(value = "state") @Required val state: VerificationFlowState,

    /* The flow type can either be `api` or `browser`. */
    @SerialName(value = "type") @Required val type: kotlin.String,

    @SerialName(value = "ui") @Required val ui: UiContainer,

    /* Active, if set, contains the registration method that is being used. It is initially not set. */
    @SerialName(value = "active") val active: kotlin.String? = null,

    /* ExpiresAt is the time (UTC) when the request expires. If the user still wishes to verify the address, a new request has to be initiated. */
    @SerialName(value = "expires_at") val expiresAt: kotlinx.datetime.Instant? = null,

    /* IssuedAt is the time (UTC) when the request occurred. */
    @SerialName(value = "issued_at") val issuedAt: kotlinx.datetime.Instant? = null,

    /* RequestURL is the initial URL that was requested from Ory Kratos. It can be used to forward information contained in the URL's path or query for example. */
    @SerialName(value = "request_url") val requestUrl: kotlin.String? = null,

    /* ReturnTo contains the requested return_to URL. */
    @SerialName(value = "return_to") val returnTo: kotlin.String? = null,

    /* TransientPayload is used to pass data from the verification flow to hooks and email templates */
    @SerialName(value = "transient_payload") val transientPayload: kotlin.String? = null

)

