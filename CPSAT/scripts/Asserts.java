package scripts;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Asserts {
	
	
	SoftAssert softAssert = new SoftAssert();
	
	
	@Test
	public void assertMethod(){
		
		
		softAssert.assertTrue(true == true);
		softAssert.assertTrue(true == false);

		softAssert.assertEquals("SoftAssert", "SoftAssert");

		softAssert.assertEquals("Neeraj", "SoftAssert");

		System.out.println("softAssertionMethod Successfully passed!");

		softAssert.assertAll();  //Important it gives actual result.
	}

}
