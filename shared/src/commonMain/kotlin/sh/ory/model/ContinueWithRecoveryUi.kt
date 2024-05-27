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

import sh.ory.model.ContinueWithRecoveryUiFlow

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

/**
 * Indicates, that the UI flow could be continued by showing a recovery ui
 *
 * @param action Action will always be `show_recovery_ui` show_recovery_ui ContinueWithActionShowRecoveryUIString
 * @param flow 
 */
@Serializable

data class ContinueWithRecoveryUi (

    /* Action will always be `show_recovery_ui` show_recovery_ui ContinueWithActionShowRecoveryUIString */
    @SerialName(value = "action") @Required val action: ContinueWithRecoveryUi.Action,

    @SerialName(value = "flow") @Required val flow: ContinueWithRecoveryUiFlow

) {

    /**
     * Action will always be `show_recovery_ui` show_recovery_ui ContinueWithActionShowRecoveryUIString
     *
     * Values: SHOW_RECOVERY_UI
     */
    @Serializable
    enum class Action(val value: kotlin.String) {
        @SerialName(value = "show_recovery_ui") SHOW_RECOVERY_UI("show_recovery_ui");
    }
}
