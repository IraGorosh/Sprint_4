package ru.praktikum.yandex.model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private static final By ORDER_PAGE = By.className("Order_Form__17u6u");
    private static final By FIRST_NAME_INPUT = By.xpath(".//input[@placeholder='* Имя']");
    private static final By SURNAME_INPUT = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By ADDRESS_INPUT = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By METRO_INPUT = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By METRO1_INPUT = By.xpath(".//button[@value='1']");
    private static final By PHONE_INPUT = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private static final By DATE_INPUT = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By RENTAL_PERIOD_INPUT = By.xpath(".//*[text()='* Срок аренды']");
    private static final By DAYS_INPUT = By.xpath(".//*[text()='пятеро суток']");
    private static final By ORDER_BUTTON = By.xpath(".//*[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private static final By MODAL_WINDOW_TEXT = By.className("Order_ModalHeader__3FDaJ");
    private static final By AGREEMENT = By.xpath(".//*[text()='Да']");
    private static final By POPUP = By.partialLinkText("Заказ оформлен");
    private static final By CHOOSE_DATE_INPUT = By.className("react-datepicker__day--today");

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public OrderPage orderPageIsOpen() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_PAGE));
        return this;
    }

    public OrderPage fillOutPersonalDataToOrderForm(String name, String surname, String address, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(FIRST_NAME_INPUT).sendKeys(name);
        driver.findElement(SURNAME_INPUT).sendKeys(surname);
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
        driver.findElement(METRO_INPUT).click();
        WebElement element = driver.findElement(METRO1_INPUT);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(METRO1_INPUT).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT));
        driver.findElement(PHONE_INPUT).sendKeys(phone);
        driver.findElement(NEXT_BUTTON).click();
        return this;
    }

    public OrderPage fillOutRentalDataToOrderForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(DATE_INPUT));
        driver.findElement(DATE_INPUT).click();
        driver.findElement(CHOOSE_DATE_INPUT).click();
        driver.findElement(RENTAL_PERIOD_INPUT).click();
        driver.findElement(DAYS_INPUT).click();
        driver.findElement(ORDER_BUTTON).click();
        return this;
    }

    public String setModalAnswer() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_WINDOW_TEXT));
        driver.findElement(AGREEMENT).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP));
        return driver.findElement(POPUP).getText();
    }
}
