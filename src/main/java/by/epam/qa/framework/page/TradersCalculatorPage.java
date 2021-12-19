package by.epam.qa.framework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TradersCalculatorPage extends AbstractPage{

    private final String PAGE_URL = "https://www.exness.com/en/calculator";

    private final By locatorLotInput = By.xpath("//*[text()='Lot']/preceding-sibling::input");
    private final By locatorCalculateButton = By.xpath("//button[text()='Calculate']");

    public TradersCalculatorPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public TradersCalculatorPage lotInputSendKeys(String amount){
        WebElement lotInput = wait.waitUntilpresenceOfElementLocated(locatorLotInput);
        lotInput.clear();
        lotInput.sendKeys(amount);
        return this;
    }

    public TradersCalculatorPage calculateClick(){
        WebElement calcButton = wait.waitUntilpresenceOfElementLocated(locatorCalculateButton);
        calcButton.click();
        logger.info("TradersCalculatorPage: inputLotAmount");
        return this;
    }

    public String getLotAmount(){
        WebElement lotInput = wait.waitUntilpresenceOfElementLocated(locatorLotInput);
        logger.info("TradersCalculatorPage: getLotAmount");
        return lotInput.getAttribute("value");
    }

    @Override
    public TradersCalculatorPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("TradersCalculatorPage: openPage");
        return this;
    }
}
