package by.epam.qa.framework.service.impl;

import by.epam.qa.framework.page.AccountPage;
import by.epam.qa.framework.page.CurrencyConverterPage;
import by.epam.qa.framework.page.TradersCalculatorPage;
import by.epam.qa.framework.page.TradingPage;
import org.openqa.selenium.WebDriver;

public class CurrencyConverterPageServiceImpl extends AbstractService {

    private CurrencyConverterPage currencyConverterPage;

    public CurrencyConverterPageServiceImpl(WebDriver driver) {
        super(driver);
        currencyConverterPage = new CurrencyConverterPage(driver);
    }

    public CurrencyConverterPageServiceImpl setAmount(String amount){
        currencyConverterPage = currencyConverterPage.setAmount(amount);
        return this;
    }

    public boolean areAllInputsSetToNull(){
        return currencyConverterPage.areAllInputsSetToNull();
    }
}
