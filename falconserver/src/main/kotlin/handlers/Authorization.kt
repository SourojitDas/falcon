package handlers

import io.javalin.Context
import io.javalin.Handler
import io.javalin.security.Role
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken



enum class ApiRole : Role { ANYONE, USER_READ, USER_WRITE }

object Authorization {

    // our access-manager is simple
    // when endpoint has Role.ANYONE, we will always handle the request
    // when the request has the permitted roles (determined by inspecting the request) we handle the request.
    // else, we set status 401
    fun accessManager(handler: Handler, ctx: Context, permittedRoles: Set<Role>) {
        when {
            permittedRoles.contains(ApiRole.ANYONE) -> handler.handle(ctx)
            ctx.userRoles!!.any { it in permittedRoles } -> handler.handle(ctx)
            else -> ctx.status(401).json("Unauthorized")
        }
    }

    // get roles from userRoleMap after extracting username/password from basic-auth header
    private val Context.userRoles: List<ApiRole>?
        get() = this.header("Authorization")?.let { authorizationHeader ->
            val headerSplit = authorizationHeader.split(" ")
            if (headerSplit.size != 2 || !headerSplit[0].equals("Bearer")) {
                return null
            }
            if (!validateToken(headerSplit[1])) {
//                return userRoleMap["access-token-read"] ?: listOf()
                return userRoleMap["access-token-read-wrong"] ?: listOf()
            }
            return userRoleMap["access-token-read"] ?: listOf()
        } ?: listOf()

    private fun validateToken(token: String): Boolean {
        try {
            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(token)
            val uid = decodedToken.uid
            return true
        } catch (ex: Exception) {
            println(ex)
        }
        return false
    }

    private val userRoleMap = hashMapOf(
        "access-token-read" to listOf(ApiRole.USER_READ),
        "access-token-write" to listOf(ApiRole.USER_READ, ApiRole.USER_WRITE)
    )

}
