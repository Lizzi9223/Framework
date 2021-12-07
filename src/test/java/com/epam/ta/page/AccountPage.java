package com.epam.ta.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends AbstractPage{
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://my.exness.com";
    int balanceValue = 1000;

    private final By locatorDemoTab=By.xpath("//div[contains(@class,'Tabs_container')]/div[contains(text(),'Demo')]");
    private final By locatorSetBalance = By.xpath("//button[contains(text(),'Set Balance')]");
    private final By locatorBalance = By.xpath("//div/span[contains(@class,'Money_balanceLarge')]");
    private final By locatorTrade = By.xpath("//div[contains(@class,'AccountCards')]/button[text()='Trade']");
    private final By locatorExnessTerminal = By.xpath("//a[contains(@class,'ApplicationsLinks')]");
    private final By locatorTraidingPageBody = By.xpath("//*[@id='tradingPage']");

    @FindBy(id = "amount")
    private WebElement inputArea;

    @FindBy(xpath = "//form/button")
    private WebElement saveChanges;

    public AccountPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public int getBalance(){
        WebElement demoTab = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorDemoTab));
        demoTab.click();

        WebElement balance = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorBalance));
        String balanceValue = balance.getAttribute("innerHTML").replaceAll(",","");
        return Integer.parseInt(balanceValue);
    }

    public AccountPage setBalance(){
        WebElement demoTab = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorDemoTab));
        demoTab.click();

        WebElement setBalanceButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorSetBalance));
        setBalanceButton.click();

        inputArea.clear();
        inputArea.sendKeys(Integer.toString(balanceValue));
        saveChanges.click();
        driver.navigate().refresh();

        return this;
    }

    public TradingPage startTraiding(){
        WebElement demoTab = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorDemoTab));
        demoTab.click();

        WebElement trade = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorTrade));
        trade.click();

        WebElement tradeButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorExnessTerminal));
        String URLToClick = tradeButton.getAttribute("href");
        driver.navigate().to(URLToClick);
        logger.info("Go to trading page");

        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorTraidingPageBody));

        return new TradingPage(driver);
    }

    @Override
    public AccountPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("To open 'Account page' firstly u have to sign in");
        return this;
    }
}
