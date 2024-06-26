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
 * @param projectSlug ProjectSlug is the project's slug.
 * @param subject Subject is the subject from the API Token.
 */
@Serializable

data class IsOwnerForProjectBySlug (

    /* ProjectSlug is the project's slug. */
    @SerialName(value = "ProjectSlug") @Required val projectSlug: kotlin.String,

    /* Subject is the subject from the API Token. */
    @SerialName(value = "Subject") @Required val subject: kotlin.String

)

