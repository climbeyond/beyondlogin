
package org.openapitools.client.models

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object UpdateLoginFlowBodyDeserializer : JsonContentPolymorphicSerializer<UpdateLoginFlowBody>(UpdateLoginFlowBody::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<UpdateLoginFlowBody> {
        val method = element.jsonObject["method"]

        return when (method?.jsonPrimitive?.content) {
            "password" -> UpdateLoginFlowWithPasswordMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            "oidc" -> UpdateLoginFlowWithOidcMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            "totp" -> UpdateLoginFlowWithTotpMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            "webauthn" -> UpdateLoginFlowWithWebAuthnMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            "passkey" -> UpdateLoginFlowWithPasskeyMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            "lookup_secret" -> UpdateLoginFlowWithLookupSecretMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            "identifier_first" -> UpdateLoginFlowWithIdentifierFirstMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            "code" -> UpdateLoginFlowWithCodeMethod.serializer() as DeserializationStrategy<UpdateLoginFlowBody>
            else -> throw kotlinx.serialization.SerializationException(
                "UpdateLoginFlowBodyDeserializer - Unknown method: $method")
        }
    }
}
