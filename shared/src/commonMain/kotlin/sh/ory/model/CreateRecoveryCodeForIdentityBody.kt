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
 * Create Recovery Code for Identity Request Body
 *
 * @param identityId Identity to Recover  The identity's ID you wish to recover.
 * @param expiresIn Code Expires In  The recovery code will expire after that amount of time has passed. Defaults to the configuration value of `selfservice.methods.code.config.lifespan`.
 */
@Serializable

data class CreateRecoveryCodeForIdentityBody (

    /* Identity to Recover  The identity's ID you wish to recover. */
    @SerialName(value = "identity_id") @Required val identityId: kotlin.String,

    /* Code Expires In  The recovery code will expire after that amount of time has passed. Defaults to the configuration value of `selfservice.methods.code.config.lifespan`. */
    @SerialName(value = "expires_in") val expiresIn: kotlin.String? = null

)
