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

import sh.ory.model.RecoveryFlowState
import sh.ory.model.UiContainer

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * This request is used when an identity wants to recover their account.  We recommend reading the [Account Recovery Documentation](../self-service/flows/password-reset-account-recovery)
 *
 * @param expiresAt ExpiresAt is the time (UTC) when the request expires. If the user still wishes to update the setting, a new request has to be initiated.
 * @param id ID represents the request's unique ID. When performing the recovery flow, this represents the id in the recovery ui's query parameter: http://<selfservice.flows.recovery.ui_url>?request=<id>
 * @param issuedAt IssuedAt is the time (UTC) when the request occurred.
 * @param requestUrl RequestURL is the initial URL that was requested from Ory Kratos. It can be used to forward information contained in the URL's path or query for example.
 * @param state 
 * @param type The flow type can either be `api` or `browser`.
 * @param ui 
 * @param active Active, if set, contains the recovery method that is being used. It is initially not set.
 * @param returnTo ReturnTo contains the requested return_to URL.
 */
@Serializable

data class RecoveryFlow (

    /* ExpiresAt is the time (UTC) when the request expires. If the user still wishes to update the setting, a new request has to be initiated. */
    @SerialName(value = "expires_at") @Required val expiresAt: kotlinx.datetime.Instant,

    /* ID represents the request's unique ID. When performing the recovery flow, this represents the id in the recovery ui's query parameter: http://<selfservice.flows.recovery.ui_url>?request=<id> */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* IssuedAt is the time (UTC) when the request occurred. */
    @SerialName(value = "issued_at") @Required val issuedAt: kotlinx.datetime.Instant,

    /* RequestURL is the initial URL that was requested from Ory Kratos. It can be used to forward information contained in the URL's path or query for example. */
    @SerialName(value = "request_url") @Required val requestUrl: kotlin.String,

    @SerialName(value = "state") @Required val state: RecoveryFlowState,

    /* The flow type can either be `api` or `browser`. */
    @SerialName(value = "type") @Required val type: kotlin.String,

    @SerialName(value = "ui") @Required val ui: UiContainer,

    /* Active, if set, contains the recovery method that is being used. It is initially not set. */
    @SerialName(value = "active") val active: kotlin.String? = null,

    /* ReturnTo contains the requested return_to URL. */
    @SerialName(value = "return_to") val returnTo: kotlin.String? = null

)
