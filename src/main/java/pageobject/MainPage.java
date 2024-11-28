package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private static final String mainPageUrl = "https://stellarburgers.nomoreparties.site/";
    private static final By EnterBoxOnMainPage = By.xpath(".//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private static final By OrderButton = By.xpath(".//*[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    private static final By PrivateBox = By.xpath(".//*[text()='Личный Кабинет']");
    private static final By innerTable = By.xpath(".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Начинки']");
    private static final By SauceTable = By.xpath(".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Соусы']");
    private static final By Inner = By.xpath(".//*[@class='text text_type_main-default' and text()='Начинки']");
    private static final By Sauce = By.xpath(".//*[@class='text text_type_main-default' and text()='Соусы']");
    private static final By InnerAllIngredients = By.xpath(".//*[@class='BurgerIngredients_ingredients__list__2A-mT'][3]");
    private static final By SauceAllIngredients = By.xpath(".//*[@class='BurgerIngredients_ingredients__list__2A-mT'][2]");
    private static final By CreateBurgerBox = By.xpath(".//*[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");
    private static final By BunsTable = By.xpath(".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Булки']");
    private static final By Buns = By.xpath(".//*[@class='text text_type_main-default' and text()='Булки']");
    private static final By BunsAllIngredients = By.xpath(".//*[@class='BurgerIngredients_ingredients__list__2A-mT'][1]");
    public static final String expectedInner = "0\n" +
            "1337\n" +
            "Мясо бессмертных моллюсков Protostomia\n" +
            "0\n" +
            "3000\n" +
            "Говяжий метеорит (отбивная)\n" +
            "0\n" +
            "424\n" +
            "Биокотлета из марсианской Магнолии\n" +
            "0\n" +
            "988\n" +
            "Филе Люминесцентного тетраодонтимформа\n" +
            "0\n" +
            "300\n" +
            "Хрустящие минеральные кольца\n" +
            "0\n" +
            "874\n" +
            "Плоды Фалленианского дерева\n" +
            "0\n" +
            "762\n" +
            "Кристаллы марсианских альфа-сахаридов\n" +
            "0\n" +
            "4400\n" +
            "Мини-салат Экзо-Плантаго\n" +
            "0\n" +
            "4142\n" +
            "Сыр с астероидной плесенью";
    public static final String expectedSauces = "0\n" +
            "90\n" +
            "Соус Spicy-X\n" +
            "0\n" +
            "80\n" +
            "Соус фирменный Space Sauce\n" +
            "0\n" +
            "15\n" +
            "Соус традиционный галактический\n" +
            "0\n" +
            "88\n" +
            "Соус с шипами Антарианского плоскоходца";
    public static final String expectedBuns = "0\n" +
            "988\n" +
            "Флюоресцентная булка R2-D3\n" +
            "0\n" +
            "1255\n" +
            "Краторная булка N-200i";

    @Step("Открываем главную страницу")
    public  MainPage openMainPage() {
        driver.get(mainPageUrl);
        return this;
    }

    @Step("На главной странице находим кнопку Вход")
    public MainPage clickEnterOnMainPage() {
        driver.findElement(EnterBoxOnMainPage).click();
        return this;
    }

    @Step("Ищем Личный кабинет")
    public MainPage clickPrivateBox() {
        driver.findElement(PrivateBox).click();
        return this;
    }

    @Step("Видна ли надпись Соберите Бургер ")
    public static boolean isCreateBurgerBoxDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement EnterBoxExistWindow =
                wait.until(ExpectedConditions.visibilityOfElementLocated(CreateBurgerBox));
        return EnterBoxExistWindow.isDisplayed();
    }

    @Step("Нажимаем кнопку Вход")
    public MainPage openMainPageAndClickEnter() {
        openMainPage();
        clickEnterOnMainPage();
        return this;
    }

    @Step("Кликаем на ингредиенты")
    public MainPage innerClick() {
        driver.findElement(Inner).click();
        return this;
    }

    @Step("Скроллим до ингредиентов")
    public MainPage scrollToInner() {
        WebElement element = driver.findElement(innerTable);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    @Step("Получаем текст с ингредиентов")
    public String getTextFromInner() {
        String ingredientsInner = driver.findElement(InnerAllIngredients).getText();
        return ingredientsInner;
    }

    @Step("Открываем Ингредиенты")
    public MainPage openInner() {
        openMainPage();
        innerClick();
        scrollToInner();
        return this;
    }

    @Step("Кликаем на вкладку Соус")
    public MainPage sauceClick() {
        driver.findElement(Sauce).click();
        return this;
    }

    @Step("Скроллим до вкладки Соус")
    public MainPage scrollToSauce() {
        WebElement element = driver.findElement(SauceTable);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    @Step("Получаем текс Соуса")
    public String getTextFromSauce() {
        return driver.findElement(SauceAllIngredients).getText();

    }

    @Step("Открываем главную страницу, нажимаем соус")
    public MainPage openSauce() {
        openMainPage();
        sauceClick();
        scrollToSauce();
        return this;
    }

    @Step("Нажимаем булочки")
    public MainPage bunsClick() {
        driver.findElement(Buns).click();
        return this;
    }

    @Step("Скроллим до булочек")
    public MainPage scrollToBuns() {
        WebElement element = driver.findElement(BunsTable);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    @Step("Получаем текст с Булочек")
    public String getTextFromBuns() {
        return driver.findElement(BunsAllIngredients).getText();

    }

    @Step("Открываем Булки")
    public MainPage openBuns() {
        openMainPage();
        sauceClick();
        bunsClick();
        scrollToBuns();
        return this;
    }

    @Step("Открываем главную страницу, нажимаем на Личный кабинет")
    public MainPage clickPersonalPage() {
        openMainPage();
        clickPrivateBox();
        return this;
    }
    @Step("Видна ли кнопка заказ")
    public static boolean isOrderButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement EnterBoxExistWindow =
                wait.until(ExpectedConditions.visibilityOfElementLocated(OrderButton));
        return EnterBoxExistWindow.isDisplayed();
    }
}
