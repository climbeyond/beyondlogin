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

import sh.ory.model.GenericError

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param error 
 * @param redirectBrowserTo Points to where to redirect the user to next.
 */
@Serializable

data class ErrorAuthenticatorAssuranceLevelNotSatisfied (

    @SerialName(value = "error") val error: GenericError? = null,

    /* Points to where to redirect the user to next. */
    @SerialName(value = "redirect_browser_to") val redirectBrowserTo: kotlin.String? = null

)

