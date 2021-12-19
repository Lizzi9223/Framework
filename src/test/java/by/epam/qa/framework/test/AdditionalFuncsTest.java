package by.epam.qa.framework.test;

import by.epam.qa.framework.page.CurrencyConverterPage;
import by.epam.qa.framework.page.HomePage;
import by.epam.qa.framework.page.TradersCalculatorPage;
import by.epam.qa.framework.page.*;
import by.epam.qa.framework.service.impl.CurrencyConverterPageServiceImpl;
import by.epam.qa.framework.service.impl.HomePageServiceImpl;
import by.epam.qa.framework.service.impl.TradersCalculatorPageServiceImpl;
import by.epam.qa.framework.service.impl.TraidingPageServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdditionalFuncsTest extends BaseConditions {
    private final String ZERO_STRING = "0";
    private final String AFTER_VALIDATION_VALUE = "0.01";

    @Test(description = "when amount is zero, other fields must be zero", priority = 1)
    public void currencyConverterWithZeroValue(){
        CurrencyConverterPageServiceImpl currencyConverterPageService = new HomePageServiceImpl(driver)
                .openPage()
                .goToSignInPage()
                .signIn(TEST_USER)
                .openCurrencyConverter()
                .setAmount(ZERO_STRING);

        Assert.assertEquals(currencyConverterPageService.areAllInputsSetToNull(), true);
    }

    @Test(description = "when lot is zero, after submit it must be 0.01", priority = 2)
    public void tradersCalcLotInputValidationForZero(){
        TradersCalculatorPageServiceImpl tradersCalculatorPageService = new HomePageServiceImpl(driver)
                .openPage()
                .goToSignInPage()
                .signIn(TEST_USER)
                .openTradersCalculator()
                .inputLotAmount(ZERO_STRING);

        Assert.assertEquals(tradersCalculatorPageService.getLotAmount(), AFTER_VALIDATION_VALUE);
    }
}
