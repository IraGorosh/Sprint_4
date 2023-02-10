import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.yandex.model.MainPage;
import ru.praktikum.yandex.model.OrderPage;

import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class WebTestsOrder {
    private static final By ORDER_BUTTON = By.cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g");
    private static final By ORDER_BUTTON_MIDDLE = By.cssSelector("div.Home_FinishButton__1_cWm > button.Button_Middle__1CSJM");

    private final By orderButton;
    private final String userName;
    private final String userSurname;
    private final String userAddress;
    private final String userPhone;

    private WebDriver driver;

    public WebTestsOrder(By orderButton, String userName, String userSurname, String userAddress, String userPhone) {
        this.orderButton = orderButton;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
    }

    @Parameterized.Parameters
    public static Object[][] getAgreement() {
        return new Object[][]{
                {ORDER_BUTTON, "Ли", "Джон", "г Казань, ул Краснопролетарская, д 4", "+70072484078"},
                {ORDER_BUTTON_MIDDLE, "Аннамария", "Иванова", "г Москва, ул Килиманджарокофейная, д.234-1,345", "80072486713"},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void createOrder_PopupIsDisplayed() {
        new MainPage(driver).open().clickAgreeToCookies().clickOrderButton(orderButton);
        String isDisplayed = new OrderPage(driver)
                .orderPageIsOpen()
                .fillOutPersonalDataToOrderForm(userName, userSurname, userAddress, userPhone)
                .fillOutRentalDataToOrderForm()
                .setModalAnswer();
        MatcherAssert.assertThat("Фактический текст отличается от ожидаемого", isDisplayed, startsWith("Заказ оформлен"));
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
