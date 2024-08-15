package com.automatizacioncomparacionimagenes.PageObjects;

import com.automatizacioncomparacionimagenes.Base.WebBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPracticeTestAutomationPage extends WebBasePage {

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement usuario;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement clave;

    @FindBy(xpath = "//*[@id=\"login\"]/h2")
    private WebElement tituloLogin;

    // Constructor
    public LoginPracticeTestAutomationPage(WebDriver driver) {
        super(driver);
    }

    // Method to enter search term
    public void inputUsuario(String usuario) {
        waitUntilElementIsVisible(this.usuario);
        this.usuario.sendKeys(usuario);
    }

    public void inputClave(String clave){
        waitUntilElementIsVisible(this.clave);
        this.clave.sendKeys(clave);
    }

    public boolean isVisibleTituloLogin(){
        waitUntilElementIsVisibleNonThrow(tituloLogin, 10);
        return isVisible(tituloLogin);
    }
}
