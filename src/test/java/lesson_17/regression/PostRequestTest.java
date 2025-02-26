package lesson_17.regression;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lesson_17.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Tag("regression")
public class PostRequestTest extends BaseTest {

    @Test
    @Description("Проверка корректности POST-запроса")
    public void testPostRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        sendPostRequest("/post", requestBody);
    }

    @Step("Отправка POST-запроса на {endpoint} с телом {body}")
    public void sendPostRequest(String endpoint, String body) {
        given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .statusCode(200)
                .body("data", equalTo(body));
    }
}
