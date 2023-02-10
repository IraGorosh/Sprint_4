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

@RunWith(Parameterized.class)
public class WebTestsFAQ {
    private static final String ANSWER1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String ANSWER2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String ANSWER3 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String ANSWER4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String ANSWER5 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String ANSWER6 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String ANSWER7 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String ANSWER8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    private final By questionBy;
    private final By answerBy;
    private final String answer;

    private WebDriver driver;

    public WebTestsFAQ(By questionBy, By answerBy, String answer) {
        this.questionBy = questionBy;
        this.answerBy = answerBy;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getAgreement() {
        return new Object[][]{
                {By.id("accordion__heading-0"), By.id("accordion__panel-0"), ANSWER1},
                {By.id("accordion__heading-1"), By.id("accordion__panel-1"), ANSWER2},
                {By.id("accordion__heading-2"), By.id("accordion__panel-2"), ANSWER3},
                {By.id("accordion__heading-3"), By.id("accordion__panel-3"), ANSWER4},
                {By.id("accordion__heading-4"), By.id("accordion__panel-4"), ANSWER5},
                {By.id("accordion__heading-5"), By.id("accordion__panel-5"), ANSWER6},
                {By.id("accordion__heading-6"), By.id("accordion__panel-6"), ANSWER7},
                {By.id("accordion__heading-7"), By.id("accordion__panel-7"), ANSWER8},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void checkFaqTexts_success() {
        String answerIsDisplayed = new MainPage(driver).open().clickAgreeToCookies().findAnswer(questionBy, answerBy);
        Assert.assertEquals("Ответ с таким текстом не найден", answer, answerIsDisplayed);
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
