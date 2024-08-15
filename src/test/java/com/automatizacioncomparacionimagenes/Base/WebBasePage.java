package com.automatizacioncomparacionimagenes.Base;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WebBasePage {

    protected static WebDriver driver;
    private static final long WAIT_TIMEOUT = 10;
    public static final int LARGE_WAIT_TIMEOUT = 30;

    // Constructor
    public WebBasePage(WebDriver driver) {
        WebBasePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Getter for driver
    public static WebDriver getDriver() {
        return driver;
    }

    // Setter for driver
    public static void setDriver(WebDriver driver) {
        WebBasePage.driver = driver;
    }

    // Check if element is visible
    public boolean isVisible(WebElement webElement) {
        return webElement.isDisplayed();
    }

    public void waitUntilElementIsVisible(WebElement element){
        try {
            await().atMost(LARGE_WAIT_TIMEOUT, SECONDS).until(() -> isVisible(element));
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException(String.format("No se encuentra el elemento después de 30 segundos\nElemento: %s", element));
        } catch (Exception e) {
            System.err.println("Error durante la espera de visibilidad del elemento: " + e.getMessage());
        }
    }

    public void waitUntilElementIsVisibleNonThrow(WebElement element, int waitTimeOut){
        try {
            await().atMost(waitTimeOut, SECONDS).until(() -> isVisible(element));
        } catch (ConditionTimeoutException e) {
            // Manejo de excepción sin lanzar
        } catch (Exception e) {
            Thread.currentThread().interrupt(); // Vuelve a establecer el estado de interrupción
            System.err.println("Interrupción durante la espera de visibilidad del elemento: " + e.getMessage());
        }
    }
}
