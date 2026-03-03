package com.juanrodolfo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private final WebDriver driver;

    // Locators (>=3)
    private final By pageTitle = By.className("title");
    private final By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions (>=3)
    public String getTitleText() {
        return driver.findElement(pageTitle).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(addBackpackBtn).click();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }

    public boolean isCartBadgeDisplayed() {
        return driver.findElements(cartBadge).size() > 0;
    }
}