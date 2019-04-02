package ie.tcd.scss.ase.utilities

import java.util.regex.Pattern

class ValidationClass {

    val regx : Regex = ".*[a-zA-Z0-9]+.*".toRegex()
    fun isAddresslValid(address: String): Boolean {
        if(address.trim().length>=3){
            if(address.contains(regx)) {
                return Pattern.compile("^[A-Za-z0-9\\.\\-\\s\\,\\/\\n\\r\\t\\(\\)\\']+").matcher(address).matches()
            }else{
                return false
            }
        }
        else return false
    }

}