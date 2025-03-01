package lesson_18.steps;

import io.qameta.allure.Step;
import lesson_18.pages.MtsMainPage;
import org.assertj.core.api.Assertions;

import java.util.Map;

public class MtsMainPageSteps {
    private final MtsMainPage mtsMainPage;

    public MtsMainPageSteps(MtsMainPage mtsMainPage) {
        this.mtsMainPage = mtsMainPage;
    }

    @Step("Закрываем всплывающее окно с куки")
    public void closeCookiePopupIfExists() {
        mtsMainPage.closeCookiePopupIfExists();
    }

    @Step("Проверяем заголовок блока: ожидаемый = {expectedTitle}")
    public void verifyBlockTitle(String expectedTitle) {
        Assertions.assertThat(mtsMainPage.getBlockTitle()).isEqualTo(expectedTitle);
    }

    @Step("Нажимаем на 'Подробнее о сервисе'")
    public void clickMoreInfo() {
        mtsMainPage.clickMoreInfo();
    }

    @Step("Выбираем способ оплаты: {option}")
    public void selectPaymentOption(String option) {
        mtsMainPage.selectPaymentOption(option);
    }

    @Step("Заполняем поле {fieldXPath} значением {value}")
    public void fillField(String fieldXPath, String value) {
        mtsMainPage.enterFieldData(fieldXPath, value);
    }

    @Step("Заполняем форму оплаты: {fields}")
    public void fillPaymentForm(Map<String, String> fields) {
        fields.forEach(this::fillField);
    }

    @Step("Нажимаем кнопку 'Продолжить' в форме {formId}")
    public void submitPaymentForm(String formId) {
        mtsMainPage.clickContinue(formId);
    }

    @Step("Проверяем сумму платежа: ожидаемая = {expectedAmount}")
    public void verifyPaymentAmount(String expectedAmount) {
        Assertions.assertThat(mtsMainPage.getPaymentAmount())
                .as("Сумма оплаты должна быть " + expectedAmount)
                .isEqualTo(expectedAmount);
    }

    @Step("Проверяем номер телефона: ожидаемый = {expectedNumber}")
    public void verifyPaymentNumber(String expectedNumber) {
        Assertions.assertThat(mtsMainPage.getPaymentNumber())
                .as("Номер телефона должен быть " + expectedNumber)
                .isEqualTo(expectedNumber);
    }

    @Step("Проверяем, что иконки платёжных систем отображаются")
    public void verifyPaymentIconsDisplayed() {
        Assertions.assertThat(mtsMainPage.arePaymentIconsDisplayed())
                .as("Иконки платёжных систем должны отображаться")
                .isTrue();
    }

    @Step("Проверяем плейсхолдер у поля {fieldXPath}: ожидаемый = {expectedText}")
    public void verifyPlaceholderText(String fieldXPath, String expectedText) {
        Assertions.assertThat(mtsMainPage.getPlaceholderText(fieldXPath))
                .as("Плейсхолдер должен быть: " + expectedText)
                .isEqualTo(expectedText);
    }

    @Step("Проверяем текст ошибки валидации: ожидаемый = {expectedMessage}")
    public void verifyValidationError(String expectedMessage) {
        Assertions.assertThat(mtsMainPage.getValidationErrorMessage())
                .as("Ошибка валидации должна быть: " + expectedMessage)
                .isEqualTo(expectedMessage);
    }
}
