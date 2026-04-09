package lesson_mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CalculatorPage {

  private static final String DIGIT_ID = "com.google.android.calculator:id/digit_%s";
  private static final String BTN_PLUS = "com.google.android.calculator:id/op_add";
  private static final String BTN_MINUS = "com.google.android.calculator:id/op_sub";
  private static final String BTN_MULTIPLY = "com.google.android.calculator:id/op_mul";
  private static final String BTN_DIVIDE = "com.google.android.calculator:id/op_div";
  private static final String BTN_EQUALS = "com.google.android.calculator:id/eq";
  private static final String BTN_CLEAR = "com.google.android.calculator:id/clr";
  private static final String BTN_DEL = "com.google.android.calculator:id/del";
  private static final String RESULT_FIELD = "com.google.android.calculator:id/result_final";
  private static final String RESULT_PREVIEW = "com.google.android.calculator:id/result_preview";
  private static final String FORMULA_FIELD = "com.google.android.calculator:id/formula";
  private final AndroidDriver driver;

  public CalculatorPage(AndroidDriver driver) {
    this.driver = driver;
  }

  public void pressDigit(int digit) {
    String id = String.format(DIGIT_ID, digit);
    driver.findElement(AppiumBy.id(id)).click();
  }

  // Ввод многозначного числа
  public void pressNumber(int number) {
    String numStr = String.valueOf(Math.abs(number));
    for (char c : numStr.toCharArray()) {
      pressDigit(Character.getNumericValue(c));
    }
  }

  public void pressPlus() {
    driver.findElement(AppiumBy.id(BTN_PLUS)).click();
  }

  public void pressMinus() {
    driver.findElement(AppiumBy.id(BTN_MINUS)).click();
  }

  public void pressMultiply() {
    driver.findElement(AppiumBy.id(BTN_MULTIPLY)).click();
  }

  public void pressDivide() {
    driver.findElement(AppiumBy.id(BTN_DIVIDE)).click();
  }

  public void pressEquals() {
    driver.findElement(AppiumBy.id(BTN_EQUALS)).click();
  }

  public String getSuccessfulResult() {
    // Пробуем result_final, затем formula (отображается до нажатия =)
    return getBaseResult(RESULT_FIELD);
  }

  public String getErrorResult() {
    // в result_preview отображается текст ошибки
    return getBaseResult(RESULT_PREVIEW);
  }

  private String getBaseResult(String resultField) {
    try {
      WebElement result = driver.findElement(AppiumBy.id(resultField));
      String text = result.getText().trim();
      if (!text.isEmpty()) {
        return text;
      }
    } catch (Exception ignored) {
    }
    return driver.findElement(AppiumBy.id(FORMULA_FIELD)).getText().trim();
  }

  public void clearAll() {
    try {
      driver.findElement(AppiumBy.id(BTN_CLEAR)).click();
    } catch (Exception ignored) {
      WebElement del = driver.findElement(AppiumBy.id(BTN_DEL));
      new Actions(driver).clickAndHold(del).pause(1000).release().perform();
    }
  }
}