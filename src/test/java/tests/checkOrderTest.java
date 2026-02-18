package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.mainPage;
import pageObjects.orderPersonPage;
import pageObjects.orderRentPage;

import java.time.Duration;
import java.util.List;

@RunWith(Parameterized.class)

public class checkOrderTest {
    private WebDriver driver;
    private mainPage objMainPage;
    private orderPersonPage objPersonPage;
    private orderRentPage objRentPage;
    //параметры заказа
    private boolean useTopButton;
    private String name;
    private String surname;
    private String adress;
    private String metro;
    private String phoneNumber;
    private String orderDate;
    private String rentPeriod;
    private String color;
    private String comment;

    //Cookie
    public void cookieKiller(){
        List<WebElement> cookieButton = driver.findElements(By.id("rcc-confirm-button"));
        if (!cookieButton.isEmpty()) {
            cookieButton.get(0).click();
        } else {
            System.out.println("Баннер не найден");
        }
    }

    public checkOrderTest(boolean useTopButton,
                      String name,
                      String surname,
                      String adress,
                      String metro,
                      String phoneNumber,
                      String orderDate,
                      String rentPeriod,
                      String color,
                      String comment){
    this.useTopButton = useTopButton;
    this.name = name;
    this.surname = surname;
    this.adress = adress;
    this.metro = metro;
    this.phoneNumber = phoneNumber;
    this.orderDate = orderDate;
    this.rentPeriod = rentPeriod;
    this.color = color;
    this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] orderParameters(){
        return new Object[][]{
                {true,"Джордж","Оруэлл","ул. Станиславского, д. 21, стр. 3",
                        "Библиотека имени Ленина","89997776655",
                        "23.02.2026","сутки","grey","позвонить за час"},
                {false,"Ларри","Кинг","Рязанский пр-кт, д. 8а, кв. 41",
                        "Рязанский проспект","79995553311",
                        "08.03.2026","двое суток","black","стучать, в звонок не звонить"},
        };
    }
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage", "--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        cookieKiller();
        objMainPage = new mainPage(driver);
        objPersonPage = new orderPersonPage(driver);
        objRentPage = new orderRentPage(driver);
        objMainPage.waitHeader();
    }

    @Test
    public void orderTest(){
        objMainPage.selectTopOrderButton(useTopButton);
        //Ввод данных пользователя
        objPersonPage.waitPage();
        objPersonPage.setName(name);
        objPersonPage.setSurname(surname);
        objPersonPage.setAdress(adress);
        objPersonPage.setMetro(metro);
        objPersonPage.setPhone(phoneNumber);
        objPersonPage.clickNextButton();
        //Ввод пораметров заказа
        objRentPage.waitPage();
        objRentPage.setDate(orderDate);
        objRentPage.setPeriodRent(rentPeriod);
        objRentPage.selectColor(color);
        objRentPage.setComment(comment);
        objRentPage.clickOrder();
        //Подтверждение
        objRentPage.waitPageConfirm();
        objRentPage.clickConfirm();
        //Оформился
        objRentPage.checkOrder();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
