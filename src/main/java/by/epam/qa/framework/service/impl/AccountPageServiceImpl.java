package by.epam.qa.framework.service.impl;

import by.epam.qa.framework.page.AccountPage;
import by.epam.qa.framework.page.CurrencyConverterPage;
import by.epam.qa.framework.page.TradersCalculatorPage;
import by.epam.qa.framework.page.TradingPage;
import org.openqa.selenium.WebDriver;

public class AccountPageServiceImpl extends AbstractService {

    private AccountPage accountPage;
    private TradingPage tradingPage;
    private TradersCalculatorPage tradersCalculatorPage;
    private CurrencyConverterPage currencyConverterPage;

    public AccountPageServiceImpl(WebDriver driver){
        super(driver);
        accountPage = new AccountPage(driver);
    }

    public int getBalance() {
        return accountPage
                .openDemoTab()
                .getBalance();
    }

    public AccountPageServiceImpl setBalance(int balance) {
        accountPage =  accountPage
                    .openDemoTab()
                    .setBalanceButtonClick()
                    .inputAreaBalanceSendKeys(balance)
                    .saveNewBalance();
        return this;
    }

    public String getAccountName() {
        return accountPage
                .openDemoTab()
                .getAccountName();
    }

    public AccountPageServiceImpl renameAccount(String name) {
        accountPage =  accountPage
                    .openDemoTab()
                    .settingsClick()
                    .renameAccountClick()
                    .inputAreaNameSendKeys(name)
                    .saveNewAccountName();
        return this;
    }

    public TraidingPageServiceImpl startTraiding() {
        tradingPage =  accountPage
                    .openDemoTab()
                    .traidingClick()
                    .goToTraidingPage();
        return new TraidingPageServiceImpl(driver);
    }

    public CurrencyConverterPageServiceImpl openCurrencyConverter() {
        currencyConverterPage =  accountPage
                    .openHelpDropDown()
                    .openCurrencyConverter();
        return new CurrencyConverterPageServiceImpl(driver);
    }

    public TradersCalculatorPageServiceImpl openTradersCalculator() {
        tradersCalculatorPage =  accountPage
                    .openHelpDropDown()
                    .openTradersCalculator();
        return new TradersCalculatorPageServiceImpl(driver);
    }
}
