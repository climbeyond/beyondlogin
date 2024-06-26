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

import sh.ory.model.IdentityWithCredentialsOidcConfigProvider
import sh.ory.model.IdentityWithCredentialsPasswordConfig

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param config 
 * @param providers A list of OpenID Connect Providers
 */
@Serializable

data class IdentityWithCredentialsOidcConfig (

    @SerialName(value = "config") val config: IdentityWithCredentialsPasswordConfig? = null,

    /* A list of OpenID Connect Providers */
    @SerialName(value = "providers") val providers: kotlin.Array<IdentityWithCredentialsOidcConfigProvider>? = null

)

