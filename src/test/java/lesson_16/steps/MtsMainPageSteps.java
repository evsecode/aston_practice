package lesson_16.steps;

import lesson_16.pages.MtsMainPage;
import org.assertj.core.api.Assertions;

import java.util.Map;

public class MtsMainPageSteps {
    private final MtsMainPage mtsMainPage;

    public MtsMainPageSteps(MtsMainPage mtsMainPage) {
        this.mtsMainPage = mtsMainPage;
    }

    public void closeCookiePopupIfExists() {
        mtsMainPage.closeCookiePopupIfExists();
    }

    public void verifyBlockTitle(String expectedTitle) {
        Assertions.assertThat(mtsMainPage.getBlockTitle()).isEqualTo(expectedTitle);
    }

    public void clickMoreInfo() {
        mtsMainPage.clickMoreInfo();
    }

    public void selectPaymentOption(String option) {
        mtsMainPage.selectPaymentOption(option);
    }

    public void fillPaymentForm(Map<String, String> fields) {
        mtsMainPage.enterFieldsData(fields);
    }

    // no usages for now. But useful if you need to fill only one field
    public void fillPaymentForm(String fieldXPath, String value) {
        mtsMainPage.enterFieldData(fieldXPath, value);
    }

    public void submitPaymentForm(String formId) {
        mtsMainPage.clickContinue(formId);
    }

    public void verifyPaymentAmount(String expectedAmount) {
        Assertions.assertThat(mtsMainPage.getPaymentAmount())
                .as("Сумма оплаты должна быть " + expectedAmount)
                .isEqualTo(expectedAmount);
    }

    public void verifyPaymentNumber(String expectedNumber) {
        Assertions.assertThat(mtsMainPage.getPaymentNumber())
                .as("Номер телефона должен быть " + expectedNumber)
                .isEqualTo(expectedNumber);
    }

    public void verifyPaymentIconsDisplayed() {
        Assertions.assertThat(mtsMainPage.arePaymentIconsDisplayed())
                .as("Иконки платёжных систем должны отображаться")
                .isTrue();
    }

    public void verifyPlaceholderText(String fieldXPath, String expectedText) {
        Assertions.assertThat(mtsMainPage.getPlaceholderText(fieldXPath))
                .as("Плейсхолдер должен быть: " + expectedText)
                .isEqualTo(expectedText);
    }

    public void verifyValidationError(String expectedMessage) {
        Assertions.assertThat(mtsMainPage.getValidationErrorMessage())
                .as("Ошибка валидации должна быть: " + expectedMessage)
                .isEqualTo(expectedMessage);
    }
}
