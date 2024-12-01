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
import pageobject.RegistrationPage;


import static webdrivercreator.WebDriverCreator.createWebDriver;


public class SuccessRegistrationTest {
    private WebDriver driver;
    Faker faker = new Faker();

    private String email = "testemail12@gmail.com";
    private String name = "newUser1";
    private String password = "password123";
    private String passwordIncorrect = faker.internet().password(0, 5);
    private UserClient userClient = new UserClient();
    private String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        driver = createWebDriver();
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверить успешную регистрацию")
    public void successRegistrationTest() {

        RegistrationPage openRegPage = new RegistrationPage(driver);

        openRegPage.openRegistrationPageAndRegister(name, email, password);
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isEnterBoxDisplayed());//Успешная регистрация -это появление кнопки Вход
        //Здесь я логинюсь, чтобы получить акксес токен для удаления созданного юзера
        Response loginResponse = userClient.loginUser(new User(name, email, password));
        accessToken = loginResponse.jsonPath().getString("accessToken");
        userClient.deleteUser(accessToken);
    }


    @Test
    @DisplayName("Проверить ошибку для некорректного пароля")
    public void unsuccessRegistrationTest() {

        RegistrationPage openRegPage = new RegistrationPage(driver);
        openRegPage.openRegistrationPageAndRegister(name, email, passwordIncorrect);
        Assert.assertTrue(openRegPage.isIncorrectPasswordNotificationDisplayed());//должно появиться уведомление о некорректном пароле
        //здесь удаления нет, тк регистрация не успешна
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
