package com.velespit.pages;

import com.velespit.utilities.Driver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class DatePage extends LandingPage {

    @AndroidFindBy(accessibility = "monthPicker")
    public WebElement monthButton;

    @AndroidFindBy(accessibility = "dayPicker")
    public WebElement dayButton;

    @AndroidFindBy(id = "android:id/text1")
    public List<WebElement> listOfMonthElement;

    @AndroidFindBy(id = "android:id/text1")
    public List<WebElement> listOfDaysElement;

    @AndroidFindBy(accessibility = "learnMore")
    public WebElement learnMoreButton;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement okButton;

    @AndroidFindBy(accessibility = "Navigate Up")
    public WebElement turnLandingPage;
    
    @AndroidFindBy(id = "android:id/text1")
    public By monthButtonBy;

    public void selectDayWithScrollAndVerify(String monthText, String dayText) {

//        List<WebElement> listOfDays = listOfDaysElement;
//        for (WebElement day : listOfDays) {
//            System.out.println(day.getText());
//
//            if (day.getText().equals("20")) {
//                day.click();
//                break;
//            }
//        }
        WebElement day = Driver.getDriver().findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().text(\"" + dayText + "\"))"
                )
        );
        day.click();

        WebElement selectedMonth = Driver.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + monthText + "\")"));
        WebElement selectedDay = Driver.getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + dayText + "\")"));

        Assert.assertEquals(selectedMonth.getText(), monthText);
        Assert.assertEquals(selectedDay.getText(), dayText);

    }

    public void selectMonth(String month) {
        for (WebElement eachMonth : listOfMonthElement) {
            System.out.println(eachMonth.getText());

            if (eachMonth.getText().equals(month)) {
                eachMonth.click();
                break;
            }
        }
    }

}
