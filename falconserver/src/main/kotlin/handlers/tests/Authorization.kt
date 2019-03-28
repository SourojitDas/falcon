package handlers.tests

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import handlers.ApiRole
import handlers.Authorization
import io.javalin.Context
import io.javalin.security.SecurityUtil
import org.junit.Assert.*
import org.junit.Test
import services.FirebaseAuthInterface

class AuthorizationTest {
    @Test
    fun `test isUserAuthenticated true actual valid token`() {
        val fakeToken =
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZiMDEyZTk5Y2EwYWNhODI2ZTkwODZiMzIyM2JiOTYwZGFhOTFmODEiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiVG9taW4iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1JNzlWVElDdk9Edy9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BQ0hpM3JkQ1dLUDFpQVVyX2wwRWplMkdrVk5NOV9fNXpnL3M5Ni1jL3Bob3RvLmpwZyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9mYWxjb24tYTAxNTQiLCJhdWQiOiJmYWxjb24tYTAxNTQiLCJhdXRoX3RpbWUiOjE1NTM1MTc1OTMsInVzZXJfaWQiOiJEOEhhSVRIcEJVTXd4NlpvVEU2Zzc1RXpWcTkyIiwic3ViIjoiRDhIYUlUSHBCVU13eDZab1RFNmc3NUV6VnE5MiIsImlhdCI6MTU1MzY5MzgwMiwiZXhwIjoxNTUzNjk3NDAyLCJlbWFpbCI6ImdldDJ0b21pbjIwMTJAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDYxMjIxMTk2Njk1MTUwNTI1MTQiXSwiZW1haWwiOlsiZ2V0MnRvbWluMjAxMkBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.Tqybql8NRenFzwpZno8Uz-aj38sjNCLD0WS5FewIn75UcSWUqbOTM5tb4ZY79xyP06_Y_CHgeB9Yx8fl8VPWFZUWsosMGBc2mpEe7gSyLg9M3fbUBmk6Fwq4IHv5xIsZRY5tZxHIF-5Vsn90kvZ0FfvWDxET1vZnKsTUbPvJWy26Mxp0HcMbEnrJ3vWVaUrG4RkdHa-77mMUprOIJhanlJICYeotOXZKG8ggeqoKg8KxwKdhSPQKE24WyfiQswbKp2fmL7KGDVBufMHpStlsCfz6lhbupCFDs9GAS3y33JYU5Wcpv7RaGhV3RDsfd9rhsPzAn7kYYUZeNzfLiAwvtw"
        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        whenever(mockFirebaseAuthService.isTokenValid(fakeToken)).thenReturn(true)
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertTrue(result)
    }

    @Test
    fun `test isUserAuthenticated true special dev token`() {
        val fakeToken = "o-token-my-token"

        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertTrue(result)
    }

    @Test
    fun `test isUserAuthenticated false user token null`() {
        val fakeToken = null

        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertFalse(result)
    }

    @Test
    fun `test isUserAuthenticated false user token invalid or expired`() {
        val fakeToken = null

        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        whenever(mockFirebaseAuthService.isTokenValid(fakeToken)).thenReturn(false)
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertFalse(result)
    }

    @Test
    fun `test isUserAuthorized true permittedRoles includes ANYONE`() {
        val fakePermittedRoles = SecurityUtil.roles(ApiRole.USER_READ, ApiRole.ANYONE)
        val result = Authorization.isUserAuthorized(fakePermittedRoles, listOf())

        assertTrue(result)
    }

    @Test
    fun `test isUserAuthorized true usersRoles includes any one of the required roles`() {
        val fakePermittedRoles = SecurityUtil.roles(ApiRole.USER_READ, ApiRole.USER_WRITE, ApiRole.USER_EXEC)
        var fakeUserRoles = listOf(ApiRole.USER_READ)
        var result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)

        fakeUserRoles = listOf(ApiRole.USER_WRITE)
        result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)

        fakeUserRoles = listOf(ApiRole.USER_EXEC)
        result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)
    }

    @Test
    fun `test isUserAuthorized true usersRoles includes multiple but not all required roles`() {
        val fakePermittedRoles = SecurityUtil.roles(ApiRole.USER_READ, ApiRole.USER_WRITE, ApiRole.USER_EXEC)
        var fakeUserRoles = listOf(ApiRole.USER_READ, ApiRole.USER_WRITE)
        var result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)

        fakeUserRoles = listOf(ApiRole.USER_WRITE, ApiRole.USER_EXEC)
        result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)

        fakeUserRoles = listOf(ApiRole.USER_EXEC, ApiRole.USER_READ)
        result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)
    }

    @Test
    fun `test isUserAuthorized true usersRoles includes all required roles`() {
        val fakePermittedRoles = SecurityUtil.roles(ApiRole.USER_READ, ApiRole.USER_WRITE, ApiRole.USER_EXEC)
        val fakeUserRoles = listOf(ApiRole.USER_READ, ApiRole.USER_WRITE, ApiRole.USER_EXEC)
        val result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)
    }

    @Test
    fun `test isUserAuthorized true usersRoles includes more than the required roles`() {
        val fakePermittedRoles = SecurityUtil.roles(ApiRole.USER_READ, ApiRole.USER_WRITE)
        val fakeUserRoles = listOf(ApiRole.USER_READ, ApiRole.USER_WRITE, ApiRole.USER_EXEC)
        val result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertTrue(result)
    }

    @Test
    fun `test isUserAuthorized false usersRoles doesn't include any required role`() {
        val fakePermittedRoles = SecurityUtil.roles(ApiRole.USER_READ, ApiRole.USER_WRITE)
        val fakeUserRoles = listOf(ApiRole.USER_EXEC)
        val result = Authorization.isUserAuthorized(fakePermittedRoles, fakeUserRoles)
        assertFalse(result)
    }

    @Test
    fun `test extractUserRoles single case`() {
        val mockContext = mock<Context>()
        val result = Authorization.extractUserRoles(mockContext)
        assertEquals(result, Authorization.userRoleMap["access-token-read"])
    }

    @Test
    fun `test extractToken nulll no Authorization header`() {
        val mockContext = mock<Context>()
        val result = Authorization.extractToken(mockContext)
        assertEquals(result, null)
    }

    @Test
    fun `test extractToken null Authorization header malformed no space`() {
        val mockContext = mock<Context>()
        mockContext.header("Authorization", "Bearer")
        val result = Authorization.extractToken(mockContext)
        assertEquals(result, null)
    }

    @Test
    fun `test extractToken null Authorization header malformed empty string`() {
        val mockContext = mock<Context>()
        mockContext.header("Authorization", "")
        val result = Authorization.extractToken(mockContext)
        assertEquals(result, null)
    }

    @Test
    fun `test extractToken null Authorization header malformed more than one space`() {
        val mockContext = mock<Context>()
        mockContext.header("Authorization", "Bearer 1234 5678")
        val result = Authorization.extractToken(mockContext)
        assertEquals(result, null)
    }

    @Test
    fun `test extractToken null Authorization header one space but Bearer missing`() {
        val mockContext = mock<Context>()
        mockContext.header("Authorization", "1234 5678")
        val result = Authorization.extractToken(mockContext)
        assertEquals(result, null)
    }

    @Test
    fun `test extractToken non-null Authorization header`() {
        val mockContext = mock<Context> {
            on { header("Authorization") } doReturn "Bearer 5678"
        }
        println(mockContext.header("Authorization"))
        val result = Authorization.extractToken(mockContext)
        assertEquals(result, "5678")
    }

}