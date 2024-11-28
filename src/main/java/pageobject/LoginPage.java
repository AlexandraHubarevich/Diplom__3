package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String loginPageUrl = "https://stellarburgers.nomoreparties.site/login";
    private static final By EmailBoxOnLoginPage = By.xpath(".//*[@class='text input__textfield text_type_main-default' and @type='text']");
    private static final By PasswordBoxonLoginPage = By.xpath(".//*[@class='text input__textfield text_type_main-default' and @type='password']");
    private static final By EnterBox = By.xpath(".//*[text()='Вход']");
    private static final By EnterBoxOnLoginPage = By.xpath("//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");


    @Step("Ждем видимость кнопки Вход")
    public boolean isEnterBoxDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement EnterBoxExistWindow =
                wait.until(ExpectedConditions.visibilityOfElementLocated(EnterBox));
        return EnterBoxExistWindow.isDisplayed();
    }


    @Step("Скроллим до бокса емайл")
    public LoginPage scrollToEmailBox() {
        WebElement element = driver.findElement(EmailBoxOnLoginPage);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    @Step("В бокс емаил мы вводим почту")
    public LoginPage setEmailOnLoginPage(String email) {
        driver.findElement(EmailBoxOnLoginPage).sendKeys(email);
        return this;
    }

    @Step("В бокс пароль мы вводим пароль")
    public LoginPage setPasswordOnLoginPage(String password) {
        driver.findElement(PasswordBoxonLoginPage).sendKeys(password);
        return this;
    }

    @Step("Нажимаем вход на странице Логина")
    public LoginPage clickEnterOnLoginPage() {
        driver.findElement(EnterBoxOnLoginPage).click();
        return this;
    }

    @Step("Видна ли кнопка Вход")
    public static boolean isEnterButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement EnterBoxExistWindow =
                wait.until(ExpectedConditions.visibilityOfElementLocated(EnterBoxOnLoginPage));
        return EnterBoxExistWindow.isDisplayed();
    }


    @Step("Логин на странице логина")
    public LoginPage loginFromLoginPage(String email, String password) {
        scrollToEmailBox();
        setEmailOnLoginPage(email);
        setPasswordOnLoginPage(password);
        clickEnterOnLoginPage();
        return this;
    }
}
