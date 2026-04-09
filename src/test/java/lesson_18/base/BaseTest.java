package lesson_18.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson_18.pages.MtsMainPage;
import lesson_18.steps.MtsMainPageSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected WebDriver driver;
    protected MtsMainPageSteps mtsMainPageSteps;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        logger.info("Запуск браузера...");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mts.by");
        mtsMainPageSteps = new MtsMainPageSteps(new MtsMainPage(driver));
    }

    @AfterEach
    void tearDown() {
        logger.info("Закрытие браузера...");
        if (driver != null) {
            driver.quit();
        }
    }
}
