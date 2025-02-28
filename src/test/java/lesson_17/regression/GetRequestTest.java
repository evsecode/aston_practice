package lesson_17.regression;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lesson_17.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Tag("regression")
public class GetRequestTest extends BaseTest {

    @Test
    @Description("Проверка GET-запроса с параметрами")
    public void testGetRequest() {
        sendGetRequest("/get", "foo1", "bar1", "foo2", "bar2");
    }

    @Step("Отправка GET-запроса на {endpoint} с параметрами {param1}={value1}, {param2}={value2}")
    public void sendGetRequest(String endpoint, String param1, String value1, String param2, String value2) {
        given()
                .spec(requestSpec)
                .queryParam(param1, value1)
                .queryParam(param2, value2)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .body("args." + param1, equalTo(value1))
                .body("args." + param2, equalTo(value2));
    }
}
