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
import sh.ory.model.Session

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.JsonObject

/**
 * The Response for Registration Flows via API
 *
 * @param identity 
 * @param continueWith Contains a list of actions, that could follow this flow  It can, for example, this will contain a reference to the verification flow, created as part of the user's registration or the token of the session.
 * @param session 
 * @param sessionToken The Session Token  This field is only set when the session hook is configured as a post-registration hook.  A session token is equivalent to a session cookie, but it can be sent in the HTTP Authorization Header:  Authorization: bearer ${session-token}  The session token is only issued for API flows, not for Browser flows!
 */
@Serializable

data class SuccessfulNativeRegistration (

    @SerialName(value = "identity") @Required val identity: Identity,

    /* Contains a list of actions, that could follow this flow  It can, for example, this will contain a reference to the verification flow, created as part of the user's registration or the token of the session. */
    @SerialName(value = "continue_with") val continueWith: kotlin.Array<JsonObject>? = null,

    @SerialName(value = "session") val session: Session? = null,

    /* The Session Token  This field is only set when the session hook is configured as a post-registration hook.  A session token is equivalent to a session cookie, but it can be sent in the HTTP Authorization Header:  Authorization: bearer ${session-token}  The session token is only issued for API flows, not for Browser flows! */
    @SerialName(value = "session_token") val sessionToken: kotlin.String? = null

)

