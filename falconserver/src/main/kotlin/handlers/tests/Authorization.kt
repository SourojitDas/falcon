package handlers.tests

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import handlers.Authorization
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import services.FirebaseAuthInterface

class AuthorizationTest {
    @Test
    fun `test isUserAuthenticated success actual valid token`() {
        val fakeToken =
            "eyJhbGciOiJSUzI1NiIsImtpZCI6ImZiMDEyZTk5Y2EwYWNhODI2ZTkwODZiMzIyM2JiOTYwZGFhOTFmODEiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiVG9taW4iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1JNzlWVElDdk9Edy9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BQ0hpM3JkQ1dLUDFpQVVyX2wwRWplMkdrVk5NOV9fNXpnL3M5Ni1jL3Bob3RvLmpwZyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9mYWxjb24tYTAxNTQiLCJhdWQiOiJmYWxjb24tYTAxNTQiLCJhdXRoX3RpbWUiOjE1NTM1MTc1OTMsInVzZXJfaWQiOiJEOEhhSVRIcEJVTXd4NlpvVEU2Zzc1RXpWcTkyIiwic3ViIjoiRDhIYUlUSHBCVU13eDZab1RFNmc3NUV6VnE5MiIsImlhdCI6MTU1MzY5MzgwMiwiZXhwIjoxNTUzNjk3NDAyLCJlbWFpbCI6ImdldDJ0b21pbjIwMTJAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDYxMjIxMTk2Njk1MTUwNTI1MTQiXSwiZW1haWwiOlsiZ2V0MnRvbWluMjAxMkBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.Tqybql8NRenFzwpZno8Uz-aj38sjNCLD0WS5FewIn75UcSWUqbOTM5tb4ZY79xyP06_Y_CHgeB9Yx8fl8VPWFZUWsosMGBc2mpEe7gSyLg9M3fbUBmk6Fwq4IHv5xIsZRY5tZxHIF-5Vsn90kvZ0FfvWDxET1vZnKsTUbPvJWy26Mxp0HcMbEnrJ3vWVaUrG4RkdHa-77mMUprOIJhanlJICYeotOXZKG8ggeqoKg8KxwKdhSPQKE24WyfiQswbKp2fmL7KGDVBufMHpStlsCfz6lhbupCFDs9GAS3y33JYU5Wcpv7RaGhV3RDsfd9rhsPzAn7kYYUZeNzfLiAwvtw"
        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        whenever(mockFirebaseAuthService.isTokenValid(fakeToken)).thenReturn(true)
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertTrue(result)
    }

    @Test
    fun `test isUserAuthenticated success special dev token`() {
        val fakeToken = "o-token-my-token"

        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertTrue(result)
    }

    @Test
    fun `test isUserAuthenticated error user token null`() {
        val fakeToken = null

        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertFalse(result)
    }

    @Test
    fun `test isUserAuthenticated error user token invalid or expired`() {
        val fakeToken = null

        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        whenever(mockFirebaseAuthService.isTokenValid(fakeToken)).thenReturn(false)
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertFalse(result)
    }

}