package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utility.Utility;

import java.time.Duration;

/**
 * userShouldAddProductSuccessFullyTo
 * ShoppinCart()
 * Mouse Hover on Gear Menu
 * Click on Bags
 * Click on Product Name ‘Overnight Duffle’
 * Verify the text ‘Overnight Duffle’
 * Change Qty 3
 * Click on ‘Add to Cart’ Button.
 * Verify the text
 * ‘You added Overnight Duffle to your shopping cart.’
 * Click on ‘shopping cart’ Link into
 * message
 * Verify the product name ‘Cronus Yoga Pant’
 * Verify the Qty is ‘3’
 * Verify the product price ‘$135.00’
 * Change Qty to ‘5’
 * Click on ‘Update Shopping Cart’ button
 * Verify the product price ‘$225.00’
 */
public class GearTest extends Utility {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {

        //Mouse Hover on Gear Menu and click
        mouseHoverToElement(By.xpath("//span[contains(text(),'Gear')]"));

        //Click on Bags
        clickOnElement(By.xpath("//a[@id='ui-id-25']//span[contains(text(),'Bags')]"));

        //Click on Product Name ‘Overnight Duffle’
        clickOnElement(By.xpath("//a[normalize-space()='Overnight Duffle']"));

        //Change Qty 3
        sendTextToElement(By.xpath("//input[@id='qty']"), Keys.DELETE + "3");

        //Click on ‘Add to Cart’ Button.
        clickOnElement(By.xpath("//button[@id='product-addtocart-button']"));

        //Verify the text
        Assert.assertEquals("Label does not match", "You added Overnight Duffle to your shopping cart.", getTextFromElement(By.xpath("//div[@class='message-success success message']")));

        //Click on ‘shopping cart’ Link into message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        //* Verify the Qty is ‘3’
        Assert.assertEquals("Quantity does not match", "3", getTextFromElement(By.xpath("//input[@id='cart-103735-qty']")));

        //Verify the product name ‘Overnight Duffle’
        Assert.assertEquals("Label does not match", "Overnight Duffle", getTextFromElement(By.id("//td[@class='col item']//a[normalize-space()='Overnight Duffle']")));

        //Verify the product price ‘$135.00’
        Assert.assertEquals("Price does not match", "$135.00", getTextFromElement(By.id("td[class='col subtotal'] span[class='price']")));

        Thread.sleep(500);

        //* Change Qty to ‘5’
        sendTextToElement(By.cssSelector("td[class='col qty'] input[class*='input-text qty']"), Keys.DELETE + "5");

        // Click On 'Update Shopping Cart' button
        clickOnElement(By.xpath("//span[text()='Update Shopping Cart']"));

        // Verify the product price '$225.00'
        Assert.assertEquals("$225.00", By.xpath("//span[@class='cart-price']//span[@class='price'][text()='$225.00']"), "$225.00");

    }
@After
    public void closeBrowser() {

        driver.close();
    }
}
