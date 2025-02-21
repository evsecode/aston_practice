package lesson_15.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsMainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public MtsMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeCookiePopupIfExists() {
        try {
            WebElement cookiePopup = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cookie-agree")));
            if (cookiePopup.isDisplayed()) {
                try {
                    cookiePopup.click();
                } catch (ElementClickInterceptedException e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookiePopup);
                }
            }
        } catch (TimeoutException ignored) {
        }
    }

    public String getBlockTitle() {
        return driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText().replace("\n", " ").trim();
    }

    public boolean arePaymentLogosDisplayed() {
        List<WebElement> logos = driver.findElements(By.xpath("//div[@class='pay__partners']//img"));
        return !logos.isEmpty() && logos.stream().allMatch(WebElement::isDisplayed);
    }

    public void clickMoreInfo() {
        driver.findElement(By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")).click();
    }

    public void enterPhoneNumber(String phoneNumber) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='connection-phone']")));
        phoneInput.clear();
        phoneInput.sendKeys(phoneNumber);
    }

    public void enterAmount(String amount) {
        WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='connection-sum']")));
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='connection-email']")));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void clickContinue() {
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Продолжить')]")));
        continueButton.click();
    }

    public void switchToPaymentIframe() {
        try {
            WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@class, 'bepaid-iframe')]")));
            driver.switchTo().frame(iframeElement);
        } catch (TimeoutException e) {
            throw new AssertionError("iframe оплаты не найден!", e);
        }
    }

    public boolean isPaymentWindowDisplayed() {
        switchToPaymentIframe();
        try {
            WebElement paymentContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class, 'payment-page')]")));            return paymentContent.isDisplayed();
        } catch (TimeoutException e) {
            throw new AssertionError("Окно оплаты не загрузилось!", e);
        }
    }
}
