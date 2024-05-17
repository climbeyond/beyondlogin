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
 * Update Settings Flow with Password Method
 *
 * @param method Method  Should be set to password when trying to update a password.
 * @param password Password is the updated password
 * @param csrfToken CSRFToken is the anti-CSRF token
 */
@Serializable

data class UpdateSettingsFlowWithPasswordMethod (

    /* Method  Should be set to password when trying to update a password. */
    @SerialName(value = "method") @Required val method: kotlin.String,

    /* Password is the updated password */
    @SerialName(value = "password") @Required val password: kotlin.String,

    /* CSRFToken is the anti-CSRF token */
    @SerialName(value = "csrf_token") val csrfToken: kotlin.String? = null

)

