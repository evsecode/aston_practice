package lesson_17.regression;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lesson_17.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Tag("regression")
public class PatchRequestTest extends BaseTest {

    @Test
    @Description("Проверка PATCH-запроса")
    public void testPatchRequest() {
        String requestBody = "{ \"patched\": true }";
        sendPatchRequest("/patch", requestBody);
    }

    @Step("Отправка PATCH-запроса на {endpoint} с телом {body}")
    public void sendPatchRequest(String endpoint, String body) {
        given()
                .spec(requestSpec)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .statusCode(200)
                .body("json.patched", equalTo(true));
    }
}
