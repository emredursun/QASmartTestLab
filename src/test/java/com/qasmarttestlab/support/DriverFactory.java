package com.qasmarttestlab.support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.time.Duration;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static WebDriver get() { return TL.get(); }

    public static void create() {
        try {
            String remote = System.getProperty("selenium.remote", System.getenv("SELENIUM_REMOTE")); // http://localhost:4444/wd/hub
            WebDriver driver;
            if (remote != null && !remote.isBlank()) {
                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("browserName", "chrome");
                driver = new RemoteWebDriver(URI.create(remote).toURL(), caps);
            } else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().window().maximize();
            TL.set(driver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create WebDriver", e);
        }
    }

    public static void tearDown() {
        WebDriver d = TL.get();
        if (d != null) {
            d.quit();
            TL.remove();
        }
    }
}