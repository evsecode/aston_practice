package lesson_mobile;


import static org.assertj.core.api.Assertions.assertThat;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import lesson_mobile.pages.CalculatorPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Google Calculator — арифметические операции")
class CalculatorTest {

  private static AndroidDriver driver;
  private static CalculatorPage calculator;

  @BeforeAll
  static void setUp() throws MalformedURLException {
    File apk = new File(
        System.getProperty("user.dir") +
            "/src/main/resources/apps/calculator-7-8-271241277.apk"
    );

    // для запуска нужен appium, pixel 6 emulator через android studio api 33-ext5
    UiAutomator2Options options = new UiAutomator2Options()
        .setDeviceName("emulator-5554")            // имя эмулятора/устройства
        .setPlatformVersion("13.0")                // версия Android
        .setApp(apk.getAbsolutePath())
        .setAppPackage("com.google.android.calculator")
        .setAppActivity("com.android.calculator2.Calculator")
        .setNoReset(true)                          // не сбрасывать состояние
        .setNewCommandTimeout(Duration.ofSeconds(60));

    driver = new AndroidDriver(
        new URL("http://127.0.0.1:4723"), options
    );
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    calculator = new CalculatorPage(driver);
  }

  @AfterAll
  static void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @AfterEach
  void clearCalculator() {
    calculator.clearAll();
  }

  @Test
  @Order(1)
  @DisplayName("Сложение: 5 + 3 = 8")
  void testAddition() {
    calculator.pressNumber(5);
    calculator.pressPlus();
    calculator.pressNumber(3);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат сложения 5 + 3")
        .isEqualTo("8");
  }

  @Test
  @Order(2)
  @DisplayName("Сложение: 123 + 456 = 579")
  void testAdditionLargeNumbers() {
    calculator.pressNumber(123);
    calculator.pressPlus();
    calculator.pressNumber(456);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат сложения 123 + 456")
        .isEqualTo("579");
  }

  @Test
  @Order(3)
  @DisplayName("Вычитание: 9 - 4 = 5")
  void testSubtraction() {
    calculator.pressNumber(9);
    calculator.pressMinus();
    calculator.pressNumber(4);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат вычитания 9 - 4")
        .isEqualTo("5");
  }

  @Test
  @Order(4)
  @DisplayName("Вычитание: 100 - 200 = -100")
  void testSubtractionNegativeResult() {
    calculator.pressNumber(100);
    calculator.pressMinus();
    calculator.pressNumber(200);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат вычитания 100 - 200")
        .isEqualTo("−100");
  }

  @Test
  @Order(5)
  @DisplayName("Умножение: 6 × 7 = 42")
  void testMultiplication() {
    calculator.pressNumber(6);
    calculator.pressMultiply();
    calculator.pressNumber(7);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат умножения 6 × 7")
        .isEqualTo("42");
  }

  @Test
  @Order(6)
  @DisplayName("Умножение: 12 × 12 = 144")
  void testMultiplicationDoubleDigit() {
    calculator.pressNumber(12);
    calculator.pressMultiply();
    calculator.pressNumber(12);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат умножения 12 × 12")
        .isEqualTo("144");
  }

  @Test
  @Order(7)
  @DisplayName("Деление: 20 ÷ 4 = 5")
  void testDivision() {
    calculator.pressNumber(20);
    calculator.pressDivide();
    calculator.pressNumber(4);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат деления 20 ÷ 4")
        .isEqualTo("5");
  }

  @Test
  @Order(8)
  @DisplayName("Деление: 10 ÷ 3 = 3.3333333...")
  void testDivisionWithRemainder() {
    calculator.pressNumber(10);
    calculator.pressDivide();
    calculator.pressNumber(3);
    calculator.pressEquals();

    assertThat(calculator.getSuccessfulResult())
        .as("Результат деления 10 ÷ 3 содержит 3.333...")
        .startsWith("3.333");
  }

  @Test
  @Order(9)
  @DisplayName("Деление на ноль: 5 ÷ 0")
  void testDivisionByZero() {
    calculator.pressNumber(5);
    calculator.pressDivide();
    calculator.pressNumber(0);
    calculator.pressEquals();

    assertThat(calculator.getErrorResult())
        .as("Деление на ноль должно показать ошибку или бесконечность")
        .isEqualTo("Can't divide by 0");
  }
}