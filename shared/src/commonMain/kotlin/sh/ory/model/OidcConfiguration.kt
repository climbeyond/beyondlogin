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

import sh.ory.model.CredentialSupportedDraft00

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Includes links to several endpoints (for example `/oauth2/token`) and exposes information on supported signature algorithms among others.
 *
 * @param authorizationEndpoint OAuth 2.0 Authorization Endpoint URL
 * @param idTokenSignedResponseAlg OpenID Connect Default ID Token Signing Algorithms  Algorithm used to sign OpenID Connect ID Tokens.
 * @param idTokenSigningAlgValuesSupported OpenID Connect Supported ID Token Signing Algorithms  JSON array containing a list of the JWS signing algorithms (alg values) supported by the OP for the ID Token to encode the Claims in a JWT.
 * @param issuer OpenID Connect Issuer URL  An URL using the https scheme with no query or fragment component that the OP asserts as its IssuerURL Identifier. If IssuerURL discovery is supported , this value MUST be identical to the issuer value returned by WebFinger. This also MUST be identical to the iss Claim value in ID Tokens issued from this IssuerURL.
 * @param jwksUri OpenID Connect Well-Known JSON Web Keys URL  URL of the OP's JSON Web Key Set [JWK] document. This contains the signing key(s) the RP uses to validate signatures from the OP. The JWK Set MAY also contain the Server's encryption key(s), which are used by RPs to encrypt requests to the Server. When both signing and encryption keys are made available, a use (Key Use) parameter value is REQUIRED for all keys in the referenced JWK Set to indicate each key's intended usage. Although some algorithms allow the same key to be used for both signatures and encryption, doing so is NOT RECOMMENDED, as it is less secure. The JWK x5c parameter MAY be used to provide X.509 representations of keys provided. When used, the bare key values MUST still be present and MUST match those in the certificate.
 * @param responseTypesSupported OAuth 2.0 Supported Response Types  JSON array containing a list of the OAuth 2.0 response_type values that this OP supports. Dynamic OpenID Providers MUST support the code, id_token, and the token id_token Response Type values.
 * @param subjectTypesSupported OpenID Connect Supported Subject Types  JSON array containing a list of the Subject Identifier types that this OP supports. Valid types include pairwise and public.
 * @param tokenEndpoint OAuth 2.0 Token Endpoint URL
 * @param userinfoSignedResponseAlg OpenID Connect User Userinfo Signing Algorithm  Algorithm used to sign OpenID Connect Userinfo Responses.
 * @param backchannelLogoutSessionSupported OpenID Connect Back-Channel Logout Session Required  Boolean value specifying whether the OP can pass a sid (session ID) Claim in the Logout Token to identify the RP session with the OP. If supported, the sid Claim is also included in ID Tokens issued by the OP
 * @param backchannelLogoutSupported OpenID Connect Back-Channel Logout Supported  Boolean value specifying whether the OP supports back-channel logout, with true indicating support.
 * @param claimsParameterSupported OpenID Connect Claims Parameter Parameter Supported  Boolean value specifying whether the OP supports use of the claims parameter, with true indicating support.
 * @param claimsSupported OpenID Connect Supported Claims  JSON array containing a list of the Claim Names of the Claims that the OpenID Provider MAY be able to supply values for. Note that for privacy or other reasons, this might not be an exhaustive list.
 * @param codeChallengeMethodsSupported OAuth 2.0 PKCE Supported Code Challenge Methods  JSON array containing a list of Proof Key for Code Exchange (PKCE) [RFC7636] code challenge methods supported by this authorization server.
 * @param credentialsEndpointDraft00 OpenID Connect Verifiable Credentials Endpoint  Contains the URL of the Verifiable Credentials Endpoint.
 * @param credentialsSupportedDraft00 OpenID Connect Verifiable Credentials Supported  JSON array containing a list of the Verifiable Credentials supported by this authorization server.
 * @param endSessionEndpoint OpenID Connect End-Session Endpoint  URL at the OP to which an RP can perform a redirect to request that the End-User be logged out at the OP.
 * @param frontchannelLogoutSessionSupported OpenID Connect Front-Channel Logout Session Required  Boolean value specifying whether the OP can pass iss (issuer) and sid (session ID) query parameters to identify the RP session with the OP when the frontchannel_logout_uri is used. If supported, the sid Claim is also included in ID Tokens issued by the OP.
 * @param frontchannelLogoutSupported OpenID Connect Front-Channel Logout Supported  Boolean value specifying whether the OP supports HTTP-based logout, with true indicating support.
 * @param grantTypesSupported OAuth 2.0 Supported Grant Types  JSON array containing a list of the OAuth 2.0 Grant Type values that this OP supports.
 * @param registrationEndpoint OpenID Connect Dynamic Client Registration Endpoint URL
 * @param requestObjectSigningAlgValuesSupported OpenID Connect Supported Request Object Signing Algorithms  JSON array containing a list of the JWS signing algorithms (alg values) supported by the OP for Request Objects, which are described in Section 6.1 of OpenID Connect Core 1.0 [OpenID.Core]. These algorithms are used both when the Request Object is passed by value (using the request parameter) and when it is passed by reference (using the request_uri parameter).
 * @param requestParameterSupported OpenID Connect Request Parameter Supported  Boolean value specifying whether the OP supports use of the request parameter, with true indicating support.
 * @param requestUriParameterSupported OpenID Connect Request URI Parameter Supported  Boolean value specifying whether the OP supports use of the request_uri parameter, with true indicating support.
 * @param requireRequestUriRegistration OpenID Connect Requires Request URI Registration  Boolean value specifying whether the OP requires any request_uri values used to be pre-registered using the request_uris registration parameter.
 * @param responseModesSupported OAuth 2.0 Supported Response Modes  JSON array containing a list of the OAuth 2.0 response_mode values that this OP supports.
 * @param revocationEndpoint OAuth 2.0 Token Revocation URL  URL of the authorization server's OAuth 2.0 revocation endpoint.
 * @param scopesSupported OAuth 2.0 Supported Scope Values  JSON array containing a list of the OAuth 2.0 [RFC6749] scope values that this server supports. The server MUST support the openid scope value. Servers MAY choose not to advertise some supported scope values even when this parameter is used
 * @param tokenEndpointAuthMethodsSupported OAuth 2.0 Supported Client Authentication Methods  JSON array containing a list of Client Authentication methods supported by this Token Endpoint. The options are client_secret_post, client_secret_basic, client_secret_jwt, and private_key_jwt, as described in Section 9 of OpenID Connect Core 1.0
 * @param userinfoEndpoint OpenID Connect Userinfo URL  URL of the OP's UserInfo Endpoint.
 * @param userinfoSigningAlgValuesSupported OpenID Connect Supported Userinfo Signing Algorithm  JSON array containing a list of the JWS [JWS] signing algorithms (alg values) [JWA] supported by the UserInfo Endpoint to encode the Claims in a JWT [JWT].
 */
