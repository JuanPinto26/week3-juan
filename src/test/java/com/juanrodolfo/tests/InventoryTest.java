package com.juanrodolfo.tests;

import com.juanrodolfo.pages.InventoryPage;
import com.juanrodolfo.pages.LoginPage;
import com.juanrodolfo.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        // Login en setup para estas pruebas
        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLogin();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void testProductsTitleVisible() {
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Should be on inventory page");
        Assert.assertEquals(inventoryPage.getTitleText(), "Products", "Title should be Products");
    }

    @Test
    public void testAddToCartShowsBadge() {
        inventoryPage.addBackpackToCart();

        Assert.assertEquals(inventoryPage.getTitleText(), "Products", "Still on products page");
        Assert.assertTrue(inventoryPage.isCartBadgeDisplayed(), "Cart badge should appear after adding item");
    }

    @Test
    public void testOpenCartNavigation() {
        inventoryPage.openCart();

        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "URL should contain cart");
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("your cart"), "Page should mention Your Cart");
    }
}