package com.epam.ta.page;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractPage
{
	protected WebDriver driver;

	protected abstract AbstractPage openPage();
	protected final int WAIT_TIMEOUT_SECONDS = 60;

	protected final Logger logger = LogManager.getRootLogger();

	protected AbstractPage(WebDriver driver)
	{
		this.driver = driver;
	}
}
