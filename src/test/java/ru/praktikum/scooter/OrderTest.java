package ru.praktikum.scooter;

import ru.praktikum.scooter.pageobject.CreateOrderPage;
import ru.praktikum.scooter.pageobject.MainPage;
import ru.praktikum.scooter.pageobject.RentPage;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.containsString;


@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String orderButtonLocation;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String days;
    private final String color;
    private final String comments;


    public OrderTest(String orderButtonLocation, String name, String surname, String address, String metro, String phone, String date, String days, String color, String comments) {
        this.orderButtonLocation = orderButtonLocation;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.days = days;
        this.color = color;
        this.comments = comments;
    }


    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                //заказ через кнопку сверху и всеми заполненными полями
                {"Заказать сверху", "Джек", "Ричер", "Неизвестная 5 д1", "+79162328778", "Сокольники", "12.12.2025", "трое суток", "чёрный жемчуг", "не тормози"},
                //заказ через кнопку снизу и  заполненными только обязательными полями
                {"Заказать снизу", "Джон", "Уик", "Улица 6", "+79112328778", "Сокольники", "01.01.2026", "трое суток", "", ""},
        };
    }

    @Test
    public void createOrder() {
        MainPage mainPage = new MainPage(driver);
        CreateOrderPage createOrderPage = new CreateOrderPage(driver);
        RentPage rentPage = new RentPage(driver);
        mainPage.clickOrderButton(orderButtonLocation);
        createOrderPage.fillOrderData(name, surname, address, phone, metro);
        createOrderPage.clickNext();
        rentPage.fillRentalData(date, days, color, comments);
        rentPage.clickCreateOrder();
        rentPage.clickConfirmOrder(); //в хроме тест тут падает (но это ожидаемо судя по тексту задания), в firefox проходит
        MatcherAssert.assertThat(rentPage.checkModalWithCreatedOrderMessageIsPresented(), containsString("Заказ оформлен"));
    }
}
