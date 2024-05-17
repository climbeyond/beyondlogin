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

import sh.ory.model.UpdateVerificationFlowWithCodeMethod
import sh.ory.model.UpdateVerificationFlowWithLinkMethod

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Update Verification Flow Request Body
 *
 * @param email The email address to verify  If the email belongs to a valid account, a verifiation email will be sent.  If you want to notify the email address if the account does not exist, see the [notify_unknown_recipients flag](https://www.ory.sh/docs/kratos/self-service/flows/verify-email-account-activation#attempted-verification-notifications)  If a code was already sent, including this field in the payload will invalidate the sent code and re-send a new code.  format: email
 * @param method Method is the method that should be used for this verification flow  Allowed values are `link` and `code`. link VerificationStrategyLink code VerificationStrategyCode
 * @param csrfToken Sending the anti-csrf token is only required for browser login flows.
 * @param code Code from the recovery email  If you want to submit a code, use this field, but make sure to _not_ include the email field, as well.
 */


interface UpdateVerificationFlowBody {

    /* The email address to verify  If the email belongs to a valid account, a verifiation email will be sent.  If you want to notify the email address if the account does not exist, see the [notify_unknown_recipients flag](https://www.ory.sh/docs/kratos/self-service/flows/verify-email-account-activation#attempted-verification-notifications)  If a code was already sent, including this field in the payload will invalidate the sent code and re-send a new code.  format: email */
    @SerialName(value = "email") @Required val email: kotlin.String
    /* Method is the method that should be used for this verification flow  Allowed values are `link` and `code`. link VerificationStrategyLink code VerificationStrategyCode */
    @SerialName(value = "method") @Required val method: UpdateVerificationFlowBody.Method
    /* Sending the anti-csrf token is only required for browser login flows. */
    @SerialName(value = "csrf_token") val csrfToken: kotlin.String?
    /* Code from the recovery email  If you want to submit a code, use this field, but make sure to _not_ include the email field, as well. */
    @SerialName(value = "code") val code: kotlin.String?
    /**
     * Method is the method that should be used for this verification flow  Allowed values are `link` and `code`. link VerificationStrategyLink code VerificationStrategyCode
     *
     * Values: LINK,CODE
     */
    @Serializable
    enum class Method(val value: kotlin.String) {
        @SerialName(value = "link") LINK("link"),
        @SerialName(value = "code") CODE("code");
    }
}

