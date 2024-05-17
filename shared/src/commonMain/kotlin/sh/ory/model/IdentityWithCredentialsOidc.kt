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

import sh.ory.model.IdentityWithCredentialsOidcConfig

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Create Identity and Import Social Sign In Credentials
 *
 * @param config 
 */
@Serializable

data class IdentityWithCredentialsOidc (

    @SerialName(value = "config") val config: IdentityWithCredentialsOidcConfig? = null

)
