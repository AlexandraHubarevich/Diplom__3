package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalPage {
    private WebDriver driver;

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final By ProfileBox = By.xpath(".//*[text()='Профиль']");
    private static final By ConstructorBox = By.xpath(".//*[text()='Конструктор']");
    private static final By LogoBox = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");

    private static final By GetOut = By.xpath(".//*[text()='Выход']");


    @Step("Видна ли надпись Профиль")
    public boolean isProfileBoxDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement EnterBoxExistWindow =
                wait.until(ExpectedConditions.visibilityOfElementLocated(ProfileBox));
        return EnterBoxExistWindow.isDisplayed();
    }

    @Step("Кликаем на Конструктор")
    public PersonalPage clickOnСonstructor() {
        driver.findElement(ConstructorBox).click();
        return this;
    }

    @Step("Нажимаем кнопку Выход")
    public PersonalPage clickGetOut() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(GetOut).click();
        return this;
    }

    @Step("Нажимаем на лого")
    public PersonalPage clickOnLogo() {
        driver.findElement(LogoBox).click();
        return this;
    }



}

