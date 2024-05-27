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

import sh.ory.model.SessionActivityDatapoint

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Response of the getSessionActivity endpoint
 *
 * @param `data` The list of data points.
 */
@Serializable

data class GetSessionActivityResponse (

    /* The list of data points. */
    @SerialName(value = "data") @Required val `data`: kotlin.Array<SessionActivityDatapoint>

)
