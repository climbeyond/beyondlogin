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
 * @param createdAt The Customization Creation Date.
 * @param id The customization theme ID.
 * @param name The customization theme name.
 * @param projectBrandingId The ProjectBranding ID this customization is associated with.
 * @param updatedAt Last Time Branding was Updated.
 * @param accentDefaultColor AccentDefaultColor is a hex color code used by the Ory Account Experience theme.
 * @param accentDisabledColor AccentDisabledColor is a hex color code used by the Ory Account Experience theme.
 * @param accentEmphasisColor AccentEmphasisColor is a hex color code used by the Ory Account Experience theme.
 * @param accentMutedColor AccentMutedColor is a hex color code used by the Ory Account Experience theme.
 * @param accentSubtleColor AccentSubtleColor is a hex color code used by the Ory Account Experience theme.
 * @param backgroundCanvasColor BackgroundCanvasColor is a hex color code used by the Ory Account Experience theme.
 * @param backgroundSubtleColor BackgroundSubtleColor is a hex color code used by the Ory Account Experience theme.
 * @param backgroundSurfaceColor BackgroundSurfaceColor is a hex color code used by the Ory Account Experience theme.
 * @param borderDefaultColor BorderDefaultColor is a hex color code used by the Ory Account Experience theme.
 * @param errorDefaultColor ErrorDefaultColor is a hex color code used by the Ory Account Experience theme.
 * @param errorEmphasisColor ErrorEmphasisColor is a hex color code used by the Ory Account Experience theme.
 * @param errorMutedColor ErrorMutedColor is a hex color code used by the Ory Account Experience theme.
 * @param errorSubtleColor ErrorSubtleColor is a hex color code used by the Ory Account Experience theme.
 * @param faviconType Favicon Type The Favicon mime type.
 * @param faviconUrl Favicon URL Favicon can be an https:// or base64:// URL. If the URL is not allowed, the favicon will be stored inside the Ory Network storage bucket.
 * @param foregroundDefaultColor ForegroundDefaultColor is a hex color code used by the Ory Account Experience theme.
 * @param foregroundDisabledColor ForegroundDisabledColor is a hex color code used by the Ory Account Experience theme.
 * @param foregroundMutedColor ForegroundMutedColor is a hex color code used by the Ory Account Experience theme.
 * @param foregroundOnAccentColor ForegroundOnAccentColor is a hex color code used by the Ory Account Experience theme.
 * @param foregroundOnDarkColor ForegroundOnDarkColor is a hex color code used by the Ory Account Experience theme.
 * @param foregroundOnDisabledColor ForegroundOnDisabledColor is a hex color code used by the Ory Account Experience theme.
 * @param foregroundSubtleColor ForegroundSubtleColor is a hex color code used by the Ory Account Experience theme.
 * @param inputBackgroundColor InputBackgroundColor is a hex color code used by the Ory Account Experience theme.
 * @param inputDisabledColor InputDisabledColor is a hex color code used by the Ory Account Experience theme.
 * @param inputPlaceholderColor InputPlaceholderColor is a hex color code used by the Ory Account Experience theme.
 * @param inputTextColor InputTextColor is a hex color code used by the Ory Account Experience theme.
 * @param logoType Logo Type The Logo mime type.
 * @param logoUrl Logo URL Logo can be an https:// or base64:// URL. If the URL is not allowed, the logo will be stored inside the Ory Network storage bucket.
 * @param primaryColor Primary color is an hsla color value used to derive the other colors from for the Ory Account Experience theme.
 * @param secondaryColor Secondary color is a hsla color code used to derive the other colors from for the Ory Account Experience theme.
 * @param successEmphasisColor SuccessEmphasisColor is a hex color code used by the Ory Account Experience theme.
 * @param textDefaultColor TextDefaultColor is a hex color code used by the Ory Account Experience theme.
 * @param textDisabledColor TextDisabledColor is a hex color code used by the Ory Account Experience theme.
 */
@Serializable

