package ie.tcd.scss.ase.utilities

import android.content.Context
import ie.tcd.scss.ase.R
import ie.tcd.scss.ase.poko.SharedPreferenceDataClass

class SharedPreferenceHelper(context:Context) {

    val sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_name),Context.MODE_PRIVATE)

    fun savePreference(dataList: ArrayList<SharedPreferenceDataClass>): Boolean {
        val editor = sharedPreferences.edit()
        dataList.forEach {
            if(it.value is Int) {
                editor.putInt(it.key, it.value as Int)
            }else if(it.value is Float) {
                editor.putFloat(it.key, it.value as Float)
            }else if(it.value is Long) {
                editor.putLong(it.key, it.value as Long)
            }else{
                editor.putString(it.key, it.value.toString())
            }
        }
        return editor.commit()
    }

    fun savePreferenceString(dataList: SharedPreferenceDataClass): Boolean {
        val editor = sharedPreferences.edit()
        editor.putString(dataList.key, dataList.value.toString())
        return editor.commit()
    }

    fun savePreferenceBoolean(dataList: SharedPreferenceDataClass): Boolean {
        val editor = sharedPreferences.edit()
        editor.putBoolean(dataList.key, dataList.value as Boolean)
        return editor.commit()
    }

    fun savePreferenceInt(dataList: SharedPreferenceDataClass): Boolean {
        val editor = sharedPreferences.edit()
        editor.putInt(dataList.key, dataList.value as Int)
        return editor.commit()
    }

    fun savePreferenceFloat(dataList: SharedPreferenceDataClass): Boolean {
        val editor = sharedPreferences.edit()
        editor.putFloat(dataList.key, dataList.value as Float)
        return editor.commit()
    }

    fun savePreference(dataList: SharedPreferenceDataClass): Boolean {
        val editor = sharedPreferences.edit()
        if(dataList.value is Int) {
            editor.putInt(dataList.key, dataList.value as Int)
        }else if(dataList.value is Float) {
            editor.putFloat(dataList.key, dataList.value as Float)
        }else if(dataList.value is Long) {
            editor.putLong(dataList.key, dataList.value as Long)
        }else{
            editor.putString(dataList.key, dataList.value.toString())
        }
        return editor.commit()
    }

    fun getPreference(key: String): String {
        return sharedPreferences.getString(key, "")
    }

    fun containPreference(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

}