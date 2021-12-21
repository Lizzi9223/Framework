package by.epam.qa.framework.service.impl;

import by.epam.qa.framework.page.*;
import org.openqa.selenium.WebDriver;

public class HomePageServiceImpl extends AbstractService{

    private HomePage homePage;
    private SignInPage signInPage;
    private CurrencyConverterPage currencyConverterPage;
    private TradersCalculatorPage tradersCalculatorPage;

    public HomePageServiceImpl(WebDriver driver){
        super(driver);
        homePage = new HomePage(driver);
    }

    public SignInPageServiceImpl goToSignInPage() {
        signInPage =  homePage.goToSignInPage();
        return new SignInPageServiceImpl(driver);
    }

    public CurrencyConverterPageServiceImpl openCurrencyConverter(){
        currencyConverterPage = homePage
                .toolsAndServicesMenuTabClick()
                .openCurrencyConverter();
        return new CurrencyConverterPageServiceImpl(driver);
    }

    public TradersCalculatorPageServiceImpl openTradersCalculator(){
        tradersCalculatorPage = homePage
                .toolsAndServicesMenuTabClick()
                .openTradersCalculator();
        return new TradersCalculatorPageServiceImpl(driver);
    }

    public HomePageServiceImpl openPage() {
        homePage = homePage.openPage();
        return this;
    }
}
