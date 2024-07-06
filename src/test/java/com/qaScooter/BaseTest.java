package com.qaScooter;

import com.qaScooter.pageobject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {

 protected WebDriver driver;

 @Before
 public  void setDriver(){
     driver= getDriver("chrome");
     // открываем главную страницу
     driver.get(MainPage.URL);
     // добавляем куки
     addCookie(new Cookie("Cartoshka","true"));
     addCookie(new Cookie("Cartoshka-legacy","true"));
     // рефрешим страницу
     driver.navigate().refresh();
 }

 @After
 //закрываем браузер
 public void tearDown(){
     driver.quit();
 }

 private void addCookie(Cookie cookie)
 {
     driver.manage().addCookie(cookie);
 }
    private WebDriver getDriver (String driverType){
     switch (driverType) {
         case "chrome":
             WebDriverManager.chromedriver().clearDriverCache().setup();
             return new ChromeDriver();
         case "firefox":
             WebDriverManager.firefoxdriver().clearDriverCache().setup();
             return new FirefoxDriver();
         default:
             throw new IllegalArgumentException("not supported");
     }
    }
}
