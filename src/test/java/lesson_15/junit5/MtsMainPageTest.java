package lesson_15.junit5;

import lesson_15.pages.MtsMainPage;
import lesson_15.steps.MtsMainPageSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MtsMainPageTest {
    private WebDriver driver;
    private MtsMainPageSteps mtsMainPageSteps;

    @BeforeAll
    void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mts.by");
        mtsMainPageSteps = new MtsMainPageSteps(new MtsMainPage(driver));
    }

    @Test
    void testBlockTitle() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.verifyBlockTitle("Онлайн пополнение без комиссии");
    }

    @Test
    void testPaymentLogos() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.verifyPaymentLogosAreDisplayed();
    }

    @Test
    void testMoreInfoLink() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.clickMoreInfo();
    }

    @Test
    void testPaymentFormSubmission() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.fillPaymentFormAndSubmit("297777777", "10", "test@example.com");
        mtsMainPageSteps.verifyPaymentWindowOpened();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
