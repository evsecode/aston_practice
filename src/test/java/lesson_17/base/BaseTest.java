package lesson_17.base;

import io.restassured.specification.RequestSpecification;
import lesson_17.config.RequestSpecFactory;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    protected static RequestSpecification requestSpec;

    @BeforeAll
    public static void setup() {
        requestSpec = RequestSpecFactory.getRequestSpec();
    }
}
