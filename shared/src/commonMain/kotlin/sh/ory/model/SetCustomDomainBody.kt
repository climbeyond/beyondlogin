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
 * Update Custom Hostname Body
 *
 * @param cookieDomain The domain where cookies will be set. Has to be a parent domain of the custom hostname to work.
 * @param corsAllowedOrigins CORS Allowed origins for the custom hostname.
 * @param corsEnabled CORS Enabled for the custom hostname.
 * @param customUiBaseUrl The custom UI base URL where the UI will be exposed.
 * @param hostname The custom hostname where the API will be exposed.
 */
@Serializable

data class SetCustomDomainBody (

    /* The domain where cookies will be set. Has to be a parent domain of the custom hostname to work. */
    @SerialName(value = "cookie_domain") val cookieDomain: kotlin.String? = null,

    /* CORS Allowed origins for the custom hostname. */
    @SerialName(value = "cors_allowed_origins") val corsAllowedOrigins: kotlin.Array<kotlin.String>? = null,

    /* CORS Enabled for the custom hostname. */
    @SerialName(value = "cors_enabled") val corsEnabled: kotlin.Boolean? = null,

    /* The custom UI base URL where the UI will be exposed. */
    @SerialName(value = "custom_ui_base_url") val customUiBaseUrl: kotlin.String? = null,

    /* The custom hostname where the API will be exposed. */
    @SerialName(value = "hostname") val hostname: kotlin.String? = null

)

