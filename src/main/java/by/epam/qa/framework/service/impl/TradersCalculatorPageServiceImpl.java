package by.epam.qa.framework.service.impl;

import by.epam.qa.framework.page.AccountPage;
import by.epam.qa.framework.page.CurrencyConverterPage;
import by.epam.qa.framework.page.TradersCalculatorPage;
import by.epam.qa.framework.page.TradingPage;
import org.openqa.selenium.WebDriver;

public class TradersCalculatorPageServiceImpl extends AbstractService{

    private TradersCalculatorPage tradersCalculatorPage;

    public TradersCalculatorPageServiceImpl(WebDriver driver){
        super(driver);
        tradersCalculatorPage = new TradersCalculatorPage(driver);
    }

    public TradersCalculatorPageServiceImpl inputLotAmount(String amount){
        tradersCalculatorPage = tradersCalculatorPage
                .lotInputSendKeys(amount)
                .calculateClick();
        return this;
    }

    public String getLotAmount(){
        return tradersCalculatorPage.getLotAmount();
    }
}
