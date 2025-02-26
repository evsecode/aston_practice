package lesson_17.smoke;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lesson_17.base.BaseTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;

@Tag("smoke")
public class MainApiTest extends BaseTest {

    @ParameterizedTest
    @Order(1)
    @CsvSource({
            "/get, GET, 200",
            "/put, PUT, 200",
            "/post, POST, 200",
            "/delete, DELETE, 200",
            "/patch, PATCH, 200"
    })
    @Description("Параметризованный тест для проверки всех HTTP-методов")
    public void testEndpoints(String endpoint, String method, int expectedStatus) {
        sendRequest(endpoint, method, expectedStatus);
    }

    @Step("Отправка {method}-запроса на {endpoint} и проверка статус-кода {expectedStatus}")
    public void sendRequest(String endpoint, String method, int expectedStatus) {
        given()
                .spec(requestSpec)
                .body("{ \"key\": \"value\" }")
                .when()
                .request(method, endpoint)
                .then()
                .statusCode(expectedStatus);
    }
}
