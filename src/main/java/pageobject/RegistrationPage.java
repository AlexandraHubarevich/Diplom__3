package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }


    private static final String RegistrationPageUrl = "https://stellarburgers.nomoreparties.site/register";
    private static final By SameBoxForEmailAndName = By.cssSelector("[class='text input__textfield text_type_main-default']");
    private static final By PasswordBox = By.xpath(".//*[@class ='text input__textfield text_type_main-default' and @type='password']");
    private static final By RegisterButton = By.xpath(".//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private static final By IncorrectPasswordNotification = By.xpath(".//*[@class='input__error text_type_main-default']");
    private static final By EnterBoxOnRegisterPage = By.xpath(".//*[text()='Войти']");

    @Step("Открываем страницу регистрации")
    public RegistrationPage openRegPage() {
        driver.get(RegistrationPageUrl);
        return this;
    }

    @Step("Скроллим до имени/емаил")
    public RegistrationPage scrollToNameBox() {
        WebElement element = driver.findElement(SameBoxForEmailAndName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    @Step("Ищем бокс с именем. Вводим имя")
    public RegistrationPage setName(String name) {
        driver.findElements(SameBoxForEmailAndName).get(0).sendKeys(name);
        return this;
    }

    @Step("Ищем бокс с емаил. Вводим емаил")
    public RegistrationPage setEmail(String email) {
        driver.findElements(SameBoxForEmailAndName).get(1).sendKeys(email);
        return this;
    }

    @Step("Находим бокс с паролем, вводим пароль")
    public RegistrationPage setPassword(String password) {
        driver.findElement(PasswordBox).sendKeys(password);
        return this;

    }

    @Step("Кнопка зарегистрироваться")
    public RegistrationPage clickRegisterButton() {
        driver.findElement(RegisterButton).click();
        return this;
    }

    @Step("Ждем появления нотификации о некорректном пароле")
    public boolean isIncorrectPasswordNotificationDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement EnterBoxExistWindow =
                wait.until(ExpectedConditions.visibilityOfElementLocated(IncorrectPasswordNotification));
        return EnterBoxExistWindow.isDisplayed();
    }

    @Step("Открываем страницу регистрации и регистрируемся")
    public RegistrationPage openRegistrationPageAndRegister(String name, String email, String password) {
        openRegPage();
        scrollToNameBox();
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
        return this;
    }
    @Step("Скроллим до кнопки Вход")
    public RegistrationPage scrollToEnterBox() {
        WebElement element = driver.findElement(EnterBoxOnRegisterPage);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }
    @Step("Нажимаем вход на странице регистрации")
    public RegistrationPage clickEnterOnRegisterPage() {
        driver.findElement(EnterBoxOnRegisterPage).click();
        return this;
    }

    @Step("Нажимаем кнопку войти на странице Регистрация")
    public RegistrationPage clickOnEnterBoxOnRegisterPage() {
        openRegPage();
        scrollToEnterBox();
        clickEnterOnRegisterPage();
        return this;
    }

}

