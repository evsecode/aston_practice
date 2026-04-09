package lesson_mobile;

import static org.assertj.core.api.Assertions.assertThat;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import lesson_mobile.pages.CalculatorPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Epic("Мобильное приложение Google Calculator")
@Feature("Арифметические операции")
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

    UiAutomator2Options options = new UiAutomator2Options()
        .setDeviceName("emulator-5554")
        .setPlatformVersion("13.0")
        .setApp(apk.getAbsolutePath())
        .setAppPackage("com.google.android.calculator")
        .setAppActivity("com.android.calculator2.Calculator")
        .setNoReset(true)
        .setNewCommandTimeout(Duration.ofSeconds(60));

    driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    calculator = new CalculatorPage(driver);
  }

  @AfterAll
  static void tearDown() {
    if (driver != null) driver.quit();
  }

  @BeforeEach
  void clearCalculator() {
    calculator.clearAll();
  }

  @Test
  @Order(1)
  @Story("Сложение")
  @Severity(SeverityLevel.BLOCKER)
  @Description("Проверка сложения двух однозначных чисел: 5 + 3 = 8")
  @DisplayName("Сложение: 5 + 3 = 8")
  void testAddition() {
    performAddition(5, 3);
    assertResult("8", "Результат сложения 5 + 3");
  }

  @Test
  @Order(2)
  @Story("Сложение")
  @Severity(SeverityLevel.NORMAL)
  @Description("Проверка сложения трёхзначных чисел: 123 + 456 = 579")
  @DisplayName("Сложение: 123 + 456 = 579")
  void testAdditionLargeNumbers() {
    performAddition(123, 456);
    assertResult("579", "Результат сложения 123 + 456");
  }

  @Test
  @Order(3)
  @Story("Вычитание")
  @Severity(SeverityLevel.BLOCKER)
  @Description("Проверка вычитания: 9 - 4 = 5")
  @DisplayName("Вычитание: 9 - 4 = 5")
  void testSubtraction() {
    performSubtraction(9, 4);
    assertResult("5", "Результат вычитания 9 - 4");
  }

  @Test
  @Order(4)
  @Story("Вычитание")
  @Severity(SeverityLevel.NORMAL)
  @Description("Проверка вычитания с отрицательным результатом: 100 - 200 = -100")
  @DisplayName("Вычитание: 100 - 200 = -100")
  void testSubtractionNegativeResult() {
    performSubtraction(100, 200);
    assertResult("−100", "Результат вычитания 100 - 200");
  }

  @Test
  @Order(5)
  @Story("Умножение")
  @Severity(SeverityLevel.BLOCKER)
  @Description("Проверка умножения: 6 × 7 = 42")
  @DisplayName("Умножение: 6 × 7 = 42")
  void testMultiplication() {
    performMultiplication(6, 7);
    assertResult("42", "Результат умножения 6 × 7");
  }

  @Test
  @Order(6)
  @Story("Умножение")
  @Severity(SeverityLevel.NORMAL)
  @Description("Проверка умножения двузначных чисел: 12 × 12 = 144")
  @DisplayName("Умножение: 12 × 12 = 144")
  void testMultiplicationDoubleDigit() {
    performMultiplication(12, 12);
    assertResult("144", "Результат умножения 12 × 12");
  }

  @Test
  @Order(7)
  @Story("Деление")
  @Severity(SeverityLevel.BLOCKER)
  @Description("Проверка деления: 20 ÷ 4 = 5")
  @DisplayName("Деление: 20 ÷ 4 = 5")
  void testDivision() {
    performDivision(20, 4);
    assertResult("5", "Результат деления 20 ÷ 4");
  }

  @Test
  @Order(8)
  @Story("Деление")
  @Severity(SeverityLevel.NORMAL)
  @Description("Проверка деления с остатком: 10 ÷ 3 = 3.333...")
  @DisplayName("Деление: 10 ÷ 3 = 3.3333333...")
  void testDivisionWithRemainder() {
    performDivision(10, 3);
    assertThat(calculator.getSuccessfulResult())
        .as("Результат деления 10 ÷ 3 содержит 3.333...")
        .startsWith("3.333");
  }

  @Test
  @Order(9)
  @Story("Деление")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Проверка деления на ноль: должна отображаться ошибка")
  @DisplayName("Деление на ноль: 5 ÷ 0")
  void testDivisionByZero() {
    performDivision(5, 0);
    assertThat(calculator.getErrorResult())
        .as("Деление на ноль должно показать ошибку или бесконечность")
        .isIn("Can't divide by 0");
  }

  @Step("Вводим {a} + {b} и нажимаем =")
  private void performAddition(int a, int b) {
    calculator.pressNumber(a);
    calculator.pressPlus();
    calculator.pressNumber(b);
    calculator.pressEquals();
  }

  @Step("Вводим {a} - {b} и нажимаем =")
  private void performSubtraction(int a, int b) {
    calculator.pressNumber(a);
    calculator.pressMinus();
    calculator.pressNumber(b);
    calculator.pressEquals();
  }

  @Step("Вводим {a} × {b} и нажимаем =")
  private void performMultiplication(int a, int b) {
    calculator.pressNumber(a);
    calculator.pressMultiply();
    calculator.pressNumber(b);
    calculator.pressEquals();
  }

  @Step("Вводим {a} ÷ {b} и нажимаем =")
  private void performDivision(int a, int b) {
    calculator.pressNumber(a);
    calculator.pressDivide();
    calculator.pressNumber(b);
    calculator.pressEquals();
  }

  @Step("Проверяем результат: ожидаем '{expected}'")
  private void assertResult(String expected, String description) {
    assertThat(calculator.getSuccessfulResult())
        .as(description)
        .isEqualTo(expected);
  }
}