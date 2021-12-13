package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TradersCalculatorPage extends AbstractPage{

    private final String PAGE_URL = "https://www.exness.com/en/calculator";

    private final By locatorLotInput = By.xpath("//*[text()='Lot']/preceding-sibling::input");
    private final By locatorCalculateButton = By.xpath("//button[text()='Calculate']");

    public TradersCalculatorPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public TradersCalculatorPage inputLotAmount(){
        WebElement lotInput = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorLotInput));
        lotInput.clear();
        lotInput.sendKeys("0");

        WebElement calcButton = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorCalculateButton));
        calcButton.click();

        return this;
    }

    public String getLotAmount(){
        WebElement lotInput = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorLotInput));
        return lotInput.getAttribute("value");
    }

    @Override
    public TradersCalculatorPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("Trader's Calculator opened");
        return this;
    }
}
