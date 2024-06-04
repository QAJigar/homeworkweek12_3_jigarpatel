package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    public void clickOnElement(By by){
        driver.findElement(by).click();
    }
    //this method get text from element
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
    //this method will send text to element
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }
    //selectByValueFromDropdown(By by, String value)
    public void selectByValueFromDropdown(By by, String value){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);// Create the object of Select class
        select.selectByValue(value); // Select by visible Text
    }
    //this method clicks on element
    public void clicksOnElement(By by) {
        driver.findElement(by).click();
    }

    //this method clear value from input element
    public void clearTextFromInputField(By by) {
        driver.findElement(by).clear();
    }

    //this method getAttribute from element
    public String getAttributeFromElement(By by) {
        return driver.findElement(by).getAttribute("value");
    }

    //this method select option from dropdown
    public void selectDropdownOptionByValue(By by, String value) {
        Select option = new Select(driver.findElement(by));
        option.selectByValue(value);
    }

    //this method mouse hover on element
    public void mouseHoverOnElement(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
}

}
