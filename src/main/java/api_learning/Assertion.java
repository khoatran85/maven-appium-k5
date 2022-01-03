package api_learning;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Assertion {
    public static void main(String[] args) {
        String actualValue = "TestA";
        String expectedValue ="TestA ";
        //Hard assert
//        Assert.assertEquals(actualValue, expectedValue, "[ERR] Login dialog title is incorrect");

        //Soft assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualValue, expectedValue, "[ERR] Login dialog title is incorrect");
        softAssert.assertEquals(true, true, "[ERR] equal");
        softAssert.assertTrue(false, "[ERR] assertTrue");

        softAssert.assertAll();
    }
}
