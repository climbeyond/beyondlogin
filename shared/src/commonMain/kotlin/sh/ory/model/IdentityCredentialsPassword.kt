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
 * 
 *
 * @param hashedPassword HashedPassword is a hash-representation of the password.
 */
@Serializable

data class IdentityCredentialsPassword (

    /* HashedPassword is a hash-representation of the password. */
    @SerialName(value = "hashed_password") val hashedPassword: kotlin.String? = null

)

