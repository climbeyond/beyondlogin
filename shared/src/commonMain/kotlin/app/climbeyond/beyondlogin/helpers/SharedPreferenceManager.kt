package app.climbeyond.beyondlogin.helpers

import app.climbeyond.beyondlogin.BeyondLoginPlatform

const val BEYOND_LOGIN_SESSION_ID = "BEYOND_LOGIN_SESSION_ID"
const val BEYOND_LOGIN_SESSION_TOKEN = "BEYOND_LOGIN_SESSION_TOKEN"
const val BEYOND_LOGIN_SESSION_EXPIRE = "BEYOND_LOGIN_SESSION_EXPIRE"
const val BEYOND_LOGIN_SESSION_IDENTITY_ID = "BEYOND_LOGIN_SESSION_ACCOUNT_ID"
const val BEYOND_LOGIN_SETTINGS = "BEYOND_LOGIN_SETTINGS"

expect object SharedPreferenceManager {

    fun delete(platform: BeyondLoginPlatform, key: String)

    /**
     * Reads String value from shared preferences.
     */
    fun read(platform: BeyondLoginPlatform, key: String, defaultValue: String?): String?

    /**
     * Reads long value from shared preferences.
     */
    fun readLong(platform: BeyondLoginPlatform, key: String, defaultValue: Long): Long

    /**
     * Write long shared preference value.
     */
    fun writeLong(platform: BeyondLoginPlatform, key: String, value: Long)

    /**
     * Write string shared preference value.
     */
    fun write(platform: BeyondLoginPlatform, key: String, value: String)
}
