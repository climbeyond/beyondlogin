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

import sh.ory.model.ProjectBrandingTheme

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * 
 *
 * @param createdAt The Customization Creation Date
 * @param defaultTheme 
 * @param id The customization ID.
 * @param projectId The Project's ID this customization is associated with
 * @param themes 
 * @param updatedAt Last Time Branding was Updated
 */
@Serializable

data class ProjectBranding (

    /* The Customization Creation Date */
    @SerialName(value = "created_at") @Required val createdAt: kotlinx.datetime.Instant,

    @SerialName(value = "default_theme") @Required val defaultTheme: ProjectBrandingTheme,

    /* The customization ID. */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* The Project's ID this customization is associated with */
    @SerialName(value = "project_id") @Required val projectId: kotlin.String,

    @SerialName(value = "themes") @Required val themes: kotlin.Array<ProjectBrandingTheme>,

    /* Last Time Branding was Updated */
    @SerialName(value = "updated_at") @Required val updatedAt: kotlinx.datetime.Instant

)

