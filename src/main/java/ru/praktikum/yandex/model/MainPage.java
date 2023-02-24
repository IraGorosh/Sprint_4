package ru.praktikum.yandex.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By FAQ_OBJECT = By.xpath("//*[@id='root']//div[@class='accordion']");
    private static final By COOKIE_BUTTON = By.className("App_CookieButton__3cvqF");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public MainPage clickAgreeToCookies() {
        driver.findElement(COOKIE_BUTTON).click();
        return this;
    }

    public MainPage clickOrderButton(By orderButton) {
        driver.findElement(orderButton).click();
        return this;
    }

    public String findAnswer(By questionBy, By answerBy) {
        WebElement element = driver.findElement(FAQ_OBJECT);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(questionBy).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerBy));
        return driver.findElement(answerBy).getText();
    }
}
