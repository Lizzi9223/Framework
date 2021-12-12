package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.AccountPage;
import com.epam.ta.page.HomePage;
import com.epam.ta.page.TradingPage;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAccessTests extends CommonConditions {
	@Test(description = "set demo account balance", priority = 1)
	public void setBalance(){
		User testUser = UserCreator.withCredentialsFromProperty();
		AccountPage accountPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(testUser)
				.setBalance();

		Assert.assertEquals(accountPage.getBalance(), 1000);
	}

	@Test(description = "rename account", priority = 1)
	public void renameAccount(){
		User testUser = UserCreator.withCredentialsFromProperty();
		AccountPage accountPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(testUser)
				.renameAccount();

		Assert.assertEquals(accountPage.getAccountName(), "NewAccName");
	}

	@Test(description = "open web terminal", priority = 2)
	public void startTraiding(){
		User testUser = UserCreator.withCredentialsFromProperty();
		TradingPage tradingPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(testUser)
				.startTraiding();

		Assert.assertTrue(driver.getCurrentUrl().contains("/webtrading/"));
	}
}
