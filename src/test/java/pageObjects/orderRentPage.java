package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class orderRentPage {
    //Дата аренды(15.02.2026)
    private By dateRent = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Срок аренды
    private By periodRent = By.xpath(".//div[contains(@class,'Dropdown-control')]");
    //Список появление
    private By menuRentPeriod = By.xpath(".//div[contains(@class,'Dropdown-menu')]");
    //Список элементы (сутки, двое суток, трое суток, четверо суток, пятеро суток, шестеро суток, семеро суток)
    private By listRentPeriod = By.xpath(".//div[contains(@class,'Dropdown-option')]");
    //Цвет самоката
    private By scooterBlack = By.id("black");
    private By scooterGrey = By.id("grey");
    //Комментарий курьеру
    private By commentCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private By buttonOrder = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //Попап кнопка подтверждения заказа
    private By buttonConfirm = By.xpath(".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");
    //Попап "Заказ оформлен"
    private By orderPlaced = By.xpath(".//div[contains(@class,'Order_Modal__YZ-d3')]");

    private WebDriver driver;
    public orderRentPage(WebDriver driver){
        this.driver = driver;
    }
    //Ожидание загрузки страницы
    public void waitPage(){
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(dateRent));
    }
    //Ввод даты
    public void setDate(String date){
        driver.findElement(dateRent).sendKeys(date);
        driver.findElement(By.xpath("//div[contains(@class,'Order_Header__BZXOb')]")).click();
    }
    //Ввод срока аренды
    public void setPeriodRent(String period){
        driver.findElement(periodRent).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(menuRentPeriod));

        driver.findElements(listRentPeriod).stream()
                .filter(option -> option.getText().equals(period))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Опция '" + period + "' не найдена"))
                .click();
    }
    //Выбор цвета
    public void selectColorBlack(){
        driver.findElement(scooterBlack).click();
    }
    public void selectColorGrey(){
        driver.findElement(scooterGrey).click();
    }
    public void selectColor(String color){
        if ("black".equals(color)) {
            selectColorBlack();
        } else if ("grey".equals(color)) {
            selectColorGrey();
        } else {
            System.out.println("Задан неверный цвет самоката");
        }
    }
    //Оставить комментарий
    public void setComment(String comment){
        driver.findElement(commentCourier).sendKeys(comment);
    }
    //Нажать заказать
    public void clickOrder(){
        driver.findElement(buttonOrder).click();
    }
    //Ожидание подтверждения
    public void waitPageConfirm(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(buttonConfirm));
    }
    //Подтвердить заказ
    public void clickConfirm(){
        driver.findElement(buttonConfirm).click();
    }
    //Оформился
    public void checkOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPlaced));
        System.out.println("Заказ оформлен");
    }
}
