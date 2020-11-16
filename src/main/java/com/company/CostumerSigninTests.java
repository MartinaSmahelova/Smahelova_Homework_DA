package com.company;

import java.util.concurrent.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;

public class CostumerSigninTests {

    WebDriver firefox;

    @Before
    public void SetUp(){

        System.setProperty("webdriver.gecko.driver","C:\\FirefoxDriver\\geckodriver.exe");

        firefox = new FirefoxDriver();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("-private");
        firefox.manage().window().maximize();
        firefox.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void StepByStepSignin_InvalidCostumerEmailInvalidPassword_RightLoginFailedAllertIsShown() {

        CostumerSigninRegisterPage costumerSigninRegisterPage = new CostumerSigninRegisterPage(firefox);

        costumerSigninRegisterPage.fillCostumerSigninEmail("NonExistingCustomer");
        costumerSigninRegisterPage.fillCostumerSigninPassword("FakePass");
        costumerSigninRegisterPage.clickOnSigninButton();
        
        Assert.assertTrue("Login Failed User Alert must be present", costumerSigninRegisterPage.isLoginFailedUserAlertPresent());
        Assert.assertEquals("Text of Login failed Alert should be: Login Failed. Username or Password is incorrect.",
                "Login Failed. Username or Password is incorrect.", costumerSigninRegisterPage.textOfLoginFailedUserAlert());

    }

    @Test
    public void DirectSignin_InvalidCostumerEmailInvalidPassword_RoghtLoginFailedAllertIsShown() {
        CostumerSigninRegisterPage costumerSigninRegisterPage = new CostumerSigninRegisterPage(firefox);

        costumerSigninRegisterPage.signin("NonExistingCustomer","FakePass");

        Assert.assertTrue("Login Failed User Alert must be present", costumerSigninRegisterPage.isLoginFailedUserAlertPresent());
        Assert.assertEquals("Text of Login failed Alert should be: Login Failed. Username or Password is incorrect.",
                "Login Failed. Username or Password is incorrect.", costumerSigninRegisterPage.textOfLoginFailedUserAlert());
    }


    @After
    public void CleanUp(){
        firefox.quit();
    }


}
