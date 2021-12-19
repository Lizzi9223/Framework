package by.epam.qa.framework.test;

import by.epam.qa.framework.page.AccountPage;
import by.epam.qa.framework.page.HomePage;
import by.epam.qa.framework.page.TradingPage;
import by.epam.qa.framework.service.impl.AccountPageServiceImpl;
import by.epam.qa.framework.service.impl.HomePageServiceImpl;
import by.epam.qa.framework.service.impl.TraidingPageServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAccessTests extends BaseConditions {
	private final int BALANCE_VALUE = 1000;
	private final String ACCOUNT_NAME = "NewAccName";

	private final String URL_PART = "/webtrading/";

	@Test(description = "set demo account balance", priority = 1)
	public void setBalance(){
		AccountPageServiceImpl accountPageService = new HomePageServiceImpl(driver)
				.openPage()
				.goToSignInPage()
				.signIn(TEST_USER)
				.setBalance(BALANCE_VALUE);

		Assert.assertEquals(accountPageService.getBalance(), BALANCE_VALUE);
	}

	@Test(description = "rename account", priority = 2)
	public void renameAccount(){
		AccountPageServiceImpl accountPageService = new HomePageServiceImpl(driver)
				.openPage()
				.goToSignInPage()
				.signIn(TEST_USER)
				.renameAccount(ACCOUNT_NAME);

		Assert.assertEquals(accountPageService.getAccountName(), ACCOUNT_NAME);
	}

	@Test(description = "open web terminal", priority = 3)
	public void startTraiding(){
		TraidingPageServiceImpl traidingPageService = new HomePageServiceImpl(driver)
				.openPage()
				.goToSignInPage()
				.signIn(TEST_USER)
				.startTraiding();

		Assert.assertTrue(driver.getCurrentUrl().contains(URL_PART));
	}
}
