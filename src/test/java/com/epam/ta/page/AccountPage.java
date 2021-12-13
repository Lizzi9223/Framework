package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.lang.String;

public class AccountPage extends AbstractPage{

    private final String PAGE_URL = "https://my.exness.com";
    int balanceValue = 1000;

    private final By locatorDemoTab=By.xpath("//div[contains(@class,'Tabs_container')]/div[contains(text(),'Demo')]");
    private final By locatorSetBalance = By.xpath("//button[contains(text(),'Set Balance')]");
    private final By locatorBalance = By.xpath("//div/span[contains(@class,'Money_balanceLarge')]");
    private final By locatorTrade = By.xpath("//div[contains(@class,'AccountCards')]/button[text()='Trade']");
    private final By locatorExnessTerminal = By.xpath("//a[contains(@class,'ApplicationsLinks')]");
    private final By locatorTraidingPageBody = By.xpath("//*[@id='tradingPage']");
    private final By locatorSettings = By.xpath("//div[contains(@class,'AccountCogMenu')]");
    private final By locatorRename = By.xpath("//li/span[text()='Rename account']");
    private final By locatorAccName = By.xpath("//div[contains(@class,'AccountCards_topText')]/div");
    private final By locatorHelp = By.xpath("//*[contains(@class,'HeaderDropDown_component')]");
    private final By locatorCurrencyConverter = By.xpath("//a[text()='Currency Converter']");
    private final By locatorTradersCalculator = By.xpath("//a[text()=\"Trader's Calculator\"]");

    @FindBy(id = "amount")
    private WebElement inputAreaBalance;

    @FindBy(id = "alias")
    private WebElement inputAreaName;

    @FindBy(xpath = "//form/button")
    private WebElement saveChanges;

    @FindBy(xpath = "//button[text()='Rename Account']")
    private WebElement renameAccout;

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

        inputAreaBalance.clear();
        inputAreaBalance.sendKeys(Integer.toString(balanceValue));
        saveChanges.click();
        driver.navigate().refresh();

        return this;
    }

    public String getAccountName(){
        WebElement demoTab = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorDemoTab));
        demoTab.click();

        List<WebElement> name = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorAccName));
        String fullName = name.get(1).getText().toString();

        return fullName.substring(0,fullName.indexOf(' '));
    }

    public AccountPage renameAccount(){
        WebElement demoTab = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorDemoTab));
        demoTab.click();

        List<WebElement> settings = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorSettings));
        settings.get(1).click();

        driver.findElements(locatorRename).get(1).click();

        inputAreaName.clear();
        inputAreaName.sendKeys("NewAccName");

        renameAccout.click();
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

    public AccountPage openHelpDropDown(){
        WebElement help = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorHelp));
        help.click();
        return this;
    }

    public CurrencyConverterPage openCurrencyConverter(){
        WebElement currencyConverter = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorCurrencyConverter));
        String URLToClick = currencyConverter.getAttribute("href");
        driver.navigate().to(URLToClick);
        return new CurrencyConverterPage(driver);
    }

    public TradersCalculatorPage openTradersCalculator(){
        WebElement tradersCalculator = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorTradersCalculator));
        String URLToClick = tradersCalculator.getAttribute("href");
        driver.navigate().to(URLToClick);
        return new TradersCalculatorPage(driver);
    }

    @Override
    public AccountPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("To open 'Account page' firstly u have to sign in");
        return this;
    }
}
