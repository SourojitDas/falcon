package ie.tcd.scss.ase.utilities

import android.content.SharedPreferences
import ie.tcd.scss.ase.poko.SharedPreferenceDataClass

class SharedPreferenceHelper(private val sharedPreferences: SharedPreferences) {

    fun savePreference(dataList: ArrayList<SharedPreferenceDataClass>): Boolean {
        val editor = sharedPreferences.edit()
        dataList.forEach {
            if(it.value is String) {
                editor.putString(it.key, it.value.toString())
            }
        }
        return editor.commit()
    }

    fun savePreference(dataList: SharedPreferenceDataClass): Boolean {
        val editor = sharedPreferences.edit()
        if(dataList.value is String) {
            editor.putString(dataList.key, dataList.value.toString())
        }
        return editor.commit()
    }

    fun getPreference(key: String): Any {
        return sharedPreferences.getString(key, "")
    }

    fun containPreference(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

}