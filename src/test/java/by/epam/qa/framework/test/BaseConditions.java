package by.epam.qa.framework.test;

import by.epam.qa.framework.driver.DriverSingleton;
import by.epam.qa.framework.model.User;
import by.epam.qa.framework.util.UserCreator;
import by.epam.qa.framework.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseConditions {
    protected WebDriver driver;
    protected final User TEST_USER = UserCreator.withCredentialsFromProperty();

    @BeforeClass()
    public void setUp(){
        driver = DriverSingleton.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void stopBrowser(){
        DriverSingleton.closeDriver();
    }
}
