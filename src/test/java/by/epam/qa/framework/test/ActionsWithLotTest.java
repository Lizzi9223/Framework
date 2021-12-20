package by.epam.qa.framework.test;

import by.epam.qa.framework.service.impl.HomePageServiceImpl;
import by.epam.qa.framework.service.impl.TraidingPageServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionsWithLotTest extends BaseConditions {
    private final String TAB_NAME = "BTC/USD";
    private final String TAB_NAME_AFTER_ADD = "BTC";
    private final String COMPANY_NAME = "AAPL";
    private final String TAKE_PROFIT_VALUE = "1000000.88";
    private final int LOTS_AMOUNT = 1;

    private TraidingPageServiceImpl traidingPageService;

    @Test(description = "add tab", priority = 1)
    public void addTab(){

        traidingPageService = new HomePageServiceImpl(driver)
                .openPage()
                .goToSignInPage()
                .signIn(TEST_USER)
                .startTraiding();


        int initialCount = traidingPageService.getAllTabsCount();
        traidingPageService = traidingPageService.addNewTab(TAB_NAME);
        int finalCount = traidingPageService.getAllTabsCount();
        if(finalCount-initialCount==1){
            String lastTabName = traidingPageService.getLastTabName();
            Assert.assertEquals(lastTabName,TAB_NAME_AFTER_ADD);
        }
    }

//    @Test(description = "buy lot", priority = 2)
//    public void buyLot(){
//        int initialCount = traidingPageService.getOrdersCount();
//        traidingPageService = traidingPageService
//                 .buyLots(LOTS_AMOUNT);
//        int finalCount = traidingPageService.getOrdersCount();
//        Assert.assertEquals(finalCount-initialCount,LOTS_AMOUNT);
//    }
//
//    @Test(description = "edit last lot", priority = 3)
//    public void editLastLot(){
//        traidingPageService = traidingPageService.editLastLot(TAKE_PROFIT_VALUE);
//        String value = traidingPageService.getTakeProfitValueOfLastOrder();
//        Assert.assertEquals(value, TAKE_PROFIT_VALUE);
//    }
//
//    @Test(description = "delete all lots", priority = 4)
//    public void deleteAllLots(){
//        traidingPageService=traidingPageService.deleteAllLots();
//        int finalCount = traidingPageService.getOrdersCount();
//        Assert.assertEquals(finalCount, 0);
//    }

    @Test(description = "add company to faves", priority = 5)
    public void addCompanyToFavourites(){
        traidingPageService = traidingPageService.addCompanyToFavourites(COMPANY_NAME);
        Assert.assertEquals(traidingPageService.isCompanyInListOfFavourites(COMPANY_NAME), true);
    }
}
