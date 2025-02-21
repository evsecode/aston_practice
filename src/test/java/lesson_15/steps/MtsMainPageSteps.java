package lesson_15.steps;

import lesson_15.pages.MtsMainPage;
import org.assertj.core.api.Assertions;

public class MtsMainPageSteps {
    private final MtsMainPage mtsMainPage;

    public MtsMainPageSteps(MtsMainPage mtsMainPage) {
        this.mtsMainPage = mtsMainPage;
    }

    public void verifyBlockTitle(String expectedTitle) {
        mtsMainPage.closeCookiePopupIfExists();
        Assertions.assertThat(mtsMainPage.getBlockTitle()).isEqualTo(expectedTitle);
    }

    public void verifyPaymentLogosAreDisplayed() {
        mtsMainPage.closeCookiePopupIfExists();
        Assertions.assertThat(mtsMainPage.arePaymentLogosDisplayed())
                .as("Логотипы платёжных систем должны отображаться")
                .isTrue();
    }

    public void clickMoreInfo() {
        mtsMainPage.closeCookiePopupIfExists();
        mtsMainPage.clickMoreInfo();
    }

    public void fillPaymentFormAndSubmit(String phone, String amount, String email) {
        mtsMainPage.closeCookiePopupIfExists();
        mtsMainPage.enterPhoneNumber(phone);
        mtsMainPage.enterAmount(amount);
        mtsMainPage.enterEmail(email);
        mtsMainPage.clickContinue();
    }

    public void verifyPaymentWindowOpened() {
        Assertions.assertThat(mtsMainPage.isPaymentWindowDisplayed())
                .as("Окно оплаты должно появиться после нажатия 'Продолжить'")
                .isTrue();
    }
}
