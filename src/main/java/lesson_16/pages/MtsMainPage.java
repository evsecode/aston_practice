package lesson_16.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class MtsMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MtsMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeCookiePopupIfExists() {
        try {
            WebElement cookiePopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            wait.until(ExpectedConditions.elementToBeClickable(cookiePopup)).click();
        } catch (TimeoutException | NoSuchElementException ignored) {
        }
    }

    public String getBlockTitle() {
        return driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText().replace("\n", " ").trim();
    }

    public void clickMoreInfo() {
        driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")).click();
    }

    public void selectPaymentOption(String option) {
        WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'select__header')]")));
        dropdownButton.click();
        WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[contains(@class, 'select__list')]//p[text()='" + option + "']")));
        optionElement.click();
    }

    public void enterFieldData(String fieldXPath, String value) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fieldXPath)));
        field.clear();
        field.sendKeys(value);
    }

    public void enterFieldsData(Map<String, String> fields) {
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            enterFieldData(entry.getKey(), entry.getValue());
        }
    }

    public void clickContinue(String formId) {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//form[@id='" + formId + "']//button[contains(text(), 'Продолжить')]")
        ));
        try {
            continueButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        }
    }

    public boolean isInsideIframe() {
        return driver.getWindowHandles().size() > 1 || driver.findElements(By.xpath("//iframe[contains(@class, 'bepaid-iframe')]")).isEmpty();
    }

    public void switchToPaymentIframe() {
        if (!isInsideIframe()) {
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@class, 'bepaid-iframe')]")));
            } catch (TimeoutException e) {
                throw new AssertionError("iframe оплаты не найден!", e);
            }
        }
    }

    public String getPaymentAmount() {
        switchToPaymentIframe();
        return driver.findElement(By.xpath("//div[@class='pay-description__cost']/span")).getText().trim();
    }

    public String getPaymentNumber() {
        return driver.findElement(By.xpath("//div[@class='pay-description__text']/span")).getText().replace("Оплата: Услуги связи Номер:", "").trim();
    }

    public boolean arePaymentIconsDisplayed() {
        List<WebElement> icons = driver.findElements(By.xpath("//div[contains(@class, 'cards-brands__container')]//img"));
        return !icons.isEmpty() && icons.stream().allMatch(WebElement::isDisplayed);
    }

    public String getPlaceholderText(String fieldXPath) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(fieldXPath)));
        return field.getAttribute("placeholder");
    }

    public String getValidationErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'input-wrapper')]//input[contains(@class, 'invalid')]/following-sibling::p")));
        return errorElement.getText();
    }

}
