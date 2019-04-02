package services

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuth : FirebaseAuthInterface {
    override fun isTokenValid(token: String?): Boolean {
        try {
            FirebaseAuth.getInstance().verifyIdToken(token)
//            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)
//            val uid = decodedToken.uid
            return true
        } catch (ex: Exception) {
            println(ex)
        }
        return false
    }
}

interface FirebaseAuthInterface {
    fun isTokenValid(token: String?): Boolean
}