package lesson_16.junit5;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson_16.pages.MtsMainPage;
import lesson_16.steps.MtsMainPageSteps;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Map;

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
    void testPlaceholders() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.selectPaymentOption("Домашний интернет");
        mtsMainPageSteps.verifyPlaceholderText("//input[@id='internet-phone']", "Номер абонента");
        mtsMainPageSteps.verifyPlaceholderText("//input[@id='internet-sum']", "Сумма");

        mtsMainPageSteps.selectPaymentOption("Рассрочка");
        mtsMainPageSteps.verifyPlaceholderText("//input[@id='score-instalment']", "Номер счета на 44");

        mtsMainPageSteps.selectPaymentOption("Задолженность");
        mtsMainPageSteps.verifyPlaceholderText("//input[@id='score-arrears']", "Номер счета на 2073");

        mtsMainPageSteps.selectPaymentOption("Услуги связи");
        mtsMainPageSteps.verifyPlaceholderText("//input[@id='connection-phone']", "Номер телефона");
        mtsMainPageSteps.verifyPlaceholderText("//input[@id='connection-email']", "E-mail для отправки чека");
    }

    @Test
    void testMoreInfoLink() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.clickMoreInfo();
    }

    @Test
    void testValidationErrors() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.selectPaymentOption("Домашний интернет");
        mtsMainPageSteps.fillPaymentForm(Map.of(
                "//input[@id='internet-phone']", "299873423",
                "//input[@id='internet-sum']", "10"
        ));
        mtsMainPageSteps.submitPaymentForm("pay-internet");
        mtsMainPageSteps.verifyValidationError("Необходимо указать номер в формате +375 00 ХХХ-ХХ-ХХ");

        mtsMainPageSteps.selectPaymentOption("Рассрочка");
        mtsMainPageSteps.fillPaymentForm(Map.of(
                "//input[@id='score-instalment']", "4412345",
                "//input[@id='instalment-sum']", "10"
        ));
        mtsMainPageSteps.submitPaymentForm("pay-instalment");
        mtsMainPageSteps.verifyValidationError("Введите корректный номер лицевого счета");

        mtsMainPageSteps.selectPaymentOption("Задолженность");
        mtsMainPageSteps.fillPaymentForm(Map.of(
                "//input[@id='score-arrears']", "4412345",
                "//input[@id='arrears-sum']", "10"
        ));
        mtsMainPageSteps.submitPaymentForm("pay-arrears");
        mtsMainPageSteps.verifyValidationError("Введите корректный номер лицевого счета");

        mtsMainPageSteps.selectPaymentOption("Услуги связи");
        mtsMainPageSteps.fillPaymentForm(Map.of(
                "//input[@id='connection-phone']", "299873423", //this phone number is not exists (22.02.2025).
                //if it's exists now - try to find another non-existing number
                "//input[@id='connection-sum']", "10"
        ));
        mtsMainPageSteps.submitPaymentForm("pay-connection");
        mtsMainPageSteps.verifyValidationError("Неверно указан номер");
    }

    @Test
    void testPaymentSummary() {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.selectPaymentOption("Услуги связи");

        Map<String, String> fields = Map.of(
                "//input[@id='connection-phone']", "297777777",
                "//input[@id='connection-sum']", "10",
                "//input[@id='connection-email']", "test@example.com"
        );

        mtsMainPageSteps.fillPaymentForm(fields);

        mtsMainPageSteps.submitPaymentForm("pay-connection");
        mtsMainPageSteps.verifyPaymentAmount("10.00 BYN");
        mtsMainPageSteps.verifyPaymentNumber("375297777777");
        mtsMainPageSteps.verifyPaymentIconsDisplayed();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
