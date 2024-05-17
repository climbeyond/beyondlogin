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

import sh.ory.model.IdentityState
import sh.ory.model.IdentityWithCredentials

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.JsonObject

/**
 * Update Identity Body
 *
 * @param schemaId SchemaID is the ID of the JSON Schema to be used for validating the identity's traits. If set will update the Identity's SchemaID.
 * @param state 
 * @param traits Traits represent an identity's traits. The identity is able to create, modify, and delete traits in a self-service manner. The input will always be validated against the JSON Schema defined in `schema_id`.
 * @param credentials 
 * @param metadataAdmin Store metadata about the user which is only accessible through admin APIs such as `GET /admin/identities/<id>`.
 * @param metadataPublic Store metadata about the identity which the identity itself can see when calling for example the session endpoint. Do not store sensitive information (e.g. credit score) about the identity in this field.
 */
@Serializable

data class UpdateIdentityBody (

    /* SchemaID is the ID of the JSON Schema to be used for validating the identity's traits. If set will update the Identity's SchemaID. */
    @SerialName(value = "schema_id") @Required val schemaId: kotlin.String,

    @SerialName(value = "state") @Required val state: IdentityState,

    /* Traits represent an identity's traits. The identity is able to create, modify, and delete traits in a self-service manner. The input will always be validated against the JSON Schema defined in `schema_id`. */
    @SerialName(value = "traits") @Required val traits: kotlin.String,

    @SerialName(value = "credentials") val credentials: IdentityWithCredentials? = null,

    /* Store metadata about the user which is only accessible through admin APIs such as `GET /admin/identities/<id>`. */
    @SerialName(value = "metadata_admin") val metadataAdmin: JsonObject? = null,

    /* Store metadata about the identity which the identity itself can see when calling for example the session endpoint. Do not store sensitive information (e.g. credit score) about the identity in this field. */
    @SerialName(value = "metadata_public") val metadataPublic: JsonObject? = null

)

