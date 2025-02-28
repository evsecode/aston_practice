package lesson_17.regression;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lesson_17.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Tag("regression")
public class DeleteRequestTest extends BaseTest {

    @Test
    @Description("Проверка DELETE-запроса")
    public void testDeleteRequest() {
        sendDeleteRequest("/delete");
    }

    @Step("Отправка DELETE-запроса на {endpoint}")
    public void sendDeleteRequest(String endpoint) {
        given()
                .spec(requestSpec)
                .when()
                .delete(endpoint)
                .then()
                .statusCode(200)
                .body("args", notNullValue())
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}
