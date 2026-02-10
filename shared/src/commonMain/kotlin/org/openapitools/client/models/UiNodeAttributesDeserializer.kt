package org.openapitools.client.models

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object UiNodeAttributesDeserializer : JsonContentPolymorphicSerializer<UiNodeAttributes>(UiNodeAttributes::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<UiNodeAttributes> {
        val nodeType = element.jsonObject["node_type"]

        return when (nodeType?.jsonPrimitive?.content) {
            "input" -> UiNodeInputAttributes.serializer() as DeserializationStrategy<UiNodeAttributes>
            "text" -> UiNodeTextAttributes.serializer() as DeserializationStrategy<UiNodeAttributes>
            "img" -> UiNodeImageAttributes.serializer() as DeserializationStrategy<UiNodeAttributes>
            "a" -> UiNodeAnchorAttributes.serializer() as DeserializationStrategy<UiNodeAttributes>
            "script" -> UiNodeScriptAttributes.serializer() as DeserializationStrategy<UiNodeAttributes>
            else -> throw IllegalArgumentException(
                "UiNodeAttributesDeserializer - Unknown node_type: $nodeType")
        }
    }
}