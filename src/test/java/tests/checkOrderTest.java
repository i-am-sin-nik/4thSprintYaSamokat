package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.mainPage;
import pageObjects.orderPersonPage;
import pageObjects.orderRentPage;
import tests.config.BaseTest;
import tests.config.Constants;

@RunWith(Parameterized.class)

public class checkOrderTest extends BaseTest {

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
    public void initPages() {
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
        objRentPage.checkOrder(Constants.EXPECTED_CONFIRM_MESSAGE);
    }

}
