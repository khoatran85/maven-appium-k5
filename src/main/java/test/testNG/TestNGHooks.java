package test.testNG;

import org.testng.annotations.*;

public class TestNGHooks {

    @Test(dependsOnMethods = {"testB"})
    public void testA(){
        System.out.println("Test A");
    }

    @Test(priority = 2)
    public void testB(){
        System.out.println("Test B");
    }
    @Test(priority = 1)
    public void testC(){
        System.out.println("Test C");
    }

    @BeforeMethod
    public void BeforeMethode(){
        System.out.println("BeforeMethod");
    }

    @BeforeClass
    public void BeforeClass(){
        System.out.println("BeforeClass");
    }

    @BeforeTest
    public void BeforeTest(){
        System.out.println("BeforeTest");
    }


}