@Serializable

data class OidcConfiguration (

    /* OAuth 2.0 Authorization Endpoint URL */
    @SerialName(value = "authorization_endpoint") @Required val authorizationEndpoint: kotlin.String,

    /* OpenID Connect Default ID Token Signing Algorithms  Algorithm used to sign OpenID Connect ID Tokens. */
    @SerialName(value = "id_token_signed_response_alg") @Required val idTokenSignedResponseAlg: kotlin.Array<kotlin.String>,

    /* OpenID Connect Supported ID Token Signing Algorithms  JSON array containing a list of the JWS signing algorithms (alg values) supported by the OP for the ID Token to encode the Claims in a JWT. */
    @SerialName(value = "id_token_signing_alg_values_supported") @Required val idTokenSigningAlgValuesSupported: kotlin.Array<kotlin.String>,

    /* OpenID Connect Issuer URL  An URL using the https scheme with no query or fragment component that the OP asserts as its IssuerURL Identifier. If IssuerURL discovery is supported , this value MUST be identical to the issuer value returned by WebFinger. This also MUST be identical to the iss Claim value in ID Tokens issued from this IssuerURL. */
    @SerialName(value = "issuer") @Required val issuer: kotlin.String,

    /* OpenID Connect Well-Known JSON Web Keys URL  URL of the OP's JSON Web Key Set [JWK] document. This contains the signing key(s) the RP uses to validate signatures from the OP. The JWK Set MAY also contain the Server's encryption key(s), which are used by RPs to encrypt requests to the Server. When both signing and encryption keys are made available, a use (Key Use) parameter value is REQUIRED for all keys in the referenced JWK Set to indicate each key's intended usage. Although some algorithms allow the same key to be used for both signatures and encryption, doing so is NOT RECOMMENDED, as it is less secure. The JWK x5c parameter MAY be used to provide X.509 representations of keys provided. When used, the bare key values MUST still be present and MUST match those in the certificate. */
    @SerialName(value = "jwks_uri") @Required val jwksUri: kotlin.String,

    /* OAuth 2.0 Supported Response Types  JSON array containing a list of the OAuth 2.0 response_type values that this OP supports. Dynamic OpenID Providers MUST support the code, id_token, and the token id_token Response Type values. */
    @SerialName(value = "response_types_supported") @Required val responseTypesSupported: kotlin.Array<kotlin.String>,

    /* OpenID Connect Supported Subject Types  JSON array containing a list of the Subject Identifier types that this OP supports. Valid types include pairwise and public. */
    @SerialName(value = "subject_types_supported") @Required val subjectTypesSupported: kotlin.Array<kotlin.String>,

    /* OAuth 2.0 Token Endpoint URL */
    @SerialName(value = "token_endpoint") @Required val tokenEndpoint: kotlin.String,

    /* OpenID Connect User Userinfo Signing Algorithm  Algorithm used to sign OpenID Connect Userinfo Responses. */
    @SerialName(value = "userinfo_signed_response_alg") @Required val userinfoSignedResponseAlg: kotlin.Array<kotlin.String>,

    /* OpenID Connect Back-Channel Logout Session Required  Boolean value specifying whether the OP can pass a sid (session ID) Claim in the Logout Token to identify the RP session with the OP. If supported, the sid Claim is also included in ID Tokens issued by the OP */
    @SerialName(value = "backchannel_logout_session_supported") val backchannelLogoutSessionSupported: kotlin.Boolean? = null,

    /* OpenID Connect Back-Channel Logout Supported  Boolean value specifying whether the OP supports back-channel logout, with true indicating support. */
    @SerialName(value = "backchannel_logout_supported") val backchannelLogoutSupported: kotlin.Boolean? = null,

    /* OpenID Connect Claims Parameter Parameter Supported  Boolean value specifying whether the OP supports use of the claims parameter, with true indicating support. */
    @SerialName(value = "claims_parameter_supported") val claimsParameterSupported: kotlin.Boolean? = null,

    /* OpenID Connect Supported Claims  JSON array containing a list of the Claim Names of the Claims that the OpenID Provider MAY be able to supply values for. Note that for privacy or other reasons, this might not be an exhaustive list. */
    @SerialName(value = "claims_supported") val claimsSupported: kotlin.Array<kotlin.String>? = null,

    /* OAuth 2.0 PKCE Supported Code Challenge Methods  JSON array containing a list of Proof Key for Code Exchange (PKCE) [RFC7636] code challenge methods supported by this authorization server. */
    @SerialName(value = "code_challenge_methods_supported") val codeChallengeMethodsSupported: kotlin.Array<kotlin.String>? = null,

    /* OpenID Connect Verifiable Credentials Endpoint  Contains the URL of the Verifiable Credentials Endpoint. */
    @SerialName(value = "credentials_endpoint_draft_00") val credentialsEndpointDraft00: kotlin.String? = null,

    /* OpenID Connect Verifiable Credentials Supported  JSON array containing a list of the Verifiable Credentials supported by this authorization server. */
    @SerialName(value = "credentials_supported_draft_00") val credentialsSupportedDraft00: kotlin.Array<CredentialSupportedDraft00>? = null,

    /* OpenID Connect End-Session Endpoint  URL at the OP to which an RP can perform a redirect to request that the End-User be logged out at the OP. */
    @SerialName(value = "end_session_endpoint") val endSessionEndpoint: kotlin.String? = null,

    /* OpenID Connect Front-Channel Logout Session Required  Boolean value specifying whether the OP can pass iss (issuer) and sid (session ID) query parameters to identify the RP session with the OP when the frontchannel_logout_uri is used. If supported, the sid Claim is also included in ID Tokens issued by the OP. */
    @SerialName(value = "frontchannel_logout_session_supported") val frontchannelLogoutSessionSupported: kotlin.Boolean? = null,

    /* OpenID Connect Front-Channel Logout Supported  Boolean value specifying whether the OP supports HTTP-based logout, with true indicating support. */
    @SerialName(value = "frontchannel_logout_supported") val frontchannelLogoutSupported: kotlin.Boolean? = null,

    /* OAuth 2.0 Supported Grant Types  JSON array containing a list of the OAuth 2.0 Grant Type values that this OP supports. */
    @SerialName(value = "grant_types_supported") val grantTypesSupported: kotlin.Array<kotlin.String>? = null,

    /* OpenID Connect Dynamic Client Registration Endpoint URL */
    @SerialName(value = "registration_endpoint") val registrationEndpoint: kotlin.String? = null,

    /* OpenID Connect Supported Request Object Signing Algorithms  JSON array containing a list of the JWS signing algorithms (alg values) supported by the OP for Request Objects, which are described in Section 6.1 of OpenID Connect Core 1.0 [OpenID.Core]. These algorithms are used both when the Request Object is passed by value (using the request parameter) and when it is passed by reference (using the request_uri parameter). */
    @SerialName(value = "request_object_signing_alg_values_supported") val requestObjectSigningAlgValuesSupported: kotlin.Array<kotlin.String>? = null,

    /* OpenID Connect Request Parameter Supported  Boolean value specifying whether the OP supports use of the request parameter, with true indicating support. */
    @SerialName(value = "request_parameter_supported") val requestParameterSupported: kotlin.Boolean? = null,

    /* OpenID Connect Request URI Parameter Supported  Boolean value specifying whether the OP supports use of the request_uri parameter, with true indicating support. */
    @SerialName(value = "request_uri_parameter_supported") val requestUriParameterSupported: kotlin.Boolean? = null,

    /* OpenID Connect Requires Request URI Registration  Boolean value specifying whether the OP requires any request_uri values used to be pre-registered using the request_uris registration parameter. */
    @SerialName(value = "require_request_uri_registration") val requireRequestUriRegistration: kotlin.Boolean? = null,

    /* OAuth 2.0 Supported Response Modes  JSON array containing a list of the OAuth 2.0 response_mode values that this OP supports. */
    @SerialName(value = "response_modes_supported") val responseModesSupported: kotlin.Array<kotlin.String>? = null,

    /* OAuth 2.0 Token Revocation URL  URL of the authorization server's OAuth 2.0 revocation endpoint. */
    @SerialName(value = "revocation_endpoint") val revocationEndpoint: kotlin.String? = null,

    /* OAuth 2.0 Supported Scope Values  JSON array containing a list of the OAuth 2.0 [RFC6749] scope values that this server supports. The server MUST support the openid scope value. Servers MAY choose not to advertise some supported scope values even when this parameter is used */
    @SerialName(value = "scopes_supported") val scopesSupported: kotlin.Array<kotlin.String>? = null,

    /* OAuth 2.0 Supported Client Authentication Methods  JSON array containing a list of Client Authentication methods supported by this Token Endpoint. The options are client_secret_post, client_secret_basic, client_secret_jwt, and private_key_jwt, as described in Section 9 of OpenID Connect Core 1.0 */
    @SerialName(value = "token_endpoint_auth_methods_supported") val tokenEndpointAuthMethodsSupported: kotlin.Array<kotlin.String>? = null,

    /* OpenID Connect Userinfo URL  URL of the OP's UserInfo Endpoint. */
    @SerialName(value = "userinfo_endpoint") val userinfoEndpoint: kotlin.String? = null,

    /* OpenID Connect Supported Userinfo Signing Algorithm  JSON array containing a list of the JWS [JWS] signing algorithms (alg values) [JWA] supported by the UserInfo Endpoint to encode the Claims in a JWT [JWT]. */
    @SerialName(value = "userinfo_signing_alg_values_supported") val userinfoSigningAlgValuesSupported: kotlin.Array<kotlin.String>? = null

)
