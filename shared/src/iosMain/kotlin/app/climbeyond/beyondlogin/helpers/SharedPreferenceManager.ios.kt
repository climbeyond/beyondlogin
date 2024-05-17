package app.climbeyond.beyondlogin.helpers

import app.climbeyond.beyondlogin.BeyondLoginPlatform
import platform.Foundation.NSUserDefaults

actual object SharedPreferenceManager {

    actual fun delete(platform: BeyondLoginPlatform, key: String) {
        NSUserDefaults.standardUserDefaults().removeObjectForKey(key)
    }

    /**
     * Reads String value from shared preferences.
     */
    actual fun read(platform: BeyondLoginPlatform, key: String, defaultValue: String?): String? {
        return NSUserDefaults.standardUserDefaults().stringForKey(key)
    }

    /**
     * Reads long value from shared preferences.
     */
    actual fun readLong(platform: BeyondLoginPlatform, key: String, defaultValue: Long): Long {
        return NSUserDefaults.standardUserDefaults().objectForKey(key)?.let { obj ->
            obj as Long
        } ?: defaultValue
    }

    /**
     * Write long shared preference value.
     */
    actual fun writeLong(platform: BeyondLoginPlatform, key: String, value: Long) {
        NSUserDefaults.standardUserDefaults().setInteger(value, key)
    }

    /**
     * Write string shared preference value.
     */
    actual fun write(platform: BeyondLoginPlatform, key: String, value: String) {
        NSUserDefaults.standardUserDefaults().setObject(value, key)
    }
}