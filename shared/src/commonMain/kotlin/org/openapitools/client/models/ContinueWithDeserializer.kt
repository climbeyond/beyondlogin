package org.openapitools.client.models

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object ContinueWithDeserializer : JsonContentPolymorphicSerializer<ContinueWith>(ContinueWith::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<ContinueWith> {
        val nodeType = element.jsonObject["action"]

        return when (nodeType?.jsonPrimitive?.content) {
            "show_recovery_ui" -> ContinueWithRecoveryUi.serializer() as DeserializationStrategy<ContinueWith>
            "redirect_browser_to" -> ContinueWithRedirectBrowserTo.serializer() as DeserializationStrategy<ContinueWith>
            "set_ory_session_token" -> ContinueWithSetOrySessionToken.serializer() as DeserializationStrategy<ContinueWith>
            "show_settings_ui" -> ContinueWithSettingsUi.serializer() as DeserializationStrategy<ContinueWith>
            "show_verification_ui" -> ContinueWithVerificationUi.serializer() as DeserializationStrategy<ContinueWith>
            else -> throw IllegalArgumentException(
                "ContinueWithDeserializer - Unknown node_type $nodeType")
        }
    }
}