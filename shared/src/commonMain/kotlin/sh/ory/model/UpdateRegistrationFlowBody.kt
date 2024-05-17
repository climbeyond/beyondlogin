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

import sh.ory.model.UpdateRegistrationFlowWithOidcMethod
import sh.ory.model.UpdateRegistrationFlowWithPasswordMethod
import sh.ory.model.UpdateRegistrationFlowWithWebAuthnMethod

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Update Registration Request Body
 *
 * @param method Method  Should be set to \"webauthn\" when trying to add, update, or remove a webAuthn pairing.
 * @param password Password to sign the user up with
 * @param traits The identity's traits
 * @param provider The provider to register with
 * @param csrfToken CSRFToken is the anti-CSRF token
 * @param transientPayload Transient data to pass along to any webhooks
 * @param upstreamParameters UpstreamParameters are the parameters that are passed to the upstream identity provider.  These parameters are optional and depend on what the upstream identity provider supports. Supported parameters are: `login_hint` (string): The `login_hint` parameter suppresses the account chooser and either pre-fills the email box on the sign-in form, or selects the proper session. `hd` (string): The `hd` parameter limits the login/registration process to a Google Organization, e.g. `mycollege.edu`. `prompt` (string): The `prompt` specifies whether the Authorization Server prompts the End-User for reauthentication and consent, e.g. `select_account`.
 * @param webauthnRegister Register a WebAuthn Security Key  It is expected that the JSON returned by the WebAuthn registration process is included here.
 * @param webauthnRegisterDisplayname Name of the WebAuthn Security Key to be Added  A human-readable name for the security key which will be added.
 */


interface UpdateRegistrationFlowBody {

    /* Method  Should be set to \"webauthn\" when trying to add, update, or remove a webAuthn pairing. */
    @SerialName(value = "method") @Required val method: kotlin.String
    /* Password to sign the user up with */
    @SerialName(value = "password") @Required val password: kotlin.String
    /* The identity's traits */
    @SerialName(value = "traits") @Required val traits: kotlin.String
    /* The provider to register with */
    @SerialName(value = "provider") @Required val provider: kotlin.String
    /* CSRFToken is the anti-CSRF token */
    @SerialName(value = "csrf_token") val csrfToken: kotlin.String?
    /* Transient data to pass along to any webhooks */
    @SerialName(value = "transient_payload") val transientPayload: kotlin.String?
    /* UpstreamParameters are the parameters that are passed to the upstream identity provider.  These parameters are optional and depend on what the upstream identity provider supports. Supported parameters are: `login_hint` (string): The `login_hint` parameter suppresses the account chooser and either pre-fills the email box on the sign-in form, or selects the proper session. `hd` (string): The `hd` parameter limits the login/registration process to a Google Organization, e.g. `mycollege.edu`. `prompt` (string): The `prompt` specifies whether the Authorization Server prompts the End-User for reauthentication and consent, e.g. `select_account`. */
    @SerialName(value = "upstream_parameters") val upstreamParameters: kotlin.String?
    /* Register a WebAuthn Security Key  It is expected that the JSON returned by the WebAuthn registration process is included here. */
    @SerialName(value = "webauthn_register") val webauthnRegister: kotlin.String?
    /* Name of the WebAuthn Security Key to be Added  A human-readable name for the security key which will be added. */
    @SerialName(value = "webauthn_register_displayname") val webauthnRegisterDisplayname: kotlin.String?
}
