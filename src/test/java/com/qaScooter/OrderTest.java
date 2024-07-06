package com.qaScooter;

import com.qaScooter.pageobject.CreateOrderPage;
import com.qaScooter.pageobject.MainPage;
import com.qaScooter.pageobject.RentPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private String orderButtonLocation;
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String date;
    private String days;
    private String color;
    private String comments;


    public OrderTest(String orderButtonLocation,String name, String surname, String address, String metro, String phone, String date, String days, String color, String comments) {
        this.orderButtonLocation=orderButtonLocation;
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
        return new Object[][] {
     //заказ через кнопку сверху и всеми заполненными полями
                { "Заказать сверху","Джек", "Ричер","Неизвестная 5 д1","+79162328778","Сокольники","12.12.2025","трое суток","чёрный жемчуг","не тормози"},
     //заказ через кнопку снизу и  заполненными только обязательными полями
                { "Заказать снизу","Джон", "Уик","Улица 6","+79112328778","Сокольники","01.01.2026","трое суток","",""},
        };
    }
    @Test
    public void createOrder() {
        MainPage mainPage = new MainPage(driver);
        CreateOrderPage createOrderPage = new CreateOrderPage(driver);
        RentPage rentPage = new RentPage(driver);
        mainPage.clickOrderButton(orderButtonLocation);
        createOrderPage.fillOrderData(name,surname,address,phone,metro);
        createOrderPage.clickNext();
        rentPage.fillRentalData(date,days,color,comments);
        rentPage.clickCreateOrder();
        rentPage.clickConfirmOrder(); //в хроме тест тут падает (но это ожидаемо судя по тексту задания), в firefox проходит
        MatcherAssert.assertThat(rentPage.checkModalWithCreatedOrderMessageIsPresented(),containsString("Заказ оформлен"));
    }
}
