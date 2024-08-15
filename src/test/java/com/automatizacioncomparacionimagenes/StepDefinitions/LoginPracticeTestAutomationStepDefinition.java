package com.automatizacioncomparacionimagenes.StepDefinitions;

import com.automatizacioncomparacionimagenes.Base.WebBasePage;
import com.automatizacioncomparacionimagenes.PageObjects.LoginPracticeTestAutomationPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class LoginPracticeTestAutomationStepDefinition {

    WebDriver driver = WebBasePage.getDriver();
    LoginPracticeTestAutomationPage loginPracticeTestAutomationPage = new LoginPracticeTestAutomationPage(driver);

    @Given("visualiza titulo login")
    public void visualizaTituloLogin(){
        Assert.assertTrue(loginPracticeTestAutomationPage.isVisibleTituloLogin());
    }

    @When("ingreso usuario {string}")
    public void ingresoUsuario(String usuario) {
        loginPracticeTestAutomationPage.inputUsuario(usuario);
    }

    @And("ingreso clave {string}")
    public void ingresoClave(String searchTerm) {
        loginPracticeTestAutomationPage.inputClave(searchTerm);
    }
}