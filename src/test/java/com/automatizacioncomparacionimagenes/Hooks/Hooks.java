package com.automatizacioncomparacionimagenes.Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.automatizacioncomparacionimagenes.Base.WebBasePage;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {

    private static Properties properties;

    @Before
    public void setUp() {
        try {
            System.out.println("Iniciando la configuración antes de cada prueba");

            // Cargar las propiedades
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);

// Configurar WebDriver
            System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver.path"));
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-web-security");
            options.addArguments("--allow-running-insecure-content");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            WebDriver driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(properties.getProperty("base.url"));

            // Establecer el driver en la WebBasePage
            WebBasePage.setDriver(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error durante la configuración del WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        System.out.println("Limpiando después de cada prueba");
        WebDriver driver = WebBasePage.getDriver();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error al cerrar el WebDriver: " + e.getMessage());
            }
        }
    }


    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
