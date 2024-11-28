import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;


import static pageobject.MainPage.*;
import static webdrivercreator.WebDriverCreator.createWebDriver;
import static org.junit.Assert.assertEquals;


public class ConstructorTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        driver = createWebDriver();
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Раздел «Конструктор»: Проверь, что работают переходы к разделам: «Начинки»")
    public void checkInnerTest() {
        MainPage openMainPage = new MainPage(driver);

        openMainPage.openInner();//открываю главную страницу, кликаю на начинки, скроллю до начинок, получаю текст начинок, сравниваю
        assertEquals(expectedInner, openMainPage.getTextFromInner());
    }


    @Test
    @DisplayName("Раздел «Конструктор»: Проверь, что работают переходы к разделам: «Соусы»")
    public void checkSauceTest() {

        MainPage openMainPage = new MainPage(driver);

        openMainPage.openSauce();//открываю главную страницу, кликаю на соус, скроллю до соуса, получаю текст соусов, сравниваю
        assertEquals(expectedSauces, openMainPage.getTextFromSauce());

    }

    @Test
    @DisplayName("Раздел «Конструктор»: Проверь, что работают переходы к разделам: «Булки»")
    public void checkBunTest() {

        MainPage openMainPage = new MainPage(driver);

        openMainPage.openBuns();//открываю главную страницу, кликаю на булки, скроллю до них, получаю текст и сравниваю
        assertEquals(expectedBuns, openMainPage.getTextFromBuns());

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}