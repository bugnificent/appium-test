package com.velespit.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.velespit.utilities.Driver.*;

public abstract class LandingPage {

    public LandingPage() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    @AndroidFindBy(accessibility = "Login Screen")
    public WebElement loginScreen;

    @AndroidFindBy(accessibility = "Echo Box")
    public WebElement echoBoxScreen;

    @AndroidFindBy(accessibility = "List Demo")
    public WebElement listDemoScreen;

    @AndroidFindBy(accessibility = "Picker Demo")
    public WebElement listDateScreen;

}