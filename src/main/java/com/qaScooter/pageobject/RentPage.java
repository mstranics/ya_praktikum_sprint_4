package com.qaScooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage extends BasePage{

    public RentPage(WebDriver driver) {
        super(driver);
    }
    //поля с данными об аренде
    private final By dateInput =By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By commentsInput =By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By rentDaysInput = By.className("Dropdown-arrow");

    //шаблон для  времени аренды из списка
    private  final  String rentDaysTemplate =".//div[@class='Dropdown-option'][text()='%s']";
    //шаблон  цвета
    private  final  String colorTemplate =".//label[text()='%s']";
    // кнопка заказать
    private  final By createOrderButton= By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
// кнопка подверждения заказа
    private final By confirmOrderButton = By.xpath(".//button[text()='Да']");
//хедере модалки об успешном заказе
    private final By successOrderHeader = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");
    // заполняем когда привезти самокат
    public void fillDateInput(String date){
        driver.findElement(dateInput).sendKeys(date);
    }
    //заполняем срок аренды
    public void fillRentDays(String days){
        driver.findElement(rentDaysInput).click();
        By rentDays=By.xpath(String.format(rentDaysTemplate,days));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(rentDays));
        driver.findElement(rentDays).click();
    }
    // выбираем цвет самоката
    public void fillColor (String color) {
        if (!color.isEmpty())
        {driver.findElement(By.xpath(String.format(colorTemplate,color))).click();}
    }
    // заполняем  комментарии
    public void fillComments (String  comments){
        driver.findElement(commentsInput).sendKeys(comments);
    }
    // жмакаем заказать
    public void clickCreateOrder (){
        driver.findElement(createOrderButton).click();
}
    // подтверждаем
    public void clickConfirmOrder () {
        driver.findElement(confirmOrderButton).click();
    }

// шаг заполняющий даннные аренды
    public void fillRentalData (String date, String days, String color, String comments) {
        fillDateInput(date);
        fillRentDays(days);
        fillColor(color);
        fillComments(comments);
    }
    // проверяем наличие модалки с сообщением об удачном заказе
    public String checkModalWithCreatedOrderMessageIsPresented ()
    {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(successOrderHeader));
        return driver.findElement(successOrderHeader).getText();
    }
}
