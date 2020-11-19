package com.company;

import org.openqa.selenium.*;

public class CostumerMyAccountPage {

    WebDriver browser;
    String costumerMyAccountPageURL = "https://czechitas-shopizer.azurewebsites.net/shop/customer/dashboard.html";
    By elementToAssert = By.id("main-content");

    public CostumerMyAccountPage(WebDriver webBrowser) {
        browser = webBrowser;
        System.out.println("Navigate browser to registered costumer page which shows My account and Past orders: " +
                costumerMyAccountPageURL);
        browser.navigate().to(costumerMyAccountPageURL);
    }

    public String getCurrentUrl(){
        System.out.println("Get current Url and compare it with: " + costumerMyAccountPageURL);
        return browser.getCurrentUrl();
    }

}
