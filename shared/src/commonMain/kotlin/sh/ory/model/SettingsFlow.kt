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

import sh.ory.model.ContinueWith
import sh.ory.model.Identity
import sh.ory.model.SettingsFlowState
import sh.ory.model.UiContainer

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * This flow is used when an identity wants to update settings (e.g. profile data, passwords, ...) in a selfservice manner.  We recommend reading the [User Settings Documentation](../self-service/flows/user-settings)
 *
 * @param expiresAt ExpiresAt is the time (UTC) when the flow expires. If the user still wishes to update the setting, a new flow has to be initiated.
 * @param id ID represents the flow's unique ID. When performing the settings flow, this represents the id in the settings ui's query parameter: http://<selfservice.flows.settings.ui_url>?flow=<id>
 * @param identity 
 * @param issuedAt IssuedAt is the time (UTC) when the flow occurred.
 * @param requestUrl RequestURL is the initial URL that was requested from Ory Kratos. It can be used to forward information contained in the URL's path or query for example.
 * @param state 
 * @param type The flow type can either be `api` or `browser`.
 * @param ui 
 * @param active Active, if set, contains the registration method that is being used. It is initially not set.
 * @param continueWith Contains a list of actions, that could follow this flow  It can, for example, contain a reference to the verification flow, created as part of the user's registration.
 * @param returnTo ReturnTo contains the requested return_to URL.
 */
@Serializable

data class SettingsFlow (

    /* ExpiresAt is the time (UTC) when the flow expires. If the user still wishes to update the setting, a new flow has to be initiated. */
    @SerialName(value = "expires_at") @Required val expiresAt: kotlinx.datetime.Instant,

    /* ID represents the flow's unique ID. When performing the settings flow, this represents the id in the settings ui's query parameter: http://<selfservice.flows.settings.ui_url>?flow=<id> */
    @SerialName(value = "id") @Required val id: kotlin.String,

    @SerialName(value = "identity") @Required val identity: Identity,

    /* IssuedAt is the time (UTC) when the flow occurred. */
    @SerialName(value = "issued_at") @Required val issuedAt: kotlinx.datetime.Instant,

    /* RequestURL is the initial URL that was requested from Ory Kratos. It can be used to forward information contained in the URL's path or query for example. */
    @SerialName(value = "request_url") @Required val requestUrl: kotlin.String,

    @SerialName(value = "state") @Required val state: SettingsFlowState,

    /* The flow type can either be `api` or `browser`. */
    @SerialName(value = "type") @Required val type: kotlin.String,

    @SerialName(value = "ui") @Required val ui: UiContainer,

    /* Active, if set, contains the registration method that is being used. It is initially not set. */
    @SerialName(value = "active") val active: kotlin.String? = null,

    /* Contains a list of actions, that could follow this flow  It can, for example, contain a reference to the verification flow, created as part of the user's registration. */
    @SerialName(value = "continue_with") val continueWith: kotlin.Array<ContinueWith>? = null,

    /* ReturnTo contains the requested return_to URL. */
    @SerialName(value = "return_to") val returnTo: kotlin.String? = null

)

