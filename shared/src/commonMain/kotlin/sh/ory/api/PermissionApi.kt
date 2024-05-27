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

import sh.ory.model.CheckPermissionResult
import sh.ory.model.ErrorGeneric
import sh.ory.model.ExpandedPermissionTree
import sh.ory.model.PostCheckPermissionBody
import sh.ory.model.PostCheckPermissionOrErrorBody

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

open class PermissionApi : ApiClient {

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
     * Check a permission
     * To learn how relationship tuples and the check works, head over to [the documentation](https://www.ory.sh/docs/keto/concepts/api-overview).
     * @param namespace Namespace of the Relationship (optional)
     * @param `object` Object of the Relationship (optional)
     * @param relation Relation of the Relationship (optional)
     * @param subjectId SubjectID of the Relationship (optional)
     * @param subjectSetNamespace Namespace of the Subject Set (optional)
     * @param subjectSetObject Object of the Subject Set (optional)
     * @param subjectSetRelation Relation of the Subject Set (optional)
     * @param maxDepth  (optional)
     * @return CheckPermissionResult
     */
    @Suppress("UNCHECKED_CAST")
    open suspend fun checkPermission(namespace: kotlin.String? = null, `object`: kotlin.String? = null, relation: kotlin.String? = null, subjectId: kotlin.String? = null, subjectSetNamespace: kotlin.String? = null, subjectSetObject: kotlin.String? = null, subjectSetRelation: kotlin.String? = null, maxDepth: kotlin.Long? = null): HttpResponse<CheckPermissionResult> {

        val localVariableAuthNames = listOf<String>("oryAccessToken")

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        namespace?.apply { localVariableQuery["namespace"] = listOf("$namespace") }
        `object`?.apply { localVariableQuery["object"] = listOf("$`object`") }
        relation?.apply { localVariableQuery["relation"] = listOf("$relation") }
        subjectId?.apply { localVariableQuery["subject_id"] = listOf("$subjectId") }
        subjectSetNamespace?.apply { localVariableQuery["subject_set.namespace"] = listOf("$subjectSetNamespace") }
        subjectSetObject?.apply { localVariableQuery["subject_set.object"] = listOf("$subjectSetObject") }
        subjectSetRelation?.apply { localVariableQuery["subject_set.relation"] = listOf("$subjectSetRelation") }
        maxDepth?.apply { localVariableQuery["max-depth"] = listOf("$maxDepth") }
        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/relation-tuples/check/openapi",
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
     * Check a permission
     * To learn how relationship tuples and the check works, head over to [the documentation](https://www.ory.sh/docs/keto/concepts/api-overview).
     * @param namespace Namespace of the Relationship (optional)
     * @param `object` Object of the Relationship (optional)
     * @param relation Relation of the Relationship (optional)
     * @param subjectId SubjectID of the Relationship (optional)
     * @param subjectSetNamespace Namespace of the Subject Set (optional)
     * @param subjectSetObject Object of the Subject Set (optional)
     * @param subjectSetRelation Relation of the Subject Set (optional)
     * @param maxDepth  (optional)
     * @return CheckPermissionResult
     */
    @Suppress("UNCHECKED_CAST")
    open suspend fun checkPermissionOrError(namespace: kotlin.String? = null, `object`: kotlin.String? = null, relation: kotlin.String? = null, subjectId: kotlin.String? = null, subjectSetNamespace: kotlin.String? = null, subjectSetObject: kotlin.String? = null, subjectSetRelation: kotlin.String? = null, maxDepth: kotlin.Long? = null): HttpResponse<CheckPermissionResult> {

        val localVariableAuthNames = listOf<String>("oryAccessToken")

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        namespace?.apply { localVariableQuery["namespace"] = listOf("$namespace") }
        `object`?.apply { localVariableQuery["object"] = listOf("$`object`") }
        relation?.apply { localVariableQuery["relation"] = listOf("$relation") }
        subjectId?.apply { localVariableQuery["subject_id"] = listOf("$subjectId") }
        subjectSetNamespace?.apply { localVariableQuery["subject_set.namespace"] = listOf("$subjectSetNamespace") }
        subjectSetObject?.apply { localVariableQuery["subject_set.object"] = listOf("$subjectSetObject") }
        subjectSetRelation?.apply { localVariableQuery["subject_set.relation"] = listOf("$subjectSetRelation") }
        maxDepth?.apply { localVariableQuery["max-depth"] = listOf("$maxDepth") }
        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/relation-tuples/check",
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
     * Expand a Relationship into permissions.
     * Use this endpoint to expand a relationship tuple into permissions.
     * @param namespace Namespace of the Subject Set
     * @param `object` Object of the Subject Set
     * @param relation Relation of the Subject Set
     * @param maxDepth  (optional)
     * @return ExpandedPermissionTree
     */
    @Suppress("UNCHECKED_CAST")
    open suspend fun expandPermissions(namespace: kotlin.String, `object`: kotlin.String, relation: kotlin.String, maxDepth: kotlin.Long? = null): HttpResponse<ExpandedPermissionTree> {

        val localVariableAuthNames = listOf<String>("oryAccessToken")

        val localVariableBody = 
            io.ktor.client.utils.EmptyContent

        val localVariableQuery = mutableMapOf<String, List<String>>()
        namespace?.apply { localVariableQuery["namespace"] = listOf("$namespace") }
        `object`?.apply { localVariableQuery["object"] = listOf("$`object`") }
        relation?.apply { localVariableQuery["relation"] = listOf("$relation") }
        maxDepth?.apply { localVariableQuery["max-depth"] = listOf("$maxDepth") }
        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.GET,
            "/relation-tuples/expand",
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
     * Check a permission
     * To learn how relationship tuples and the check works, head over to [the documentation](https://www.ory.sh/docs/keto/concepts/api-overview).
     * @param maxDepth  (optional)
     * @param postCheckPermissionBody  (optional)
     * @return CheckPermissionResult
     */
    @Suppress("UNCHECKED_CAST")
    open suspend fun postCheckPermission(maxDepth: kotlin.Long? = null, postCheckPermissionBody: PostCheckPermissionBody? = null): HttpResponse<CheckPermissionResult> {

        val localVariableAuthNames = listOf<String>("oryAccessToken")

        val localVariableBody = postCheckPermissionBody

        val localVariableQuery = mutableMapOf<String, List<String>>()
        maxDepth?.apply { localVariableQuery["max-depth"] = listOf("$maxDepth") }
        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.POST,
            "/relation-tuples/check/openapi",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }



    /**
     * Check a permission
     * To learn how relationship tuples and the check works, head over to [the documentation](https://www.ory.sh/docs/keto/concepts/api-overview).
     * @param maxDepth  (optional)
     * @param postCheckPermissionOrErrorBody  (optional)
     * @return CheckPermissionResult
     */
    @Suppress("UNCHECKED_CAST")
    open suspend fun postCheckPermissionOrError(maxDepth: kotlin.Long? = null, postCheckPermissionOrErrorBody: PostCheckPermissionOrErrorBody? = null): HttpResponse<CheckPermissionResult> {

        val localVariableAuthNames = listOf<String>("oryAccessToken")

        val localVariableBody = postCheckPermissionOrErrorBody

        val localVariableQuery = mutableMapOf<String, List<String>>()
        maxDepth?.apply { localVariableQuery["max-depth"] = listOf("$maxDepth") }
        val localVariableHeaders = mutableMapOf<String, String>()

        val localVariableConfig = RequestConfig<kotlin.Any?>(
            RequestMethod.POST,
            "/relation-tuples/check",
            query = localVariableQuery,
            headers = localVariableHeaders,
            requiresAuthentication = true,
        )

        return jsonRequest(
            localVariableConfig,
            localVariableBody,
            localVariableAuthNames
        ).wrap()
    }



}
