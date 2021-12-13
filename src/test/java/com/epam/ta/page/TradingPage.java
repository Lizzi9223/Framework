package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TradingPage extends AbstractPage{

    private final String PAGE_URL = "https://my.exness.com/webtrading/";

    private final By locatorAddTab = By.className("add-tab-button");
    private final By locatorSearchInput = By.xpath("//input[@type='search']");
    private final By locatorSearchResult = By.xpath("//div[contains(@class,'watchlistItem')]");
    private final By locatorInputLots = By.xpath("//*[contains(@class,'TradingPage__Order')]//input[contains(@value,'.')]");
    private final By locatorBuyButton = By.xpath("//*[contains(@class,'OrderButton__Panel')]");
    private final By locatorNavMenu = By.xpath("//*[contains(@class,'navButton')]");
    private final By locatorControlButtons = By.xpath("//div[contains(@class,'close-button')]");
    private final By locatorTakeProfitInput = By.xpath("//div[contains(@class,'OrderModifyPopup')]//input");
    private final By locatorApplyTakeProfitButton = By.xpath("//div[contains(@class,'OrderModifyPopup')]//button");
    private final By locatorOrdersList = By.xpath("//*[contains(@class,'ScrollContainer')]/div");
    private final By locatorMoreInfo = By.xpath("//div[@class='buttons-container']/div/div[1]");
    private final By locatorTakeProfitValue = By.xpath("//*[text()='Take Profit']/following-sibling::div");
    private final By locatorAllTabs = By.xpath("//div[contains(@class,'asset-close')]");
    private final By locatorWatchlistInput = By.xpath("//div[contains(@class,'NavigationMenuPanel')]//input");
    private final By locatorStar = By.xpath("//div[contains(@class,'favoritesStar')]");
    private final By locatorCompaniesDropdown = By.xpath("//div[contains(@class,'NavigationMenuPanel')]//button");
    private final By locatorAllCompanies = By.xpath("//div[text()='All']");
    private final By locatorFaveCompanies = By.xpath("//div[text()='Favorites']");
    private final By locatorCompany = By.xpath("//div[contains(text(),'AAPL')]");
    private final By locatorFavesList = By.xpath("//div[contains(@class,'WatchList')]");

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

        driver.navigate().refresh();

        return this;
    }

    public TradingPage editLastLot(){
        List<WebElement> navMenu = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorNavMenu));
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();

        WebElement editFirstOrderButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorControlButtons));
        editFirstOrderButton.click();

        WebElement takeProfitInput = driver.findElement(locatorTakeProfitInput);
        takeProfitInput.clear();
        takeProfitInput.sendKeys("1000000.88");

        WebElement applyTakeProfitButton = driver.findElement(locatorApplyTakeProfitButton);
        applyTakeProfitButton.click();

        portfolioButton.click();

        return this;
    }

    public TradingPage deleteAllLots(){
        List<WebElement> navMenu = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorNavMenu));
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();

        int count = getOrdersCount();
        for (int i=0;i<count;i++){
            List<WebElement> controlButtons = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorControlButtons));
            controlButtons.get(1).click();
        }

        portfolioButton.click();

        return this;
    }

    public TradingPage addCompanyToFaves(){
        List<WebElement> navMenu = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorNavMenu));
        WebElement watchListButton = navMenu.get(0);
        watchListButton.click();

        WebElement dropDown = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorCompaniesDropdown));
        dropDown.click();
        WebElement allCompanies = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorAllCompanies));
        allCompanies.click();

        WebElement input = driver.findElement(locatorWatchlistInput);
        input.clear();
        input.sendKeys("APPL");

        WebElement star = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorStar));
        star.click();

        watchListButton.click();

        return this;
    }

    public boolean isCompanyInFaves(){
        List<WebElement> navMenu = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorNavMenu));
        WebElement watchListButton = navMenu.get(0);
        watchListButton.click();

        WebElement dropDown = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorCompaniesDropdown));
        dropDown.click();
        WebElement faveCompanies = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorFaveCompanies));
        faveCompanies.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorFavesList));

        int size = driver.findElements(locatorCompany).size();

        return (size != 0);
    }

    public int getOrdersCount(){
        List<WebElement> navMenu = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorNavMenu));
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();

        List<WebElement> ordersList = driver.findElements(locatorOrdersList);
        portfolioButton.click();
        return ordersList.size();
    }

    public int getAllTabsCount(){
        List<WebElement> allTabs = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorAllTabs));
        return allTabs.size();
    }

    public String getTakeProfitValueOfLast(){
        List<WebElement> navMenu = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorNavMenu));
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();

        driver.findElement(locatorMoreInfo).click();
        String takeProfitValue = driver.findElement(locatorTakeProfitValue).getText().replace(",","");
        driver.findElement(locatorMoreInfo).click();

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
