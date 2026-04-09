package lesson_18.smoke;

import io.qameta.allure.*;
import lesson_18.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("MTS UI Testing")
@Feature("Smoke Tests")
@Tag("lesson_18")
public class MtsSmokeTest extends BaseTest {

    @Test
    @Story("Проверка заголовка главного блока")
    @Description("Убедиться, что заголовок блока 'Онлайн пополнение без комиссии' отображается корректно")
    @Severity(SeverityLevel.TRIVIAL)
    void testBlockTitle() {
        mtsMainPageSteps.closeCookiePopupIfExists();
        mtsMainPageSteps.verifyBlockTitle("Онлайн пополнение без комиссии");
    }

    @Test
    @Story("Проверка кликабельности ссылки 'Подробнее'")
    @Description("Открытие страницы дополнительной информации")
    @Severity(SeverityLevel.TRIVIAL)
    void testMoreInfoLink() {
        mtsMainPageSteps.closeCookiePopupIfExists();
        mtsMainPageSteps.clickMoreInfo();
    }
}
