package com.company;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class RegistrationPage {

    WebDriver browser;
    String registrationPageUrl = "https://czechitas-shopizer.azurewebsites.net/shop/customer/registration.html";
    By firstNameLocator = By.id("firstName");
    By lastNameLocator = By.id("lastName");
    By dropDownCountryLocator = By.id("registration_country");
    By dropDownStateProvinceNameLocator = By.id("customer_zones");
    By StateProvinceNameLocator = By.id("hidden_zones");
    By emailAdressLocator = By.id("emailAddress");
    By passwordLocator = By.id("password");
    By passwordValidationLocator = By.id("passwordAgain");
    By createAnAccountButtonLocator = By.className("login-btn");
    By registrationErrorAlertLocator = By.id("customer.errors");
    JavascriptExecutor js = (JavascriptExecutor) browser;


    public RegistrationPage(WebDriver webBrowser) {
        browser = webBrowser;
        System.out.println("Navigate browser to page where costumers could Resgister: " + registrationPageUrl);
        browser.navigate().to(registrationPageUrl);
    }

    public void fillFirstName(String firstName) {
        System.out.println("Find Element First name and fill it by: " + firstName);
        WebElement CostumerFirstName = browser.findElement(firstNameLocator);
        CostumerFirstName.sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        System.out.println("Find Element Last name and fill it by: " + lastName);
        WebElement CostumerLastName = browser.findElement(lastNameLocator);
        CostumerLastName.sendKeys(lastName);
    }

    public void selectCountry(String countryName) {
        System.out.println("Find Element Country and from drop down menu select: " + countryName);
        Select dropDownCountry = new Select(browser.findElement(dropDownCountryLocator));
        dropDownCountry.selectByVisibleText(countryName);
    }

    public void selectOrFillStateProvince(String stateProvinceName){
        WebElement dropDownStateProvince = browser.findElement(dropDownStateProvinceNameLocator);
        String stateProvinceText = dropDownStateProvince.getText();
        if (stateProvinceText.isEmpty()) {
            WebElement stateProvince = browser.findElement(StateProvinceNameLocator);
            stateProvince.sendKeys(stateProvinceName);
        } else {
            Select dropDownStateProvinceSelect = new Select(browser.findElement(dropDownStateProvinceNameLocator));
            dropDownStateProvinceSelect.selectByVisibleText(stateProvinceName);
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
        WebElement CostumerEmailAddress = browser.findElement(emailAdressLocator);
        CostumerEmailAddress.sendKeys(emailAdress);
    }

    public void fillPassword(String password) {
        System.out.println("Find Element Password and fill it by: " + password);
        WebElement CostumerPassword = browser.findElement(passwordLocator);
        CostumerPassword.sendKeys(password);
    }

    public void fillPasswordAgain(String passwordAgain) {
        System.out.println("Find Element Repeat Password and fill it by: " + passwordAgain);
        WebElement CostumerPasswordValiditation = browser.findElement(passwordValidationLocator);
        CostumerPasswordValiditation.sendKeys(passwordAgain);
    }

    public void fillSignInInfromation (String emailAdress, String password, String passwordAgain){
        fillEmailAddress(emailAdress);
        fillPassword(password);
        fillPasswordAgain(passwordAgain);
    }

    public CostumerMyAccountPage clickOnCreateAnAccount() {
        System.out.println("Find Element Create an account button and clik on it.");
        WebElement registrationButton = browser.findElement(createAnAccountButtonLocator);
        registrationButton.click();

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
        browser.findElement(registrationErrorAlertLocator);
        return true;
    }

    public String textOfRegistrationErrorAlert(){
        System.out.println("Find Element Registration Error Alert and get its text.");
        WebElement registrationErrorAlert = browser.findElement(registrationErrorAlertLocator);
        String searchOutputText = registrationErrorAlert.getText();
        return searchOutputText;
    }

    public String getCurrentUrl(){
        System.out.println("Get current Url and compare it with: " + registrationPageUrl);
        String currentUrl = browser.getCurrentUrl();
        return currentUrl;
    }
}
