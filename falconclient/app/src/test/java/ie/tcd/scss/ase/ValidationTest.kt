package ie.tcd.scss.ase

import ie.tcd.scss.ase.utilities.ValidationClass
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ValidationTest {

    val invalidAddress1 ="ABC@123"
    val invalidAddress2 ="AB"
    val invalidAddress3 = ""
    val validAddress0 = "342536"
    val validAddress1 ="53/2, St. James' Avenue, Dublin 5."
    val validAddress2 ="Hoscombe-Bosworth Castle, 2/3 Dublin-Limerick Road, D09F456"
    val validAddress3 ="D0985839"


    @Test
    fun isAddresslValidTestedWithAddresses(){

        val ob = ValidationClass()
        assertEquals("Passed",true,ob.isAddresslValid(validAddress0))
        assertEquals("Passed",true,ob.isAddresslValid(validAddress1))
        assertEquals("Passed",true,ob.isAddresslValid(validAddress2))
        assertEquals("Passed",true,ob.isAddresslValid(validAddress3))
        assertEquals("Passed",false,ob.isAddresslValid(invalidAddress1))
        assertEquals("Passed",false,ob.isAddresslValid(invalidAddress2))
        assertEquals("Passed",false,ob.isAddresslValid(invalidAddress3))

    }



}