data class ProjectBrandingTheme (

    /* The Customization Creation Date. */
    @SerialName(value = "created_at") @Required val createdAt: kotlinx.datetime.Instant,

    /* The customization theme ID. */
    @SerialName(value = "id") @Required val id: kotlin.String,

    /* The customization theme name. */
    @SerialName(value = "name") @Required val name: kotlin.String,

    /* The ProjectBranding ID this customization is associated with. */
    @SerialName(value = "project_branding_id") @Required val projectBrandingId: kotlin.String,

    /* Last Time Branding was Updated. */
    @SerialName(value = "updated_at") @Required val updatedAt: kotlinx.datetime.Instant,

    /* AccentDefaultColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "accent_default_color") val accentDefaultColor: kotlin.String? = null,

    /* AccentDisabledColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "accent_disabled_color") val accentDisabledColor: kotlin.String? = null,

    /* AccentEmphasisColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "accent_emphasis_color") val accentEmphasisColor: kotlin.String? = null,

    /* AccentMutedColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "accent_muted_color") val accentMutedColor: kotlin.String? = null,

    /* AccentSubtleColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "accent_subtle_color") val accentSubtleColor: kotlin.String? = null,

    /* BackgroundCanvasColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "background_canvas_color") val backgroundCanvasColor: kotlin.String? = null,

    /* BackgroundSubtleColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "background_subtle_color") val backgroundSubtleColor: kotlin.String? = null,

    /* BackgroundSurfaceColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "background_surface_color") val backgroundSurfaceColor: kotlin.String? = null,

    /* BorderDefaultColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "border_default_color") val borderDefaultColor: kotlin.String? = null,

    /* ErrorDefaultColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "error_default_color") val errorDefaultColor: kotlin.String? = null,

    /* ErrorEmphasisColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "error_emphasis_color") val errorEmphasisColor: kotlin.String? = null,

    /* ErrorMutedColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "error_muted_color") val errorMutedColor: kotlin.String? = null,

    /* ErrorSubtleColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "error_subtle_color") val errorSubtleColor: kotlin.String? = null,

    /* Favicon Type The Favicon mime type. */
    @SerialName(value = "favicon_type") val faviconType: kotlin.String? = null,

    /* Favicon URL Favicon can be an https:// or base64:// URL. If the URL is not allowed, the favicon will be stored inside the Ory Network storage bucket. */
    @SerialName(value = "favicon_url") val faviconUrl: kotlin.String? = null,

    /* ForegroundDefaultColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "foreground_default_color") val foregroundDefaultColor: kotlin.String? = null,

    /* ForegroundDisabledColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "foreground_disabled_color") val foregroundDisabledColor: kotlin.String? = null,

    /* ForegroundMutedColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "foreground_muted_color") val foregroundMutedColor: kotlin.String? = null,

    /* ForegroundOnAccentColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "foreground_on_accent_color") val foregroundOnAccentColor: kotlin.String? = null,

    /* ForegroundOnDarkColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "foreground_on_dark_color") val foregroundOnDarkColor: kotlin.String? = null,

    /* ForegroundOnDisabledColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "foreground_on_disabled_color") val foregroundOnDisabledColor: kotlin.String? = null,

    /* ForegroundSubtleColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "foreground_subtle_color") val foregroundSubtleColor: kotlin.String? = null,

    /* InputBackgroundColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "input_background_color") val inputBackgroundColor: kotlin.String? = null,

    /* InputDisabledColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "input_disabled_color") val inputDisabledColor: kotlin.String? = null,

    /* InputPlaceholderColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "input_placeholder_color") val inputPlaceholderColor: kotlin.String? = null,

    /* InputTextColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "input_text_color") val inputTextColor: kotlin.String? = null,

    /* Logo Type The Logo mime type. */
    @SerialName(value = "logo_type") val logoType: kotlin.String? = null,

    /* Logo URL Logo can be an https:// or base64:// URL. If the URL is not allowed, the logo will be stored inside the Ory Network storage bucket. */
    @SerialName(value = "logo_url") val logoUrl: kotlin.String? = null,

    /* Primary color is an hsla color value used to derive the other colors from for the Ory Account Experience theme. */
    @SerialName(value = "primary_color") val primaryColor: kotlin.String? = null,

    /* Secondary color is a hsla color code used to derive the other colors from for the Ory Account Experience theme. */
    @SerialName(value = "secondary_color") val secondaryColor: kotlin.String? = null,

    /* SuccessEmphasisColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "success_emphasis_color") val successEmphasisColor: kotlin.String? = null,

    /* TextDefaultColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "text_default_color") val textDefaultColor: kotlin.String? = null,

    /* TextDisabledColor is a hex color code used by the Ory Account Experience theme. */
    @SerialName(value = "text_disabled_color") val textDisabledColor: kotlin.String? = null

)

