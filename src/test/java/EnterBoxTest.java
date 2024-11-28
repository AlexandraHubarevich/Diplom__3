import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.ForgotPasswordPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;


import static webdrivercreator.WebDriverCreator.createWebDriver;


public class EnterBoxTest {
    private WebDriver driver;
    Faker faker = new Faker();
    private String email = faker.internet().emailAddress();
    private String name = faker.name().firstName();
    private String password = faker.internet().password(8, 10);
    private UserClient userClient = new UserClient();
    private String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        driver = createWebDriver();
        driver.manage().window().maximize();
    }

    //Успешным входом я считаю появление кнопки Оформить заказ
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void enterByEnterBoxTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.сreateUniqueUser(user);

        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPageAndClickEnter();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromLoginPage(email, password);

        Assert.assertTrue(MainPage.isOrderButtonDisplayed());
        accessToken = responseCreate.jsonPath().getString("accessToken");

    }


    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void enterByPrivateBoxTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.сreateUniqueUser(user);
        accessToken = responseCreate.jsonPath().getString("accessToken");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromLoginPage(user.getEmail(), user.getPassword());

        Assert.assertTrue(MainPage.isOrderButtonDisplayed());


    }

    @Test
    @DisplayName("Вход  через кнопку в форме регистрации")
    public void enterByEnterBoxFromRegPageTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.сreateUniqueUser(user);
        accessToken = responseCreate.jsonPath().getString("accessToken");

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickOnEnterBoxOnRegisterPage();


        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromLoginPage(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isOrderButtonDisplayed());



    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void enterByRecoverPasswordTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.сreateUniqueUser(user);
        accessToken = responseCreate.jsonPath().getString("accessToken");

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickEnterFromForgotPasswordPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromLoginPage(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);

        Assert.assertTrue(mainPage.isOrderButtonDisplayed());


    }

    @After
    public void tearDown() {
        userClient.deleteUser(accessToken);
        driver.quit();

    }

}