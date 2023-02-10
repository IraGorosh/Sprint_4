import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.yandex.model.MainPage;
import ru.praktikum.yandex.model.OrderPage;
import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class WebTestsOrder {

    private final String userName;
    private final String userSurname;
    private final String userAddress;
    private final String userPhone;
    private WebDriver driver;

    public WebTestsOrder(String userName, String userSurname, String userAddress, String userPhone) {
            this.userName = userName;
            this.userSurname = userSurname;
            this.userAddress = userAddress;
            this.userPhone = userPhone;
    }
    @Parameterized.Parameters
    public static Object[][] getAgreement() {
        return new Object[][] {
                {"Ли", "Джон", "г Казань, ул Краснопролетарская, д 4", "+70072484078" },
                {"Аннамария", "Иванова", "г Москва, ул Килиманджарокофейная, д.234-1,345", "80072486713" },
        };
    }
    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void createOrder_PopupIsDisplayed() {
        new MainPage(driver).open().clickOrderButton();
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
