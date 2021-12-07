package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.HomePage;
import com.epam.ta.page.TradingPage;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionsWithLotTest extends CommonConditions{

    User testUser = UserCreator.withCredentialsFromProperty();
    TradingPage tradingPage;

    @Test(description = "add tab", priority = 1)
    public void addTab(){
        tradingPage = new HomePage(driver)
                .openPage()
                .goToSignInPage()
                .signIn(testUser)
                .startTraiding();

        int initialCount = tradingPage.getAllTabsCount();
        tradingPage = tradingPage.addNewTab();
        int finalCount = tradingPage.getAllTabsCount();
        Assert.assertEquals(finalCount-initialCount,1);
    }

    @Test(description = "buy lot", priority = 2)
    public void buyLot(){
        int initialCount = tradingPage.getOrdersCount();
        tradingPage = tradingPage.addNewTab()
                 .buyLots();
        int finalCount = tradingPage.getOrdersCount();
        Assert.assertEquals(finalCount-initialCount,1);
    }

    @Test(description = "edit last lot", priority = 3)
    public void editLastLot(){
        tradingPage = tradingPage.editLastLot();
        String value = tradingPage.getTakeProfitValueOfLast();
        Assert.assertEquals(value, "1000000.88");
    }

    @Test(description = "delete all lots", priority = 4)
    public void deleteAllLots(){
        tradingPage=tradingPage.deleteAllLots();
        int finalCount = tradingPage.getOrdersCount();
        Assert.assertEquals(finalCount, 0);
    }

    @Test(description = "add company to faves", priority = 5)
    public void addCompanyToFaves(){
        tradingPage = tradingPage.addCompanyToFaves();
        Assert.assertEquals(tradingPage.isCompanyInFaves(), true);
    }
}
