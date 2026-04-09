package lesson_18.regression;

import io.qameta.allure.*;
import lesson_18.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

@Epic("MTS UI Testing")
@Feature("Regression Tests")
@Tag("lesson_18")
public class MtsRegressionTest extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "Домашний интернет, //input[@id='internet-phone'], Номер абонента",
            "Домашний интернет, //input[@id='internet-sum'], Сумма",
            "Рассрочка, //input[@id='score-instalment'], Номер счета на 44",
            "Задолженность, //input[@id='score-arrears'], Номер счета на 2073",
            "Услуги связи, //input[@id='connection-phone'], Номер телефона",
            "Услуги связи, //input[@id='connection-email'], E-mail для отправки чека"
    })
    @Story("Проверка плейсхолдеров полей ввода")
    @Description("Убедиться, что плейсхолдеры отображаются корректно")
    @Severity(SeverityLevel.TRIVIAL)
    void testPlaceholders(String paymentOption, String fieldXPath, String expectedPlaceholder) {
        mtsMainPageSteps.closeCookiePopupIfExists();
        mtsMainPageSteps.selectPaymentOption(paymentOption);
        mtsMainPageSteps.verifyPlaceholderText(fieldXPath, expectedPlaceholder);
    }

    @ParameterizedTest
    @Story("Проверка сообщений об ошибках валидации")
    @Description("Убедиться, что ошибки валидации отображаются корректно")
    @Severity(SeverityLevel.NORMAL)
    @CsvSource({
            "Домашний интернет,//input[@id='internet-phone'],299873423," +
                    "//input[@id='internet-sum'],10, pay-internet, Необходимо указать номер в формате +375 00 ХХХ-ХХ-ХХ",
            "Рассрочка,//input[@id='score-instalment'],4412345," +
                    "//input[@id='instalment-sum'],10, pay-instalment, Введите корректный номер лицевого счета"
    })
    void testValidationErrors(
            String paymentOption, String field1, String value1,
            String field2, String value2, String submitButton,
            String expectedError
    ) {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.selectPaymentOption(paymentOption);
        mtsMainPageSteps.fillPaymentForm(Map.of(
                field1, value1,
                field2, value2
        ));
        mtsMainPageSteps.submitPaymentForm(submitButton);
        mtsMainPageSteps.verifyValidationError(expectedError);
    }

    @ParameterizedTest
    @Story("Проверка отображения итоговой суммы платежа")
    @Description("Убедиться, что сумма платежа отображается корректно в итоговом окне")
    @Severity(SeverityLevel.CRITICAL)
    @CsvSource({
            "Услуги связи, //input[@id='connection-phone'], 297777777," +
                    "//input[@id='connection-sum'], 10," +
                    "//input[@id='connection-email'], test@example.com," +
                    "pay-connection, 10.00 BYN, 375297777777"
    })
    void testPaymentSummary(
            String paymentOption,
            String phoneField, String phoneValue,
            String sumField, String sumValue,
            String emailField, String emailValue,
            String submitButton,
            String expectedAmount, String expectedPhone
    ) {
        mtsMainPageSteps.closeCookiePopupIfExists();

        mtsMainPageSteps.selectPaymentOption(paymentOption);

        Map<String, String> fields = Map.of(
                phoneField, phoneValue,
                sumField, sumValue,
                emailField, emailValue
        );

        mtsMainPageSteps.fillPaymentForm(fields);
        mtsMainPageSteps.submitPaymentForm(submitButton);

        mtsMainPageSteps.verifyPaymentAmount(expectedAmount);
        mtsMainPageSteps.verifyPaymentNumber(expectedPhone);
        mtsMainPageSteps.verifyPaymentIconsDisplayed();
    }
}
