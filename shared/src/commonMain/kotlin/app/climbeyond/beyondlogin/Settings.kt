package app.climbeyond.beyondlogin

import app.climbeyond.beyondlogin.exception.BeyondException
import app.climbeyond.beyondlogin.exception.SettingsException
import app.climbeyond.beyondlogin.helpers.BEYOND_LOGIN_SETTINGS
import app.climbeyond.beyondlogin.helpers.BLLogger
import app.climbeyond.beyondlogin.helpers.SharedPreferenceManager
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


interface SettingsListener {
    fun onSettingsChange()
}

@Serializable
object Settings {

    @Serializable
    data class Data(val kratosUrl: String) {
        internal var logLevel: ApiLogLevel = ApiLogLevel.INFO
        internal var logTag = "BeyondLogin"
    }

    @Serializable
    enum class ApiLogLevel {
        INFO,
        BODY,
        HEADERS,
        ALL
    }

    @Transient
    private var listener: SettingsListener? = null

    var debug = false
        set(value) {
            field = value
            listener?.onSettingsChange()
        }

    internal fun bind(viewService: ViewService) {
        listener = viewService
    }

    fun setLogTag(logTag: String) {
        settings?.logTag = logTag
        BLLogger.tag = logTag
    }

    fun setLogLevel(logLevel: ApiLogLevel) {
        settings?.logLevel = logLevel
    }

    internal var settings: Data? = null

    fun init(platform: BeyondLoginPlatform, data: Data) {
        if (data.kratosUrl.isEmpty()) {
            throw SettingsException("kratosUrl has not been defined")
        }

        BLLogger.tag = data.logTag

        // Store settings
        SharedPreferenceManager.write(platform, BEYOND_LOGIN_SETTINGS, Json.encodeToString(data))

        BLLogger.logDebug("BeyondLogin [Url: ${data.kratosUrl}] [Built: ${beyondLoginBuildTime()}]")

        settings = data
    }

    internal fun load(platform: BeyondLoginPlatform) : Data {
        return settings ?: run {
            SharedPreferenceManager.read(platform, BEYOND_LOGIN_SETTINGS, null)?.let {
                val settings = Json.decodeFromString<Data>(it)

                BLLogger.tag = settings.logTag
                BLLogger.logDebug("Settings loaded")
                return settings

            } ?: run {
                throw BeyondException("BeyondLogin settings not available")
            }
        }
    }
}