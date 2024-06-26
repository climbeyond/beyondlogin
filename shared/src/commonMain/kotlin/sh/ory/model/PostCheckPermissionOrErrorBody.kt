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

import sh.ory.model.SubjectSet

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Post Check Permission Or Error Body
 *
 * @param namespace Namespace to query
 * @param `object` Object to query
 * @param relation Relation to query
 * @param subjectId SubjectID to query  Either SubjectSet or SubjectID can be provided.
 * @param subjectSet 
 */
@Serializable

data class PostCheckPermissionOrErrorBody (

    /* Namespace to query */
    @SerialName(value = "namespace") val namespace: kotlin.String? = null,

    /* Object to query */
    @SerialName(value = "object") val `object`: kotlin.String? = null,

    /* Relation to query */
    @SerialName(value = "relation") val relation: kotlin.String? = null,

    /* SubjectID to query  Either SubjectSet or SubjectID can be provided. */
    @SerialName(value = "subject_id") val subjectId: kotlin.String? = null,

    @SerialName(value = "subject_set") val subjectSet: SubjectSet? = null

)

