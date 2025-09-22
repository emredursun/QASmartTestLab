package com.qasmarttestlab.pages;

import com.qasmarttestlab.support.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver d() { return DriverFactory.get(); }
    private final By user = By.id("username");
    private final By pass = By.id("password");
    private final By submit = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");

    public LoginPage open() {
        d().get("https://the-internet.herokuapp.com/login");
        return this;
    }

    public LoginPage login(String u, String p) {
        d().findElement(user).clear(); d().findElement(user).sendKeys(u);
        d().findElement(pass).clear(); d().findElement(pass).sendKeys(p);
        d().findElement(submit).click();
        return this;
    }

    public String message() {
        return d().findElement(flash).getText();
    }
}

