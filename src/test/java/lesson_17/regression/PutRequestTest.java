package lesson_17.regression;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lesson_17.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Tag("regression")
public class PutRequestTest extends BaseTest {

    @Test
    @Description("Проверка PUT-запроса")
    public void testPutRequest() {
        String requestBody = "{ \"key\": \"value\" }";
        sendPutRequest("/put", requestBody);
    }

    @Step("Отправка PUT-запроса на {endpoint} с телом {body}")
    public void sendPutRequest(String endpoint, String body) {
        given()
                .spec(requestSpec)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .statusCode(200)
                .body("json.key", equalTo("value"));
    }
}
