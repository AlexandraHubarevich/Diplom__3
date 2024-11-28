package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    private static final String forgotPasswordPageUrl = "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final By enterBoxOnForgotPasswordPage = By.xpath(".//*[text()='Войти']");


    @Step("Открываем страницу с линкой Забыл пароль")
    public ForgotPasswordPage openForgotPasswordPage() {
        driver.get(forgotPasswordPageUrl);
        return this;

    }

    @Step("Кнопка Войти на странице Забыл Пароль")
    public ForgotPasswordPage clickOnEnterFromForgotPasswordPage() {
        driver.findElement(enterBoxOnForgotPasswordPage).click();
        return this;
    }

    @Step("Нажимаем кнопку войти на странице Забыл Пароль")
    public  ForgotPasswordPage clickEnterFromForgotPasswordPage(){
        openForgotPasswordPage();
        clickOnEnterFromForgotPasswordPage();
        return this;
    }
}

