    /**
     * Merged version of waitForVisibility and click
     * @param element
     * @param time
     * @return
     */
    public static WebElement waitForVisibilityAndClick(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        return element;
    }

    /**
     * Waits for element matching the locator to be visible on the page
     *
     * @param locator
     * @param time
     * @return
     */
    public static WebElement waitForVisibility(By locator, int time) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }