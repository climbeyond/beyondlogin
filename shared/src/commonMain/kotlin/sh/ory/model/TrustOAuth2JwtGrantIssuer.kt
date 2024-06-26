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

import sh.ory.model.JsonWebKey

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Trust OAuth2 JWT Bearer Grant Type Issuer Request Body
 *
 * @param expiresAt The \"expires_at\" indicates, when grant will expire, so we will reject assertion from \"issuer\" targeting \"subject\".
 * @param issuer The \"issuer\" identifies the principal that issued the JWT assertion (same as \"iss\" claim in JWT).
 * @param jwk 
 * @param scope The \"scope\" contains list of scope values (as described in Section 3.3 of OAuth 2.0 [RFC6749])
 * @param allowAnySubject The \"allow_any_subject\" indicates that the issuer is allowed to have any principal as the subject of the JWT.
 * @param subject The \"subject\" identifies the principal that is the subject of the JWT.
 */
@Serializable

data class TrustOAuth2JwtGrantIssuer (

    /* The \"expires_at\" indicates, when grant will expire, so we will reject assertion from \"issuer\" targeting \"subject\". */
    @SerialName(value = "expires_at") @Required val expiresAt: kotlinx.datetime.Instant,

    /* The \"issuer\" identifies the principal that issued the JWT assertion (same as \"iss\" claim in JWT). */
    @SerialName(value = "issuer") @Required val issuer: kotlin.String,

    @SerialName(value = "jwk") @Required val jwk: JsonWebKey,

    /* The \"scope\" contains list of scope values (as described in Section 3.3 of OAuth 2.0 [RFC6749]) */
    @SerialName(value = "scope") @Required val scope: kotlin.Array<kotlin.String>,

    /* The \"allow_any_subject\" indicates that the issuer is allowed to have any principal as the subject of the JWT. */
    @SerialName(value = "allow_any_subject") val allowAnySubject: kotlin.Boolean? = null,

    /* The \"subject\" identifies the principal that is the subject of the JWT. */
    @SerialName(value = "subject") val subject: kotlin.String? = null

)

