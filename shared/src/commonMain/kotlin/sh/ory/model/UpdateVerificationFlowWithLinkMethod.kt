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
 * Update Verification Flow with Link Method
 *
 * @param email Email to Verify  Needs to be set when initiating the flow. If the email is a registered verification email, a verification link will be sent. If the email is not known, a email with details on what happened will be sent instead.  format: email
 * @param method Method is the method that should be used for this verification flow  Allowed values are `link` and `code` link VerificationStrategyLink code VerificationStrategyCode
 * @param csrfToken Sending the anti-csrf token is only required for browser login flows.
 */
@Serializable

data class UpdateVerificationFlowWithLinkMethod (

    /* Email to Verify  Needs to be set when initiating the flow. If the email is a registered verification email, a verification link will be sent. If the email is not known, a email with details on what happened will be sent instead.  format: email */
    @SerialName(value = "email") @Required val email: kotlin.String,

    /* Method is the method that should be used for this verification flow  Allowed values are `link` and `code` link VerificationStrategyLink code VerificationStrategyCode */
    @SerialName(value = "method") @Required val method: UpdateVerificationFlowWithLinkMethod.Method,

    /* Sending the anti-csrf token is only required for browser login flows. */
    @SerialName(value = "csrf_token") val csrfToken: kotlin.String? = null

) {

    /**
     * Method is the method that should be used for this verification flow  Allowed values are `link` and `code` link VerificationStrategyLink code VerificationStrategyCode
     *
     * Values: LINK,CODE
     */
    @Serializable
    enum class Method(val value: kotlin.String) {
        @SerialName(value = "link") LINK("link"),
        @SerialName(value = "code") CODE("code");
    }
}

