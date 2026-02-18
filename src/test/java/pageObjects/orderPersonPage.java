package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class orderPersonPage {
    //Имя
    private By nameLocator = By.xpath(".//input[@placeholder='* Имя']");
    //Фамилия
    private By surnameLocator = By.xpath(".//input[@placeholder='* Фамилия']");
    //Адрес
    private By adressLocator = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Метро
    private By metroLocator = By.xpath(".//input[@placeholder='* Станция метро']");
    //Телефон
    private By phoneLocator = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private By nextButtonLocator = By.xpath(".//button[text()='Далее']");

    private WebDriver driver;
    public orderPersonPage(WebDriver driver){
        this.driver = driver;
    }

    //Ожидание загрузки страницы
    public void waitPage(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(nextButtonLocator));
    }
    //Ввод имени
    public void setName(String name){
        WebElement element = driver.findElement(nameLocator);
        element.clear();
        element.sendKeys(name);
    }
    //Ввод фамилии
    public void setSurname(String surname){
        WebElement element = driver.findElement(surnameLocator);
        element.clear();
        element.sendKeys(surname);
    }
    //Ввод адреса
    public void setAdress(String adress){
        WebElement element = driver.findElement(adressLocator);
        element.clear();
        element.sendKeys(adress);
    }
    //Ввод метро
    public void setMetro(String metro){
        WebElement element = driver.findElement(metroLocator);
        element.clear();
        element.sendKeys(metro);
        By listMetro = By.xpath("//div[contains(@class,'select-search__select')]//div[text()='" + metro + "']");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(listMetro))
                .click();
    }
    //Ввод телефона
    public void setPhone(String phone){
        WebElement element = driver.findElement(phoneLocator);
        element.clear();
        element.sendKeys(phone);
    }
    //Нажать "Далее"
    public void clickNextButton(){
        driver.findElement(nextButtonLocator).click();
    }

}
