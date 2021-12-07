package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.HomePage;
import com.epam.ta.page.TradingPage;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionsWithLotTest extends CommonConditions{
    @Test(description = "add lot")
    public void addLot(){
        User testUser = UserCreator.withCredentialsFromProperty();
        TradingPage tradingPage = new HomePage(driver)
                .openPage()
                .goToSignInPage()
                .signIn(testUser)
                .startTraiding();

        int initialCount = tradingPage.getOrdersCount();

        tradingPage = tradingPage.addNewTab()
                    .buyLots();

        int finalCount = tradingPage.getOrdersCount();

        Assert.assertEquals(finalCount-initialCount,1);
    }

    @Test(description = "edit lot")
    public void editLot(){
        User testUser = UserCreator.withCredentialsFromProperty();
        TradingPage tradingPage = new HomePage(driver)
                .openPage()
                .goToSignInPage()
                .signIn(testUser)
                .startTraiding()
                .editLot();

        String value = tradingPage.getTakeProfitValue();

        Assert.assertEquals(value, "1000000.88");
    }
}
