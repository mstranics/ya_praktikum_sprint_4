package ru.praktikum.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {
    //URL страницы
    public static final String URL = "https://qa-scooter.praktikum-services.ru";
    //шаблон для локатора вопроса
    private final String questionCommon = "accordion__heading-%s";
    //шаблон для локатора ответа
    private final String answerCommon = "accordion__panel-%s";
    //кнопка заказать в хедере
    private final By orderButtonHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //кнопка заказать не в хедере
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    // конструктор
    public MainPage(WebDriver driver) {
        super(driver);
    }

    //скроллим до нужного вопроса
    public void scrollTillQuestion(String questionNumber) {
        By question1 = By.id(String.format(questionCommon, questionNumber));
        WebElement element = driver.findElement(question1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //нажимаем на вопрос
    public void clickQuestion(String questionNumber) {
        By question = By.id(String.format(questionCommon, questionNumber));
        driver.findElement(question).click();
    }

    //возвращаем текст ответа
    public String getAnswerText(String questionNumber) {
        By answer = By.id(String.format(answerCommon, questionNumber));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(answer));

        return driver.findElement(answer).getText();
    }

    //жмакаем на заказать
    public void clickOrderButton(String orderButtonLocation) {
        if (orderButtonLocation.equals("Заказать сверху")) {
            driver.findElement(orderButtonHeader).click();
            new WebDriverWait(driver, Duration.ofSeconds(3));
        } else if (orderButtonLocation.equals("Заказать снизу")) {
            WebElement element = driver.findElement(orderButtonBottom);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(orderButtonBottom).click();
            new WebDriverWait(driver, Duration.ofSeconds(3));
        }
    }
}
