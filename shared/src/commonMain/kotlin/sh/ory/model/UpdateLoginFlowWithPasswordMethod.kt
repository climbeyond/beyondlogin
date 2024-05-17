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
 * Update Login Flow with Password Method
 *
 * @param identifier Identifier is the email or username of the user trying to log in.
 * @param method Method should be set to \"password\" when logging in using the identifier and password strategy.
 * @param password The user's password.
 * @param csrfToken Sending the anti-csrf token is only required for browser login flows.
 * @param passwordIdentifier Identifier is the email or username of the user trying to log in. This field is deprecated!
 */
@Serializable

data class UpdateLoginFlowWithPasswordMethod (

    /* Identifier is the email or username of the user trying to log in. */
    @SerialName(value = "identifier") @Required val identifier: kotlin.String,

    /* Method should be set to \"password\" when logging in using the identifier and password strategy. */
    @SerialName(value = "method") @Required val method: kotlin.String,

    /* The user's password. */
    @SerialName(value = "password") @Required val password: kotlin.String,

    /* Sending the anti-csrf token is only required for browser login flows. */
    @SerialName(value = "csrf_token") val csrfToken: kotlin.String? = null,

    /* Identifier is the email or username of the user trying to log in. This field is deprecated! */
    @SerialName(value = "password_identifier") val passwordIdentifier: kotlin.String? = null

)
