package com.qasmarttestlab.stepdefs.common;

import com.qasmarttestlab.support.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before(order = 0)
    public void start() {
        DriverFactory.create();
    }

    @After(order = 0)
    public void stop(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] png = ((TakesScreenshot) DriverFactory.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(png, "image/png", "failed-step");
        }
        DriverFactory.tearDown();
    }
}