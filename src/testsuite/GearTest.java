package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utility;

import java.time.Duration;

public class GearTest extends Utility {

    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    //1. userShouldAddProductSuccessFullyToShoppinCart()
    public void userShouldAddProductSuccessfullyToShoppingCart() {
        mouseHoverOnElement(By.xpath("//span[text()='Gear']")); // Mouse Hover on Gear Menu
        clicksOnElement(By.id("ui-id-25")); // Click on Bags
        clicksOnElement(By.xpath("//a[contains(text(),'Overnight Duffle')]")); // Click on Product Name ‘Overnight Duffle’
        // Verify the text ‘Overnight Duffle’
        Assert.assertEquals("text is not displayed", "Overnight Duffle", getTextFromElement(By.xpath("//span[contains(text(),'Overnight Duffle')]")));
        clearTextFromInputField(By.id("qty"));// Change Qty 3
        sendTextToElement(By.id("qty"), "3");
        clicksOnElement(By.id("product-addtocart-button"));// Click on ‘Add to Cart’ Button.
        // Verify the tex ‘You added Overnight Duffle to your shopping cart.’
        Assert.assertEquals("text is not displayed", "You added Overnight Duffle to your shopping cart.", getTextFromElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
        clicksOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));// Click on ‘shopping cart’ Link into message
        //Verify product name
        Assert.assertEquals("products is incorrect", "Overnight Duffle", getTextFromElement(By.xpath("//td[@class='col item']//div[@class='product-item-details']")));
        // Verify the Qty is ‘3’
        Assert.assertEquals("invalid qty", "3", getAttributeFromElement(By.xpath("//input[@title='Qty']")));
        // Verify the product price ‘$135.00’
        Assert.assertEquals("invalid price", "$135.00", getTextFromElement(By.cssSelector("td[class='col subtotal'] span[class='price']")));
        clearTextFromInputField(By.xpath("//input[@title='Qty']")); // Change Qty to ‘5’
        sendTextToElement(By.xpath("//input[@title='Qty']"), "5");
        clicksOnElement(By.xpath("//span[contains(text(),'Update Shopping Cart')]"));// Click on ‘Update Shopping Cart’ button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));//Wait for price update
        wait.until(ExpectedConditions.textToBe(By.cssSelector("td[class='col subtotal'] span[class='price']"), "$225.00"));
        //Verify the product price ‘$225.00’
        Assert.assertEquals("invalid price", "$225.00", getTextFromElement(By.cssSelector("td[class='col subtotal'] span[class='price']")));
}
}

