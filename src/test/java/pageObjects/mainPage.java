package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.config.Constants;

import java.time.Duration;
import java.util.List;

public class mainPage {
    private WebDriver driver;
    //Заголовок страницы
    private By header = By.className("Header_Header__214zg");
    //Кнопка "Заказать" верхняя
    private By topOrderButton = By.cssSelector("div.Header_Nav__AGCXC button.Button_Button__ra12g");
    //Кнопка "Заказать" нижняя
    private By lowOrderButton = By.xpath("//div[contains(@class,'Home_FinishButton')]//button[text()='Заказать']");
    //FAQ вопросы
    private By questionsHeading = By.className("accordion__button");
    //FAQ ответы
    private By answerPanel = By.className("accordion__panel");
    //Локатор первого вопроса
    private By firstAnswer = By.id("accordion__heading-0");

    public mainPage(WebDriver driver){
        this.driver = driver;
    }

    //Ожидание загрузки Header
    public void waitHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_PAGE_LOAD))
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }
    //Перемотка до первого вопроса
    public void scrollIntoFirstAnswer() {
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //Клик по верхней кнопке заказа
    public void clickTopOrderButton(){
        driver.findElement(topOrderButton).click();
    }
    //Клик по нижней кнопке заказа
    public void clickLowOrderButton(){
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_ELEMENT_VISIBLE))
                .until(ExpectedConditions.elementToBeClickable(lowOrderButton));
        element.click();
    }
    //Выбор кнопки заказа
    public void selectTopOrderButton(boolean useTopButton){
        if(useTopButton){
            clickTopOrderButton();
        } else {
            clickLowOrderButton();
        }
    }
    //Список всех вопросов FAQ
    public List<WebElement> getQuestions(){
        return driver.findElements(questionsHeading);
    }
    //Список всех ответов FAQ
    public List<WebElement> getAnswers(){
        return driver.findElements(answerPanel);
    }
    //Нажатие на вопрос
    public void clickQuestion(int index) {
        getQuestions().get(index).click();
    }
    //Текст ответа
    public String getAnswerText(int index) {
        List<WebElement> answers = getAnswers();
        new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT_ELEMENT_VISIBLE))
                .until(ExpectedConditions.visibilityOf(answers.get(index)));
        return answers.get(index).getText();
    }
}
