    public static WebElement waitForVisibilityAndClick(WebElement element, int time){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        return element;
    }

        public static void waitForVisibilityOfListElement(List<WebElement> elements, long timeout) {
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout))
                .until(driver -> elements.stream().anyMatch(WebElement::isDisplayed));
    }