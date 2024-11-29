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


import static webdrivercreator.WebDriverCreator.createWebDriver;


public class GetOutTest {
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


    @Test
    @DisplayName("Проверь выход по кнопке «Выйти» в личном кабинете")
    public void enterByPrivateBoxTest() {
        User user = new User(name, email, password);
        Response responseCreate = userClient.createUniqueUser(user);
        accessToken = responseCreate.jsonPath().getString("accessToken");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginFromLoginPage(user.getEmail(), user.getPassword());
        mainPage.clickPrivateBox();
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.clickGetOut();

        Assert.assertTrue(LoginPage.isEnterButtonDisplayed());//должна появиться кнопка Вход
        accessToken = responseCreate.jsonPath().getString("accessToken");
    }

    @After
    public void tearDown() {
        userClient.deleteUser(accessToken);
        driver.quit();
    }

}