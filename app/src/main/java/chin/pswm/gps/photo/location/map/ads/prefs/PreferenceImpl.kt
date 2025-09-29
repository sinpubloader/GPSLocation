package chin.pswm.gps.photo.location.map.ads.prefs

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import kotlin.reflect.KProperty

open class PreferenceImpl(private val app: Application) {

    protected val TAG: String
        get() = this::class.java.simpleName

    companion object {
        private const val PREF_NAME: String = "ic_qibla_pref"
    }

    protected val mSharedPreferences = app.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getInt(name: String, defaultInt: Int): Int {
        return mSharedPreferences.getInt(name, defaultInt)
    }

    fun getBoolean(name: String, defaultBoolean: Boolean): Boolean {
        return mSharedPreferences.getBoolean(name, defaultBoolean)
    }

    fun getString(name: String, defaultString: String): String {
        return mSharedPreferences.getString(name, defaultString) ?: defaultString
    }

    fun getFloat(name: String, defaultFloat: Float): Float {
        return mSharedPreferences.getFloat(name, defaultFloat)
    }

    fun getLong(name: String, defaultValue: Long): Long {
        return mSharedPreferences.getLong(name, defaultValue)
    }

    fun <T> put(key: String, data: T) {
        mSharedPreferences.edit {
            when (data) {
                is String -> putString(key, data)
                is Boolean -> putBoolean(key, data)
                is Float -> putFloat(key, data)
                is Int -> putInt(key, data)
                is Long -> putLong(key, data)
                else -> {
                    val jsonData = Gson().toJson(data)
                    putString(key, jsonData)
                }
            }
        }
    }

    protected fun clear() {
        mSharedPreferences.edit { clear() }
    }

    protected inner class SharedPreferenceProperty<T>(
        private val key: String,
        private val defaultValue: T,
    ) {
        private val clas: Class<T>
            get() = defaultValue!!::class.java as Class<T>

        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return when (defaultValue) {
                is String -> mSharedPreferences.getString(key, defaultValue as String) as T
                is Int -> mSharedPreferences.getInt(key, defaultValue as Int) as T
                is Boolean -> mSharedPreferences.getBoolean(key, defaultValue as Boolean) as T
                is Float -> mSharedPreferences.getFloat(key, defaultValue as Float) as T
                is Double -> mSharedPreferences.getFloat(key, defaultValue as Float).toDouble() as T
                is Long -> mSharedPreferences.getLong(key, defaultValue as Long) as T
                is Enum<*> -> {
                    val enumName = mSharedPreferences.getString(key, "")
                    return try {
                        clas.enumConstants?.firstOrNull { (it as Enum<*>).name == enumName }
                            ?: defaultValue
                    } catch (_: Exception) {
                        defaultValue
                    }
                }

                else -> {
                    val jsonData = mSharedPreferences.getString(key, null)
                    return if (jsonData != null) {
                        try {
                            val data = Gson().fromJson(jsonData, clas)
                            data as T
                        } catch (e: Exception) {
                            e.printStackTrace()
                            return defaultValue as T
                        }
                    } else {
                        return defaultValue as T
                    }
                }
            }
        }

        operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            with(mSharedPreferences.edit()) {
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Float -> putFloat(key, value)
                    is Double -> putFloat(key, value.toFloat())
                    is Long -> putLong(key, value)
                    is Enum<*> -> putString(key, value.name)
                    else -> {
                        val json = Gson().toJson(value)
                        putString(key, json)
                    }
                }
                apply()
            }
        }
    }
}