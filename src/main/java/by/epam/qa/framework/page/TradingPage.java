package by.epam.qa.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class TradingPage extends AbstractPage{

    private final String PAGE_URL = "https://my.exness.com/webtrading/";

    private final By locatorAddTab = By.className("add-tab-button");
    private final By locatorSearchInput = By.xpath("//input[@type='search']");
    private final By locatorSearchResult = By.xpath("//div[contains(@class,'watchlistItem')]");
    private final By locatorInputLots = By.xpath("//*[contains(@class,'TradingPage__Order')]//div[text()='lots']/preceding-sibling::input");
    private final By locatorBuyButton = By.xpath("//*[contains(@class,'OrderButton__Panel')]");
    private final By locatorNavMenu = By.xpath("//*[contains(@class,'navButton')]");
    private final By locatorControlButtons = By.xpath("//div[contains(@class,'close-button')]");
    private final By locatorTakeProfitInput = By.xpath("//div[contains(@class,'OrderModifyPopup')]//input");
    private final By locatorApplyTakeProfitButton = By.xpath("//div[contains(@class,'OrderModifyPopup')]//button");
    private final By locatorOrdersList = By.xpath("//*[contains(@class,'ScrollContainer')]/div");
    private final By locatorMoreInfo = By.xpath("//div[@class='buttons-container']/div/div[1]");
    private final By locatorTakeProfitValue = By.xpath("//*[text()='Take Profit']/following-sibling::div");
    private final By locatorAllTabs = By.xpath("//div[contains(@class,'asset-tabs-container')]/div");
    private final By locatorAllTabsOneByOne = By.xpath("//div[contains(@class,'asset-tab-symbol')]");
    private final By locatorWatchlistInput = By.xpath("//div[contains(@class,'NavigationMenuPanel')]//input");
    private final By locatorStar = By.xpath("//div[contains(@class,'favoritesStar')]");
    private final By locatorCompaniesDropdown = By.xpath("//div[contains(@class,'NavigationMenuPanel')]//button");
    private final By locatorAllCompanies = By.xpath("//div[text()='All']");
    private final By locatorFavouriteCompanies = By.xpath("//div[text()='Favorites']");
    private final By locatorCompany = By.xpath("//div[contains(text(),'AAPL')]");
    private final By locatorFavouriteCompaniesList = By.xpath("//div[contains(@class,'WatchList')]");
    private final By locatorAllFavouriteCompaniesOneByOne = By.xpath("//div[@class='watchlistSymbol']");

    public TradingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public TradingPage addTabClick(){
        WebElement addTab = wait.waitUntilpresenceOfElementLocated(locatorAddTab);
        addTab.click();
        return this;
    }

    public TradingPage searchInputSendKeys(String tabName){
        WebElement searchInput = wait.waitUntilpresenceOfElementLocated(locatorSearchInput);
        searchInput.sendKeys(tabName);
        return this;
    }

    public TradingPage searchResultClick(){
        WebElement searchResult = wait.waitUntilpresenceOfElementLocated(locatorSearchResult);
        searchResult.click();
        logger.info("TradingPage: addNewTab");
        return this;
    }

    public TradingPage addLotsSendKeys(int amount){
        WebElement addLots = wait.waitUntilpresenceOfElementLocated(locatorInputLots);
        addLots.clear();
        addLots.sendKeys(Integer.toString(amount));
        return this;
    }

    public TradingPage buyLotsClick(){
        WebElement buyButton = driver.findElement(locatorBuyButton);
        buyButton.click();
        driver.navigate().refresh();
        logger.info("TradingPage: buyLots");
        return this;
    }

    public TradingPage menuPortfolioClick(){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement portfolioButton = navMenu.get(1);
        portfolioButton.click();
        return this;
    }

    public TradingPage editFirstOrderClick(){
        WebElement editFirstOrderButton = wait.waitUntilpresenceOfElementLocated(locatorControlButtons);
        editFirstOrderButton.click();
        return this;
    }
    public TradingPage takeProfitSendKeys(String takeProfitValue){
        WebElement takeProfitInput = driver.findElement(locatorTakeProfitInput);
        takeProfitInput.clear();
        takeProfitInput.sendKeys(takeProfitValue);
        return this;
    }

    public TradingPage applyTakeProfitClick(){
        WebElement applyTakeProfitButton = driver.findElement(locatorApplyTakeProfitButton);
        applyTakeProfitButton.click();
        logger.info("TradingPage: editLastLot");
        return this;
    }

    public TradingPage deleteAllLots(){
        int count = getOrdersCount();
        for (int i=0;i<count;i++){
            List<WebElement> controlButtons = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorControlButtons);
            controlButtons.get(1).click();
        }
        logger.info("TradingPage: deleteAllLots");
        return this;
    }

    public TradingPage menuWatchlistClick(){
        List<WebElement> navMenu = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorNavMenu);
        WebElement watchListButton = navMenu.get(0);
        watchListButton.click();
        return this;
    }

    public TradingPage dropDownCompaniesClick(){
        WebElement dropDown = wait.waitUntilpresenceOfElementLocated(locatorCompaniesDropdown);
        dropDown.click();
        return this;
    }

    public TradingPage allCompaniesClick(){
        WebElement allCompanies = wait.waitUntilpresenceOfElementLocated(locatorAllCompanies);
        allCompanies.click();
        return this;
    }

    public TradingPage inputCompanySendKeys(String company){
        WebElement input = driver.findElement(locatorWatchlistInput);
        input.clear();
        input.sendKeys(company);
        return this;
    }

    public TradingPage starClick(){
        WebElement star = wait.waitUntilpresenceOfElementLocated(locatorStar);
        star.click();
        logger.info("TradingPage: addCompanyToFaves");
        return this;
    }

    public TradingPage favouriteCompaniesClick(){
        WebElement faveCompanies = wait.waitUntilpresenceOfElementLocated(locatorFavouriteCompanies);
        faveCompanies.click();
        return this;
    }

    public int getFavouriteCompaniesCount(){
        wait.waitUntilpresenceOfElementLocated(locatorFavouriteCompaniesList);
        logger.info("TradingPage: getFavouriteCompaniesCount");
        return driver.findElements(locatorCompany).size();
    }

    public List<String> getAllFavouriteCompaniesNames(){
        List<String> names = new ArrayList<>();
        List<WebElement> companies = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorAllFavouriteCompaniesOneByOne);
        for(WebElement company : companies){
            names.add(company.getText());
        }
        return names;
    }

    public int getOrdersCount(){
        List<WebElement> ordersList = driver.findElements(locatorOrdersList);
        logger.info("TradingPage: getOrdersCount");
        return ordersList.size();
    }

    public int getAllTabsCount(){
        List<WebElement> allTabs = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorAllTabs);
        logger.info("TradingPage: getAllTabsCount");
        return allTabs.size();
    }

    public List<String> getAllTabsName(){
        List<String> names = new ArrayList<>();
        List<WebElement> allTabs = wait.waitUntilpresenceOfAllElementsLocatedBy(locatorAllTabsOneByOne);
        for(WebElement tab : allTabs){
            names.add(tab.getText());
        }
        logger.info("TradingPage: getAllTabsName");
        return names;
    }

    public TradingPage moreInfoClick(){
        driver.findElement(locatorMoreInfo).click();
        return this;
    }

    public String getTakeProfitValueOfLastOrder(){
        String takeProfitValue = driver.findElement(locatorTakeProfitValue).getText().replace(",","");
        logger.info("TradingPage: getTakeProfitValueOfLast");
        return takeProfitValue;
    }

    @Override
    protected AbstractPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("TradingPage: openPage");
        return this;
    }
}
