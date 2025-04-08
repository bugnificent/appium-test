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
     * Waits for any element in the list to be visible
     *
     * @param elements list of web elements
     * @param timeout timeout in seconds
     */
    public static void refreshAndWaitForVisibilityOfListElement(List<WebElement> elements, int timeout) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout))
                        .until(driver -> elements.stream().anyMatch(WebElement::isDisplayed));
                return;
            } catch (StaleElementReferenceException e) {
                Driver.getDriver().navigate().refresh();
                attempts++;
            }
        }
        throw new StaleElementReferenceException("Element still stale after 3 refresh attempts");
    }