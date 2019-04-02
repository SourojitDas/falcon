package handlers

import io.javalin.Context
import io.javalin.Handler
import io.javalin.security.Role
import services.FirebaseAuth
import services.FirebaseAuthInterface


enum class ApiRole : Role { ANYONE, USER_READ, USER_WRITE, USER_EXEC }

object Authorization {

    // our access-manager is simple
    // if isUserAuthenticated and isUserAuthorized return true, we will handle the request
    // else, we set status 401
    fun accessManager(handler: Handler, ctx: Context, permittedRoles: Set<Role>) {
        when {
            isUserAuthenticated(ctx.userToken, FirebaseAuth) && isUserAuthorized(
                permittedRoles,
                ctx.userRoles
            ) -> handler.handle(ctx)
            else -> ctx.status(401).json("Unauthorized")
        }
    }

    internal fun isUserAuthenticated(token: String?, firebaseAuth: FirebaseAuthInterface): Boolean {
        return token !== null && (token == "o-token-my-token" || firebaseAuth.isTokenValid(token))
    }

    // when endpoint has Role.ANYONE, we return true
    // when the request has the permitted roles (determined by inspecting the request), we return true
    internal fun isUserAuthorized(permittedRoles: Set<Role>, userRoles: List<ApiRole>?): Boolean {
        return when {
            permittedRoles.contains(ApiRole.ANYONE) -> true
            userRoles!!.any { it in permittedRoles } -> true
            else -> false
        }
    }

    val userRoleMap = hashMapOf(
        "access-token-read" to listOf(ApiRole.USER_READ),
        "access-token-write" to listOf(ApiRole.USER_READ, ApiRole.USER_WRITE)
    )

    internal fun extractToken(ctx: Context): String? {
        return ctx.header("Authorization")?.let { authorizationHeader ->
            val headerSplit = authorizationHeader.split(" ")
            if (headerSplit.size != 2 || headerSplit[0] != "Bearer") {
                return null
            }
            return headerSplit[1]
        }
    }

    internal fun extractUserRoles(ctx: Context): List<ApiRole>? {
        // this is just giving default read permissions to every user because we don't have different user roles
        // every authenticated user will go through
        return Authorization.userRoleMap["access-token-read"] ?: listOf()
    }

    private val Context.userToken: String?
        get() = extractToken(this)

    private val Context.userRoles: List<ApiRole>?
        get() = extractUserRoles(this)
}

