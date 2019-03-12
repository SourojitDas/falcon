package ie.tcd.scss.ase;

import ie.tcd.scss.ase.interfaces.RetroFitAPIClient
import junit.framework.TestCase
import okhttp3.Callback
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthCallTest : TestCase() {

    lateinit var authAPI: RetroFitAPIClient

    @Mock
    lateinit var mockApi : RetroFitAPIClient

    @Captor
    lateinit var  cb : ArgumentCaptor<Callback<>>

    @Before
    fun setUp() {
//        authAPI =  AuthAPI ("username", "password")
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testLogin() {

//        Mockito.verify(mockApi).basicLogin((cb.capture()))
//
//        AuthObject authObject = new AuthObject()
//        cb.getValue().success(authObject, null)
//
//        assertEquals(authObject.isError(), false)
    }
}