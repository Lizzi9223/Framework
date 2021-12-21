package by.epam.qa.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{

    private final String PAGE_URL = "https://exness.com";

    private final By locatorSignInButton = By.xpath("//div[@class='sidebar-tabs__buttons']/a");
    private final By locatorToolsAndServices = By.xpath("//*[text()='Tools & Services ']");
    private final By locatorCurrencyConverter = By.xpath("//a[text()='Converter']");
    private final By locatorTradersCalculator = By.xpath("//a[text()='Calculator']");

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public SignInPage goToSignInPage(){
        WebElement signInButton = wait.waitUntilpresenceOfElementLocated(locatorSignInButton);
        String URLToClick = signInButton.getAttribute("href");
        driver.navigate().to(URLToClick);
        logger.info("HomePage: goToSignInPage");
        return new SignInPage(driver);
    }

    public HomePage toolsAndServicesMenuTabClick(){
        WebElement toolsAndServicesButton = wait.waitUntilpresenceOfElementLocated(locatorToolsAndServices);
        toolsAndServicesButton.click();
        return this;
    }

    public CurrencyConverterPage openCurrencyConverter(){
        WebElement currencyConverterButton = wait.waitUntilpresenceOfElementLocated(locatorCurrencyConverter);
        currencyConverterButton.click();
        return new CurrencyConverterPage(driver);
    }

    public TradersCalculatorPage openTradersCalculator(){
        WebElement tradersCalculatorButton = wait.waitUntilpresenceOfElementLocated(locatorTradersCalculator);
        tradersCalculatorButton.click();
        return new TradersCalculatorPage(driver);
    }

    @Override
    public HomePage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("HomePage: openPage");
        return this;
    }
}
