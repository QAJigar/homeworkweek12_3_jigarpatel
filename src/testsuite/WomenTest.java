package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WomenTest extends Utility {
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
    //1. verifyTheSortByProductNameFilter
    public void verifyTheSortByProductNameFilter() {
        // Mouse Hover on Women Menu
        mouseHoverOnElement(By.xpath("//span[text()='Women']"));
        // Mouse Hover on Tops
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-9']//span[contains(text(),'Tops')]"));
        // Click on Jackets
        clicksOnElement(By.id("ui-id-11"));
        //products before sorting and make it in ascending order
        System.out.print(" before sorting : ");
        List<WebElement> originalProductList = driver.findElements(By.xpath("//strong[@class='product name product-item-name']"));
        List<String> originalProductNames = new ArrayList<>();
        for (WebElement e : originalProductList) {
            originalProductNames.add(e.getText());
        }
        //sort products in ascending order
        Collections.sort(originalProductNames);
        System.out.println(originalProductNames);
        // Select Sort By filter “Product Name”
        selectDropdownOptionByValue(By.id("sorter"), "name");
        //product list after sorting by 'Product Name'
        List<WebElement> productListAfterSorting = driver.findElements(By.xpath("//strong[@class='product name product-item-name']"));
        List<String> actualProductNamesAfterSorting = new ArrayList<>();
        for (WebElement e : productListAfterSorting) {
            actualProductNamesAfterSorting.add(e.getText());
        }
        System.out.println("After sorting products names: " + actualProductNamesAfterSorting);
        //Verify the products name display in alphabetical order
        Assert.assertEquals("products are not is ascending order", originalProductNames, actualProductNamesAfterSorting);

    }

    @Test
    //2. verifyTheSortByPriceFilter
    public void verifyTheSortByPriceFilter() {
        // Mouse Hover on Women Menu
        mouseHoverOnElement(By.xpath("//span[text()='Women']"));
        // Mouse Hover on Tops
        mouseHoverOnElement(By.xpath("//a[@id='ui-id-9']//span[contains(text(),'Tops')]"));
        // Click on Jackets
        clicksOnElement(By.id("ui-id-11"));
        //before sorting Price
        List<WebElement> beforeSortingPrice = driver.findElements(By.className("price-wrapper"));
        List<Double> beforePriceList = new ArrayList<>();
        for (WebElement p : beforeSortingPrice) {
            beforePriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        //sort values of before filter price
        Collections.sort(beforePriceList);
        //System.out.println("before filter sorting price:" + beforePriceList);
        // Select Sort By filter “Price”
        selectDropdownOptionByValue(By.id("sorter"), "price");
        //after sorting by 'Price'
        List<WebElement> afterSortingPrice = driver.findElements(By.className("price-wrapper"));
        //remove '$' symbol from price and convert string into double
        List<Double> afterSortingPriceList = new ArrayList<>();
        for (WebElement p : afterSortingPrice) {
            afterSortingPriceList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        // System.out.println("after sorting price:" + afterSortingPriceList);
        // Verify the products price display in Low to High
        Assert.assertEquals("Price is not display in Low to high", beforePriceList, afterSortingPriceList);
        System.out.println("after sorting price:" + afterSortingPriceList);
    }
}
