package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.*;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdditionalFuncsTest extends CommonConditions{
    private final User testUser = UserCreator.withCredentialsFromProperty();

    @Test(description = "when amount is zero, other fields must be zero", priority = 1)
    public void currencyConverterWithZeroValue(){
        CurrencyConverterPage currencyConverterPage = new HomePage(driver)
                .openPage()
                .goToSignInPage()
                .signIn(testUser)
                .openHelpDropDown()
                .openCurrencyConverter()
                .setAmount();

        Assert.assertEquals(currencyConverterPage.areAllInputsSetToNull(), true);
    }

    @Test(description = "when lot is zero, after submit it must be 0.01", priority = 2)
    public void tradersCalcLotInputValidationForZero(){
        TradersCalculatorPage tradersCalculatorPage = new HomePage(driver)
                .openPage()
                .goToSignInPage()
                .signIn(testUser)
                .openHelpDropDown()
                .openTradersCalculator()
                .inputLotAmount();

        Assert.assertEquals(tradersCalculatorPage.getLotAmount(), "0.01");
    }
}
