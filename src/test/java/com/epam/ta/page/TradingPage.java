package com.epam.ta.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TradingPage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://my.exness.com/webtrading/";

    private final By locatorAddTab = By.className("add-tab-button");
    private final By locatorSearchInput = By.xpath("//input[@type='search']");
    private final By locatorSearchResult = By.xpath("//div[contains(@class,'watchlistItem')]");
    private final By locatorInputLots = By.xpath("//*[contains(@class,'TradingPage__Order')]//input[contains(@value,'0')]");
    private final By locatorBuyButton = By.xpath("//*[contains(@class,'OrderButton__Panel')]");
    private final By locatorNavMenu = By.xpath("//*[contains(@class,'navButton')]");
    private final By locatorEditFirstOrder = By.xpath("//div[contains(@class,'close-button')]");
    private final By locatorTakeProfitInput = By.xpath("//div[contains(@class,'OrderModifyPopup')]//input");
    private final By locatorApplyTakeProfitButton = By.xpath("//div[contains(@class,'OrderModifyPopup')]//button");

    private final By locatorOrdersList = By.xpath("//*[contains(@class,'ScrollContainer')]/div");


    private final By locMoreInfo = By.xpath("//*[@id=\"root\"]/div[3]/div/div[1]/div[2]/div[2]/div[3]/div/div/div[2]/div/div/div/div/div/div/div[2]/div[2]/div/div[1]/div");
    private final By locatorTakeProfitValue = By.xpath("//*[text()='Take Profit']/following-sibling::div");


    public TradingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public TradingPage addNewTab(){
        WebElement addTab = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorAddTab));
        addTab.click();

        WebElement searchInput = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorSearchInput));
        searchInput.sendKeys("BTC/USD");

        WebElement searchResult = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorSearchResult));
        searchResult.click();

        return this;
    }

    public TradingPage buyLots(){
        WebElement addLots = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorInputLots));
        addLots.clear();
        addLots.sendKeys("1");

        WebElement buyButton = driver.findElement(locatorBuyButton);
        buyButton.click();

        return this;
    }

    public int getOrdersCount(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorNavMenu));
        List<WebElement> navMenu = driver.findElements(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();

        List<WebElement> ordersList = driver.findElements(locatorOrdersList);
        portfolioButton.click();
        return ordersList.size();
    }

    public TradingPage editLot(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorNavMenu));
        List<WebElement> navMenu = driver.findElements(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();

        WebElement editFirstOrderButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorEditFirstOrder));
        editFirstOrderButton.click();

        WebElement takeProfitInput = driver.findElement(locatorTakeProfitInput);
        takeProfitInput.sendKeys("1000000.88");

        WebElement applyTakeProfitButton = driver.findElement(locatorApplyTakeProfitButton);
        applyTakeProfitButton.click();

        portfolioButton.click();

        return this;
    }

    public String getTakeProfitValue(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorNavMenu));
        List<WebElement> navMenu = driver.findElements(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();

        driver.findElement(locMoreInfo).click();
        String takeProfitValue = driver.findElement(locatorTakeProfitValue).getText().replace(",","");
        driver.findElement(locMoreInfo).click();

        portfolioButton.click();
        return takeProfitValue;
    }

    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("To open 'Trading page' firstly u have to sign in");
        return this;
    }
}
