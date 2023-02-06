import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
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

    private final By agreement;
    private final boolean expected;
    private WebDriver driver;

    public WebTestsOrder(By agreement, boolean expected) {
        this.agreement = agreement;
        this.expected = expected;
    }
    @Parameterized.Parameters
    public static Object[][] getAgreement() {
        return new Object[][] {
                { By.xpath(".//*[text()='Да']"), true},
                { By.xpath(".//*[text()='Нет']"), false},
        };
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/irinagorosh/chromedriver_mac64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void createOrder_PopupIsDisplayed() {
        new MainPage(driver).open().clickOrderButton();
        boolean isDisplayed = new OrderPage(driver).orderPageIsOpen().fillOutOrderForm().agreementModalWindow(agreement);
        Assert.assertEquals("Фактический текст отличается от ожидаемого", expected, isDisplayed);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
