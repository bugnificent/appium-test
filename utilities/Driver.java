package com.velespit.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {
    //create a private constructor to remove access to this object
    // Private constructor to prevent instantiation
    private Driver() {
    }

    // ThreadLocal for AndroidDriver to support parallel testing
    private static InheritableThreadLocal<AndroidDriver> driverPool = new InheritableThreadLocal<>();

    /**
     * Gets the AndroidDriver instance. If it doesn't exist, creates a new one.
     *
     * @return AndroidDriver instance
     */
    public static AndroidDriver getDriver() {

        if (driverPool.get() == null) {
            String platformName = ConfigurationReader.getProperty("platformName");
            String deviceName = ConfigurationReader.getProperty("deviceName");
            String appPackage = ConfigurationReader.getProperty("appPackage");
            String appActivity = ConfigurationReader.getProperty("appActivity");
            String appiumServerURL = ConfigurationReader.getProperty("appiumServerURL");

            UiAutomator2Options options = NativeApp(platformName, deviceName, appPackage);
            options.setAppActivity(appActivity);
            options.setNoReset(false); // Set to true if you don't want to reset app state

            try {
                driverPool.set(new AndroidDriver(new URL(appiumServerURL), options));
                driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            } catch (MalformedURLException e) {
                throw new RuntimeException("Appium server URL is malformed", e);
            }
        }
        return driverPool.get();
    }

    private static UiAutomator2Options NativeApp(String platformName, String deviceName, String appPackage) {
        UiAutomator2Options options = new UiAutomator2Options();
//            options.setDeviceName("emulator-5554"); // Your device/emulator ID
//            options.withBrowserName("Chrome");        // Use "Chrome" or "Browser" (default Android browser)
//            options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        //For native apps
        options.setPlatformName(platformName);
        options.setDeviceName(deviceName);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setAppPackage(appPackage);
        return options;
    }

    /**
     * It will terminate application instance during each of our test if we want.
     */
    public static void resetApp() {
        if (driverPool.get() != null) {
            driverPool.get().terminateApp(ConfigurationReader.getProperty("appPackage"));
            driverPool.get().activateApp(ConfigurationReader.getProperty("appPackage"));
        }
    }

    /**
     * Closes the AndroidDriver instance and removes it from the ThreadLocal
     * It directs Appium to free up a device and any other resources for future tests.
     */
    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
