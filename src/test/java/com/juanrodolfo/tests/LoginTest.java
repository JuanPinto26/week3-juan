package com.juanrodolfo.tests;

import com.juanrodolfo.pages.InventoryPage;
import com.juanrodolfo.pages.LoginPage;
import com.juanrodolfo.utils.ConfigReader;
import com.juanrodolfo.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod
    public void setUp() {

        driver = DriverFactory.createDriver();

        // 🔥 Week 3 requirement: using ConfigReader
        System.out.println("Environment: " + ConfigReader.getEnv());
        System.out.println("Base URL: " + ConfigReader.getBaseUrl());
        System.out.println("Browser: " + ConfigReader.getBrowser());

        driver.get(ConfigReader.getBaseUrl());

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testValidLogin() {

        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "URL should contain inventory");

        Assert.assertEquals(inventoryPage.getTitleText(),
                "Products",
                "Title should be Products");
    }

    @Test
    public void testInvalidLogin() {

        loginPage.typeUsername("wrong_user");
        loginPage.typePassword("wrong_pass");
        loginPage.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"),
                "Still should be on saucedemo domain");

        Assert.assertTrue(loginPage.getErrorText().length() > 0,
                "Error message should be displayed");
    }

    @Test
    public void testEmptyLogin() {

        loginPage.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"),
                "Still should be on saucedemo domain");

        Assert.assertTrue(loginPage.getErrorText().toLowerCase().contains("required"),
                "Should show required error");
    }
}