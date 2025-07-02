package com.juaracoding.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Profilepage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "header button[aria-haspopup='true']")
    WebElement navbarButton;

    @FindBy(css = "#profile-menu button.css-uajqx8")
    WebElement logoutButton;

    public Profilepage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navbarButton() {
        wait.until(ExpectedConditions.elementToBeClickable(navbarButton));
        navbarButton.click();
    }
    public void logoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }
}
