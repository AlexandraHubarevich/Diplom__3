import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalPage;

import static org.junit.Assert.assertEquals;
import static webdrivercreator.WebDriverCreator.createWebDriver;

public class checkPersonalPageTest {

    private WebDriver driver;
    Faker faker = new Faker();
    private String email = faker.internet().emailAddress();
    private String name = faker.name().firstName();
    private String password = faker.internet().password(8, 10);
    private UserClient userClient = new UserClient();
    private String accessToken;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().window().maximize();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";

    }

    @Test
    @DisplayName("Проверь переход по клику на «Личный кабинет»")
    public void successRegistrationTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.сreateUniqueUser(user);
        accessToken = responseCreate.jsonPath().getString("accessToken");

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        PersonalPage personalPage = new PersonalPage(driver);
        mainPage.clickPersonalPage();
        loginPage.loginFromLoginPage(user.getEmail(), user.getPassword());
        mainPage.clickPrivateBox();
        Assert.assertTrue(personalPage.isProfileBoxDisplayed());//Переход работает, если мы видим надпись Профиль, которая есть в личном кабинете


    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор: Проверь переход по клику на «Конструктор»")
    public void checkConstructorTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.сreateUniqueUser(user);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalPage personalPage = new PersonalPage(driver);

        mainPage.clickPersonalPage();
        loginPage.loginFromLoginPage(user.getEmail(), user.getPassword());
        mainPage.clickPrivateBox();


        personalPage.clickOnСonstructor();//здесь в личном кабинете мы кликаем на надпись Конструктор
        assertEquals(RestAssured.baseURI, driver.getCurrentUrl());//переход работает, если линка главная и есть надпись Создать бургер
        Assert.assertTrue(MainPage.isCreateBurgerBoxDisplayed());
        accessToken = responseCreate.jsonPath().getString("accessToken");
    }


    @Test
    @DisplayName("Переход из личного кабинета по клику на Лого")
    public void checkLogoTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.сreateUniqueUser(user);
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalPage personalPage = new PersonalPage(driver);

        mainPage.clickPersonalPage();
        loginPage.loginFromLoginPage(user.getEmail(), user.getPassword());
        mainPage.clickPrivateBox();

        personalPage.clickOnLogo();//здесь в личном кабинете мы кликаем на лого
        assertEquals(RestAssured.baseURI, driver.getCurrentUrl());
        Assert.assertTrue(MainPage.isCreateBurgerBoxDisplayed());//переход работает, если линка главная и есть надпись Создать буоргер
        accessToken = responseCreate.jsonPath().getString("accessToken");
    }

    @After
    public void tearDown() {
        userClient.deleteUser(accessToken);
        driver.quit();
    }

}


