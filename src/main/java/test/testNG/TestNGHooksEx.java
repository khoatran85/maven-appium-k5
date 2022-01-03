package test.testNG;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGHooksEx {

    @Test
    public void TestA(){
        System.out.println("Test A");
    }

    @Test
    public void TestB(){
        System.out.println("Test B");
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
