package com.company;

import org.openqa.selenium.*;

public class CostumerSigninRegisterPage {

    WebDriver browser;
    String costumerSigninRegisterPageUrl = "https://czechitas-shopizer.azurewebsites.net/shop/customer/customLogon.html";
    By costumerSigninEmailLocator = By.id("signin_userName");
    By costumerSigninPasswordLocator = By.id("signin_password");
    By signinButtonLocator = By.id("genericLogin-button");
    By loginErrorAlertLocator = By.id("loginError");

    public CostumerSigninRegisterPage(WebDriver webBrowser) {
        browser = webBrowser;
        System.out.println("Navigate browser to page where costumers could Sign in or Resgister:" +
                " https://czechitas-shopizer.azurewebsites.net/shop/customer/customLogon.html");
        browser.navigate().to(costumerSigninRegisterPageUrl);
    }

    public void fillCostumerSigninEmail(String costumerEmail) {
        System.out.println("Find Element Costumer email adress and fill it by: " + costumerEmail);
        WebElement costumerSigninEmail = browser.findElement(costumerSigninEmailLocator);
        costumerSigninEmail.sendKeys(costumerEmail);
    }

    public void fillCostumerSigninPassword(String costumerPassword) {
        System.out.println("Find Element Password and fill it by: " + costumerPassword);
        WebElement costumerSigninPassword = browser.findElement(costumerSigninPasswordLocator);
        costumerSigninPassword.sendKeys(costumerPassword);
    }

    /*
    Viem, že v tejto chvíly sa neviem prihlásiť na stránku registrovaných zákazníkov, ale predpokladám, že existuje a tak
    si vytváram už rovno možnosť, že po prihlásení ma to na ňu presmeruje a pokiaľ sa tak stane, možem s ňou Ďalej pracovať.
    */
    public RegisteredCostumerPage clickOnSigninButton() {
        System.out.println("Find Element Sign in button and clik on it.");
        WebElement signinButton = browser.findElement(signinButtonLocator);
        signinButton.click();

        return new RegisteredCostumerPage(browser);
    }

    /*
    Metóda registrácie, pre testy kedy sa chcem priamo prihlásiť, bez použitia metód pre jednotlivé kroky
    */
    public RegisteredCostumerPage signin (String userName, String password) {
        fillCostumerSigninEmail(userName);
        fillCostumerSigninPassword(password);
        return clickOnSigninButton();
    }

    public boolean isLoginFailedUserAlertPresent() {
        System.out.println("When Element Login failed alert box if found, return true.");
        browser.findElement(loginErrorAlertLocator);
        return true;
    }

    public String textOfLoginFailedUserAlert(){
        System.out.println("Find Element Login failed alert box and get its text.");
        WebElement loginErrorAlert = browser.findElement(loginErrorAlertLocator);
        String searchOutputText = loginErrorAlert.getText();
        return searchOutputText;
    }

}

