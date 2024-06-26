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

package sh.ory.api

import sh.ory.model.CourierMessageStatus
import sh.ory.model.ErrorGeneric
import sh.ory.model.Message

import org.openapitools.client.infrastructure.*
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.request.forms.formData
import io.ktor.client.engine.HttpClientEngine
import kotlinx.serialization.json.Json
import io.ktor.http.ParametersBuilder
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

open class CourierApi : ApiClient {

    constructor(
        baseUrl: String = ApiClient.BASE_URL,
        httpClientEngine: HttpClientEngine? = null,
        httpClientConfig: ((HttpClientConfig<*>) -> Unit)? = null,
        jsonSerializer: Json = ApiClient.JSON_DEFAULT
    ) : super(baseUrl = baseUrl, httpClientEngine = httpClientEngine, httpClientConfig = httpClientConfig, jsonBlock = jsonSerializer)

    constructor(
        baseUrl: String,
        httpClient: HttpClient
    ): super(baseUrl = baseUrl, httpClient = httpClient)

    /**
     * Get a Message
     * Gets a specific messages by the given ID.
     * @param id MessageID is the ID of the message.
     * @return Message
     */
    @Suppress("UNCHECKED_CAST")
    open suspend fun getCourierMessage(id: kotlin.String): HttpResponse<Message> {

        val localVariableAuthNames = listOf<String>("oryAccessToken")

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/admin/courier/messages/{id}".replace("{" + "id" + "}", "$id"),
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }


    /**
     * List Messages
     * Lists all messages by given status and recipient.
     * @param pageSize Items per Page  This is the number of items per page to return. For details on pagination please head over to the [pagination documentation](https://www.ory.sh/docs/ecosystem/api-design#pagination). (optional, default to 250L)
     * @param pageToken Next Page Token  The next page token. For details on pagination please head over to the [pagination documentation](https://www.ory.sh/docs/ecosystem/api-design#pagination). (optional)
     * @param status Status filters out messages based on status. If no value is provided, it doesn&#39;t take effect on filter. (optional)
     * @param recipient Recipient filters out messages based on recipient. If no value is provided, it doesn&#39;t take effect on filter. (optional)
     * @return kotlin.Array<Message>
     */
    @Suppress("UNCHECKED_CAST")
    open suspend fun listCourierMessages(pageSize: kotlin.Long? = 250L, pageToken: kotlin.String? = null, status: CourierMessageStatus? = null, recipient: kotlin.String? = null): HttpResponse<kotlin.Array<Message>> {

        val localVariableAuthNames = listOf<String>("oryAccessToken")

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        pageSize?.apply { localVariableQuery["page_size"] = listOf("$pageSize") }
        pageToken?.apply { localVariableQuery["page_token"] = listOf("$pageToken") }
        status?.apply { localVariableQuery["status"] = listOf("$status") }
        recipient?.apply { localVariableQuery["recipient"] = listOf("$recipient") }
        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/admin/courier/messages",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
        )

        return request(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap<ListCourierMessagesResponse>().map { value.toTypedArray() }
    }

    @Serializable(ListCourierMessagesResponse.Companion::class)
    private class ListCourierMessagesResponse(val value: List<Message>) {
        companion object : KSerializer<ListCourierMessagesResponse> {
            private val serializer: KSerializer<List<Message>> = serializer<List<Message>>()
            override val descriptor = serializer.descriptor
            override fun serialize(encoder: Encoder, obj: ListCourierMessagesResponse) = serializer.serialize(encoder, obj.value)
            override fun deserialize(decoder: Decoder) = ListCourierMessagesResponse(serializer.deserialize(decoder))
        }
    }

}
