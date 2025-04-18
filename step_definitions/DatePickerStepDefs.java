package com.velespit.step_definitions;

import com.velespit.pages.DatePage;
import org.testng.annotations.Test;

import static com.velespit.utilities.BrowserUtils.*;

public class DatePickerStepDefs extends DatePage {

    private static final String month = "September";

    @Test
    public void datePickTest() {

        try {
            waitForVisibilityAndClick(listDateScreen, 10);

            waitForVisibilityAndClick(monthButton, 10);

            WaitForVisibility(monthButtonBy, 10);

            selectMonth(month);

            waitForVisibilityAndClick(dayButton, 10);

            WaitForVisibility(monthButtonBy, 10);

            selectDayWithScrollAndVerify(month, "20");

            waitForVisibilityAndClick(learnMoreButton, 10);

            waitForVisibilityAndClick(okButton, 10);
            
            waitForVisibilityAndClick(turnLandingPage, 10);

        } catch (Throwable e) {
            throw new RuntimeException("Issue occurred on Date Picker: " + e.getMessage());
        }

    }

}
