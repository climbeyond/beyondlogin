package app.climbeyond.beyondlogin.helpers

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import app.climbeyond.beyondlogin.BeyondLogin
import app.climbeyond.beyondlogin.BeyondLoginPlatform


actual object SharedPreferenceManager {

    actual fun delete(platform: BeyondLoginPlatform, key: String) {
        PreferenceManager.getDefaultSharedPreferences(platform.context).edit().remove(key).apply()
    }

    /**
     * Reads String value from shared preferences.
     */
    actual fun read(platform: BeyondLoginPlatform, key: String, defaultValue: String?): String? {
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                platform.context)
        return sharedPref.getString(key, defaultValue)
    }

    /**
     * Reads long value from shared preferences.
     */
    actual fun readLong(platform: BeyondLoginPlatform, key: String, defaultValue: Long): Long {
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                platform.context)
        return sharedPref.getLong(key, defaultValue)
    }

    /**
     * Write long shared preference value.
     */
    actual fun writeLong(platform: BeyondLoginPlatform, key: String, value: Long) {
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                platform.context)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * Write string shared preference value.
     */
    actual fun write(platform: BeyondLoginPlatform, key: String, value: String) {
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                platform.context)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }
}