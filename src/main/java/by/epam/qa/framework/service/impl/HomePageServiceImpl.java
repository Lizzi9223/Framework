package by.epam.qa.framework.service.impl;

import by.epam.qa.framework.page.AccountPage;
import by.epam.qa.framework.page.HomePage;
import by.epam.qa.framework.page.SignInPage;
import org.openqa.selenium.WebDriver;

public class HomePageServiceImpl extends AbstractService{

    private HomePage homePage;
    private SignInPage signInPage;

    public HomePageServiceImpl(WebDriver driver){
        super(driver);
        homePage = new HomePage(driver);
    }

    public SignInPageServiceImpl goToSignInPage() {
        signInPage =  homePage.goToSignInPage();
        return new SignInPageServiceImpl(driver);
    }

    public HomePageServiceImpl openPage() {
        homePage = homePage.openPage();
        return this;
    }
}
