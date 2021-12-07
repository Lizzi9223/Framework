package com.epam.ta.test;

import com.epam.ta.model.User;
import com.epam.ta.page.AccountPage;
import com.epam.ta.page.HomePage;
import com.epam.ta.page.TradingPage;
import com.epam.ta.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserAccessTests extends CommonConditions {
	@Test(description = "set demo account balance")
	public void oneCanLoginExness(){
		User testUser = UserCreator.withCredentialsFromProperty();
		AccountPage accountPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(testUser)
				.setBalance();

		Assert.assertEquals(accountPage.getBalance(), 1000);
	}

	@Test(description = "open web terminal")
	public void trading(){
		User testUser = UserCreator.withCredentialsFromProperty();
		TradingPage tradingPage = new HomePage(driver)
				.openPage()
				.goToSignInPage()
				.signIn(testUser)
				.startTraiding();

		Assert.assertTrue(driver.getCurrentUrl().contains("/webtrading/"));
	}
}
