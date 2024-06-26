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
 * Logout Flow
 *
 * @param logoutToken LogoutToken can be used to perform logout using AJAX.
 * @param logoutUrl LogoutURL can be opened in a browser to sign the user out.  format: uri
 */
@Serializable

data class LogoutFlow (

    /* LogoutToken can be used to perform logout using AJAX. */
    @SerialName(value = "logout_token") @Required val logoutToken: kotlin.String,

    /* LogoutURL can be opened in a browser to sign the user out.  format: uri */
    @SerialName(value = "logout_url") @Required val logoutUrl: kotlin.String

)

