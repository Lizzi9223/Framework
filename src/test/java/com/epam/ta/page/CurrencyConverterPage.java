package com.epam.ta.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CurrencyConverterPage extends AccountPage{

    private final String PAGE_URL = "https://www.exness.com/en/converter";

    private final By locatorAmountInput = By.xpath("//*[text()='Amount']/preceding-sibling::input");
    private final By locatorAllAmountInputs = By.xpath("//*[@class='inp-group__case']//input");

    public CurrencyConverterPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CurrencyConverterPage setAmount(){
        WebElement amountInput = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(locatorAmountInput));
        amountInput.clear();
        amountInput.sendKeys("0");
        return this;
    }

    public boolean areAllInputsSetToNull(){
        List<WebElement> allAmountInputs = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorAllAmountInputs));
        for(int i=0; i<allAmountInputs.size();i++){
            if(!allAmountInputs.get(i).getAttribute("value").equals("0")){
                return false;
            }
        }
        return true;
    }

    @Override
    public CurrencyConverterPage openPage(){
        driver.navigate().to(PAGE_URL);
        logger.info("Currency Converter opened");
        return this;
    }
}
