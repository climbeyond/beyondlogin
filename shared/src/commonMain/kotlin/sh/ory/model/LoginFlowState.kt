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

/**
 * The state represents the state of the login flow.  choose_method: ask the user to choose a method (e.g. login account via email) sent_email: the email has been sent to the user passed_challenge: the request was successful and the login challenge was passed.
 *
 * Values: CHOOSE_METHOD,SENT_EMAIL,PASSED_CHALLENGE
 */
@Serializable
enum class LoginFlowState(val value: kotlin.String) {

    @SerialName(value = "choose_method")
    CHOOSE_METHOD("choose_method"),

    @SerialName(value = "sent_email")
    SENT_EMAIL("sent_email"),

    @SerialName(value = "passed_challenge")
    PASSED_CHALLENGE("passed_challenge");

    /**
     * Override [toString()] to avoid using the enum variable name as the value, and instead use
     * the actual value defined in the API spec file.
     *
     * This solves a problem when the variable name and its value are different, and ensures that
     * the client sends the correct enum values to the server always.
     */
    override fun toString(): kotlin.String = value

    companion object {
        /**
         * Converts the provided [data] to a [String] on success, null otherwise.
         */
        fun encode(data: kotlin.Any?): kotlin.String? = if (data is LoginFlowState) "$data" else null

        /**
         * Returns a valid [LoginFlowState] for [data], null otherwise.
         */
        fun decode(data: kotlin.Any?): LoginFlowState? = data?.let {
          val normalizedData = "$it".lowercase()
          values().firstOrNull { value ->
            it == value || normalizedData == "$value".lowercase()
          }
        }
    }
}

