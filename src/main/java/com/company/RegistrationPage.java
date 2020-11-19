package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class RegistrationPage {

    WebDriver browser;
    String registrationPageUrl = "https://czechitas-shopizer.azurewebsites.net/shop/customer/registration.html";
    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By dropDownCountry = By.id("registration_country");
    By dropDownStateOrProvinceName = By.id("customer_zones");
    By StateProvinceName = By.id("hidden_zones");
    By emailAdress = By.id("emailAddress");
    By password = By.id("password");
    By passwordValidation = By.id("passwordAgain");
    By createAnAccountButton = By.className("login-btn");
    By registrationErrorAlert = By.id("customer.errors");


    public RegistrationPage(WebDriver webBrowser) {
        browser = webBrowser;
        System.out.println("Navigate browser to page where costumers could Resgister: " + registrationPageUrl);
        browser.navigate().to(registrationPageUrl);
    }

    public void fillFirstName(String firstName) {
        System.out.println("Find Element First name and fill it by: " + firstName);
        browser.findElement(this.firstName).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        System.out.println("Find Element Last name and fill it by: " + lastName);
        browser.findElement(this.lastName).sendKeys(lastName);
    }

    public void selectCountry(String countryName) {
        System.out.println("Find Element Country and from drop down menu select: " + countryName);
        new Select(browser.findElement(dropDownCountry)).selectByVisibleText(countryName);
    }

    public void selectOrFillStateProvince(String stateProvinceName){
        WebElement dropDownStateProvince = browser.findElement(dropDownStateOrProvinceName);
        String stateProvinceText = dropDownStateProvince.getText();
        if (stateProvinceText.isEmpty()) {
            browser.findElement(StateProvinceName).sendKeys(stateProvinceName);
        } else {
            new Select(browser.findElement(dropDownStateOrProvinceName)).selectByVisibleText(stateProvinceName);
        }
    }

    public void fillPeronalInformations(String firstName, String lastName, String countryName, String stateProvinceName){
        fillFirstName(firstName);
        fillLastName(lastName);
        selectCountry(countryName);
        selectOrFillStateProvince(stateProvinceName);
    }

    public void fillEmailAddress(String emailAdress) {
        System.out.println("Find Element Email address and fill it by: " + emailAdress);
        browser.findElement(this.emailAdress).sendKeys(emailAdress);
    }

    public void fillPassword(String password) {
        System.out.println("Find Element Password and fill it by: " + password);
        browser.findElement(this.password).sendKeys(password);
    }

    public void fillPasswordAgain(String passwordAgain) {
        System.out.println("Find Element Repeat Password and fill it by: " + passwordAgain);
        browser.findElement(passwordValidation).sendKeys(passwordAgain);
    }

    public void fillSignInInfromation (String emailAdress, String password, String passwordAgain){
        fillEmailAddress(emailAdress);
        fillPassword(password);
        fillPasswordAgain(passwordAgain);
    }

    public CostumerMyAccountPage clickOnCreateAnAccount() {
        System.out.println("Find Element Create an account button and clik on it.");
        browser.findElement(createAnAccountButton).click();

        return new CostumerMyAccountPage(browser);
    }


    public CostumerMyAccountPage registerUser (String firstName, String lastName, String countryName, String stateProvinceName,
                                               String emailAdress, String password, String passwordAgain) throws InterruptedException{
        fillPeronalInformations(firstName, lastName, countryName, stateProvinceName);
        fillSignInInfromation (emailAdress, password, passwordAgain);
        Thread.sleep(5000);
        clickOnCreateAnAccount();

        return new CostumerMyAccountPage(browser);
    }

    public boolean isRegistrationErrorAlertPresent(){
        System.out.println("When Element Registration Error Alert if found, return true.");
        browser.findElement(registrationErrorAlert);
        return true;
    }

    public String textOfRegistrationErrorAlert(){
        System.out.println("Find Element Registration Error Alert and get its text.");
        return browser.findElement(registrationErrorAlert).getText();
    }

    public String getCurrentUrl(){
        System.out.println("Get current Url and compare it with: " + registrationPageUrl);
        return browser.getCurrentUrl();
    }
}
