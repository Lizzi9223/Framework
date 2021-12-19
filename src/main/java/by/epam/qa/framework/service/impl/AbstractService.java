package by.epam.qa.framework.service.impl;

import by.epam.qa.framework.util.ExplicitWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class AbstractService {

    protected WebDriver driver;

    protected final Logger logger = LogManager.getRootLogger();

    protected AbstractService(WebDriver driver)
    {
        this.driver = driver;
    }
}
