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

import sh.ory.model.IdentityWithCredentialsOidc
import sh.ory.model.IdentityWithCredentialsPassword

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Create Identity and Import Credentials
 *
 * @param oidc 
 * @param password 
 */
@Serializable

data class IdentityWithCredentials (

    @SerialName(value = "oidc") val oidc: IdentityWithCredentialsOidc? = null,

    @SerialName(value = "password") val password: IdentityWithCredentialsPassword? = null

)

