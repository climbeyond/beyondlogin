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
 * @param additionalIdTokenAudiences 
 * @param applePrivateKey 
 * @param applePrivateKeyId Apple Private Key Identifier  Sign In with Apple Private Key Identifier needed for generating a JWT token for client secret
 * @param appleTeamId Apple Developer Team ID  Apple Developer Team ID needed for generating a JWT token for client secret
 * @param authUrl AuthURL is the authorize url, typically something like: https://example.org/oauth2/auth Should only be used when the OAuth2 / OpenID Connect server is not supporting OpenID Connect Discovery and when `provider` is set to `generic`.
 * @param azureTenant Tenant is the Azure AD Tenant to use for authentication, and must be set when `provider` is set to `microsoft`.  Can be either `common`, `organizations`, `consumers` for a multitenant application or a specific tenant like `8eaef023-2b34-4da1-9baa-8bc8c9d6a490` or `contoso.onmicrosoft.com`.
 * @param claimsSource 
 * @param clientId ClientID is the application's Client ID.
 * @param clientSecret 
 * @param createdAt The Project's Revision Creation Date
 * @param id 
 * @param issuerUrl IssuerURL is the OpenID Connect Server URL. You can leave this empty if `provider` is not set to `generic`. If set, neither `auth_url` nor `token_url` are required.
 * @param label Label represents an optional label which can be used in the UI generation.
 * @param mapperUrl Mapper specifies the JSONNet code snippet which uses the OpenID Connect Provider's data (e.g. GitHub or Google profile information) to hydrate the identity's data.
 * @param organizationId 
 * @param projectRevisionId The Revision's ID this schema belongs to
 * @param provider Provider is either \"generic\" for a generic OAuth 2.0 / OpenID Connect Provider or one of: generic google github gitlab microsoft discord slack facebook vk yandex apple
 * @param providerId ID is the provider's ID
 * @param requestedClaims 
 * @param scope 
 * @param state State indicates the state of the provider  Only providers with state `enabled` will be used for authentication enabled ThirdPartyProviderStateEnabled disabled ThirdPartyProviderStateDisabled
 * @param subjectSource 
 * @param tokenUrl TokenURL is the token url, typically something like: https://example.org/oauth2/token  Should only be used when the OAuth2 / OpenID Connect server is not supporting OpenID Connect Discovery and when `provider` is set to `generic`.
 * @param updatedAt Last Time Project's Revision was Updated
 */
@Serializable

data class NormalizedProjectRevisionThirdPartyProvider (

    @SerialName(value = "additional_id_token_audiences") val additionalIdTokenAudiences: kotlin.Array<kotlin.String>? = null,

    @SerialName(value = "apple_private_key") val applePrivateKey: kotlin.String? = null,

    /* Apple Private Key Identifier  Sign In with Apple Private Key Identifier needed for generating a JWT token for client secret */
    @SerialName(value = "apple_private_key_id") val applePrivateKeyId: kotlin.String? = null,

    /* Apple Developer Team ID  Apple Developer Team ID needed for generating a JWT token for client secret */
    @SerialName(value = "apple_team_id") val appleTeamId: kotlin.String? = null,

    /* AuthURL is the authorize url, typically something like: https://example.org/oauth2/auth Should only be used when the OAuth2 / OpenID Connect server is not supporting OpenID Connect Discovery and when `provider` is set to `generic`. */
    @SerialName(value = "auth_url") val authUrl: kotlin.String? = null,

    /* Tenant is the Azure AD Tenant to use for authentication, and must be set when `provider` is set to `microsoft`.  Can be either `common`, `organizations`, `consumers` for a multitenant application or a specific tenant like `8eaef023-2b34-4da1-9baa-8bc8c9d6a490` or `contoso.onmicrosoft.com`. */
    @SerialName(value = "azure_tenant") val azureTenant: kotlin.String? = null,

    @SerialName(value = "claims_source") val claimsSource: kotlin.String? = null,

    /* ClientID is the application's Client ID. */
    @SerialName(value = "client_id") val clientId: kotlin.String? = null,

    @SerialName(value = "client_secret") val clientSecret: kotlin.String? = null,

    /* The Project's Revision Creation Date */
    @SerialName(value = "created_at") val createdAt: kotlinx.datetime.Instant? = null,

    @SerialName(value = "id") val id: kotlin.String? = null,

    /* IssuerURL is the OpenID Connect Server URL. You can leave this empty if `provider` is not set to `generic`. If set, neither `auth_url` nor `token_url` are required. */
    @SerialName(value = "issuer_url") val issuerUrl: kotlin.String? = null,

    /* Label represents an optional label which can be used in the UI generation. */
    @SerialName(value = "label") val label: kotlin.String? = null,

    /* Mapper specifies the JSONNet code snippet which uses the OpenID Connect Provider's data (e.g. GitHub or Google profile information) to hydrate the identity's data. */
    @SerialName(value = "mapper_url") val mapperUrl: kotlin.String? = null,

    @SerialName(value = "organization_id") val organizationId: kotlin.String? = null,

    /* The Revision's ID this schema belongs to */
    @SerialName(value = "project_revision_id") val projectRevisionId: kotlin.String? = null,

    /* Provider is either \"generic\" for a generic OAuth 2.0 / OpenID Connect Provider or one of: generic google github gitlab microsoft discord slack facebook vk yandex apple */
    @SerialName(value = "provider") val provider: kotlin.String? = null,

    /* ID is the provider's ID */
    @SerialName(value = "provider_id") val providerId: kotlin.String? = null,

    @SerialName(value = "requested_claims") val requestedClaims: kotlin.String? = null,

    @SerialName(value = "scope") val scope: kotlin.Array<kotlin.String>? = null,

    /* State indicates the state of the provider  Only providers with state `enabled` will be used for authentication enabled ThirdPartyProviderStateEnabled disabled ThirdPartyProviderStateDisabled */
    @SerialName(value = "state") val state: NormalizedProjectRevisionThirdPartyProvider.State? = null,

    @SerialName(value = "subject_source") val subjectSource: kotlin.String? = null,

    /* TokenURL is the token url, typically something like: https://example.org/oauth2/token  Should only be used when the OAuth2 / OpenID Connect server is not supporting OpenID Connect Discovery and when `provider` is set to `generic`. */
    @SerialName(value = "token_url") val tokenUrl: kotlin.String? = null,

    /* Last Time Project's Revision was Updated */
    @SerialName(value = "updated_at") val updatedAt: kotlinx.datetime.Instant? = null

) {

    /**
     * State indicates the state of the provider  Only providers with state `enabled` will be used for authentication enabled ThirdPartyProviderStateEnabled disabled ThirdPartyProviderStateDisabled
     *
     * Values: ENABLED,DISABLED
     */
    @Serializable
    enum class State(val value: kotlin.String) {
        @SerialName(value = "enabled") ENABLED("enabled"),
        @SerialName(value = "disabled") DISABLED("disabled");
    }
}

