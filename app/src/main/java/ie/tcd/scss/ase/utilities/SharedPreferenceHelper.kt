package ie.tcd.scss.ase.utilities

import android.content.SharedPreferences
import ie.tcd.scss.ase.poko.SharedPreferenceDataClass

class SharedPreferenceHelper(private val sharedPreferences: SharedPreferences) {

    fun savePreference(dataList:ArrayList<SharedPreferenceDataClass>):Boolean{
        val editor = sharedPreferences.edit()
        dataList.forEach {
            editor.putString(it.key,it.value);
        }
        return editor.commit()
    }

    fun getPreference(key:String):String{

        return sharedPreferences.getString(key,"")
    }

}