package com.epam.ta.page;

import com.epam.ta.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://my.exness.com";

    private final By locatorLoginInput = By.id("login");

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//exwc-button/button[text()='Continue']")
    private WebElement continueButton;

    public SignInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SignInPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("SignIn page opened");
        return this;
    }

    public AccountPage signIn(User user){
        WebElement loginInput = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorLoginInput));
        loginInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        continueButton.click();
        logger.info("SignIn performed");
        return new AccountPage(driver);
    }
}
