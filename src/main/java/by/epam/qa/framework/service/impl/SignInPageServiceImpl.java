package by.epam.qa.framework.service.impl;

import by.epam.qa.framework.model.User;
import by.epam.qa.framework.page.AccountPage;
import by.epam.qa.framework.page.SignInPage;
import org.openqa.selenium.WebDriver;

public class SignInPageServiceImpl extends AbstractService {

    private SignInPage signInPage;
    private AccountPage accountPage;

    public SignInPageServiceImpl(WebDriver driver){
        super(driver);
        signInPage = new SignInPage(driver);
    }

    public AccountPageServiceImpl signIn(User user) {
        accountPage = signInPage.signIn(user);
        return new AccountPageServiceImpl(driver);
    }

    public SignInPageServiceImpl openPage() {
        signInPage = signInPage.openPage();
        return this;
    }
}
