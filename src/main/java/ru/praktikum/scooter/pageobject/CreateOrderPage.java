package ru.praktikum.scooter.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateOrderPage extends BasePage {
    //инпуты для перс данных заказа
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка далее
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    //шаблон для  станции метро из списка
    private final String metroTemplate = ".//div[@class='Order_Text__2broi'][text()='%s']";
    //шаблон для  времени аренды из списка
    private final String rentDaysTemplate = ".//div[@class='Dropdown-option'][text()='%s']";
    //шаблон для цвета
    private final String colorTemplate = ".//input[@type='checkbox'][text()='%s']";
    // кнопка заказать
    private final By createOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    public CreateOrderPage(WebDriver driver) {
        super(driver);
    }

    // заполняем  имя
    public void fillName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    // заполняем фамилию
    public void fillSurname(String surname) {
        driver.findElement(surnameInput).sendKeys(surname);
    }

    // заполняем адрес
    public void fillAddress(String address) {
        driver.findElement(addressInput).sendKeys(address);
    }

    // заполняем метро
    public void fillMetro(String metro) {
        driver.findElement(metroInput).click();
        By station = By.xpath(String.format(metroTemplate, metro));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(station));
        driver.findElement(station).click();
    }

    // заполняем телефон
    public void fillPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    //  жмакаем далее
    public void clickNext() {
        driver.findElement(nextButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    // шаг заполяющий все перс данные по порядку
    public void fillOrderData(String name, String surname, String address, String metro, String phone) {
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillMetro(metro);
        fillPhone(phone);

    }
}
