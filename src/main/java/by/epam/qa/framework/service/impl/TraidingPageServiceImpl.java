package by.epam.qa.framework.service.impl;


import by.epam.qa.framework.page.AccountPage;
import by.epam.qa.framework.page.TradingPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class TraidingPageServiceImpl extends AbstractService {

    private TradingPage tradingPage;

    public TraidingPageServiceImpl(WebDriver driver){
        super(driver);
        tradingPage = new TradingPage(driver);
    }

    public TraidingPageServiceImpl addNewTab(String tabName){
        tradingPage = tradingPage
                .addTabClick()
                .searchInputSendKeys(tabName)
                .searchResultClick();
        return this;
    }

    public TraidingPageServiceImpl buyLots(int amount){
        tradingPage = tradingPage
                .addLotsSendKeys(amount)
                .buyLotsClick();
        return this;
    }

    public TraidingPageServiceImpl editLastLot(String takeProfitValue){
        tradingPage = tradingPage
                .menuPortfolioClick()
                .editFirstOrderClick()
                .takeProfitSendKeys(takeProfitValue)
                .applyTakeProfitClick()
                .menuPortfolioClick();
        return this;
    }

    public TraidingPageServiceImpl deleteAllLots(){
        tradingPage = tradingPage
                .menuPortfolioClick()
                .deleteAllLots()
                .menuPortfolioClick();
        return this;
    }

    public TraidingPageServiceImpl addCompanyToFavourites(String company){
        tradingPage = tradingPage
                .menuWatchlistClick()
                .dropDownCompaniesClick()
                .allCompaniesClick()
                .inputCompanySendKeys(company)
                .starClick()
                .menuWatchlistClick();
        return this;
    }

    public boolean isCompanyInListOfFavourites(String companyName){
        List<String> allCompaniesNames = tradingPage
                    .menuWatchlistClick()
                    .favouriteCompaniesClick()
                    .getAllFavevouriteCompaniesNames();
        for (String name : allCompaniesNames){
            if(name.equals(companyName)){
                return true;
            }
        }
        return false;
    }

    public int getFavouriteCompaniesCount(){
        int count = tradingPage
                .menuWatchlistClick()
                .dropDownCompaniesClick()
                .favouriteCompaniesClick()
                .getFavevouriteCompaniesCount();
        return count;
    }

    public int getOrdersCount(){
        int count = tradingPage
                .menuPortfolioClick()
                .getOrdersCount();
        tradingPage = tradingPage.menuPortfolioClick();
        return count;
    }

    public int getAllTabsCount(){
        return tradingPage.getAllTabsCount();
    }

    public String getTakeProfitValueOfLastOrder(){
        String takeProfitValue = tradingPage
                .menuPortfolioClick()
                .moreInfoClick()
                .getTakeProfitValueOfLastOrder();
        tradingPage = tradingPage
                .moreInfoClick()
                .menuPortfolioClick();
        return takeProfitValue;
    }

    public String getLastTabName(){
        List<String> allNames = tradingPage.getAllTabsName();
        int count = tradingPage.getAllTabsCount();
        return allNames.get(count-1);
    }
}
