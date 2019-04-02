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

class Weather {
    @Test
    fun `test isUserAuthenticated false user token null`() {
        val fakeToken = null

        val mockFirebaseAuthService = mock<FirebaseAuthInterface>()
        val result = Authorization.isUserAuthenticated(fakeToken, mockFirebaseAuthService)

        assertFalse(result)
    }
}