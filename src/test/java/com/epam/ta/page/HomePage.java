package com.epam.ta.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://exness.com";

    private final By locatorSignInButton = By.xpath("//div[@class='sidebar-tabs__buttons']/a");

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public HomePage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("Home page opened");
        return this;
    }

    public SignInPage goToSignInPage(){
        WebElement signInButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorSignInButton));
        String URLToClick = signInButton.getAttribute("href");
        driver.navigate().to(URLToClick);
        logger.info("Go to SignIn page");
        return new SignInPage(driver);
    }
}