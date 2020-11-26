package com.company;

import org.openqa.selenium.*;

public class CostumerSigninRegisterPage {

    WebDriver browser;
    String costumerSigninRegisterPageUrl = "https://czechitas-shopizer.azurewebsites.net/shop/customer/customLogon.html";
    By costumerSigninEmail = By.id("signin_userName");
    By costumerSigninPassword = By.id("signin_password");
    By signinButton = By.id("genericLogin-button");
    By loginErrorAlert = By.id("loginError");
    By registerButton = By.xpath("//*[@class=\"btn btn-default login-btn\" and text()=\"Register\"]");

    public CostumerSigninRegisterPage(WebDriver webBrowser) {
        browser = webBrowser;
        System.out.println("Navigate browser to page where costumers could Sign in or Resgister: " +
                costumerSigninRegisterPageUrl);
        browser.navigate().to(costumerSigninRegisterPageUrl);
    }

    public void fillCostumerSigninEmail(String costumerEmail) {
        System.out.println("Find Element Costumer email adress and fill it by: " + costumerEmail);
        browser.findElement(costumerSigninEmail).sendKeys(costumerEmail);
    }

    public void fillCostumerSigninPassword(String costumerPassword) {
        System.out.println("Find Element Password and fill it by: " + costumerPassword);
        browser.findElement(costumerSigninPassword).sendKeys(costumerPassword);
    }
    
    public CostumerMyAccountPage clickOnSigninButton() {
        System.out.println("Find Element Sign in button and clik on it.");
       browser.findElement(signinButton).click();

        return new CostumerMyAccountPage(browser);
    }

    /*
    Metóda registrácie, pre testy kedy sa chcem priamo prihlásiť, bez použitia metód pre jednotlivé kroky
    */
    public CostumerMyAccountPage signin (String userName, String password) {
        fillCostumerSigninEmail(userName);
        fillCostumerSigninPassword(password);
        return clickOnSigninButton();
    }

    public boolean isLoginFailedUserAlertPresent() {
        System.out.println("When Element Login failed alert box if found, return true.");
        browser.findElement(loginErrorAlert);
        return true;
    }

    public String textOfLoginFailedUserAlert(){
        System.out.println("Find Element Login failed alert box and get its text.");
        return browser.findElement(loginErrorAlert).getText();
    }

   public RegistrationPage clickOnRegistrationButton(){
       browser.findElement(registerButton).click();

        return new RegistrationPage(browser);
   }

    public String getCurrentUrl(){
        System.out.println("Get current Url and compare it with: " + costumerSigninRegisterPageUrl);
        return browser.getCurrentUrl();
    }

}

