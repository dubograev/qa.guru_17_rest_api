import com.github.javafaker.Faker;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoWebShopTests {
    Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    String cookie = "Nop.customer=e95af19c-0cd7-4f81-abf3-f73dc4f64d38; ARRAffinity=7f10010dd6b12d83d6aefe199065b2e8fe0d0850a7df2983b482815225e42439; __utmc=78382081; __utmz=78382081.1610698722.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72&RecentlyViewedProductIds=31; __atuvc=9%7C2%2C1%7C3; __atuvs=60042a8af8b5d9c9000; __utma=78382081.1047351399.1610698722.1610805895.1610885772.3; __utmt=1; __utmb=78382081.1.10.1610885772";

    @BeforeAll
    static void beforeAll() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @DisplayName("Добавление товара в корзину с использованием API")
    void addToCartTest() {
        String body = "product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=5";

        given()
                .body(body)
                .contentType(contentType)
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Добавление товара в корзину с использованием API и assertTrue")
    void addToCartWithSimpleAssertTest() {
        String body = "product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=5";

        Response response = given()
                .body(body)
                .contentType(contentType)
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .extract().response();

        assertTrue(response.asString().contains("The product has been added to your"));
    }

    @Test
    @DisplayName("Добавление товара в корзину с использованием API (модель)")
    void addToCartWithModelTest() {
        String body = "product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=5";

        CartResponse response = given()
                .body(body)
                .contentType(contentType)
                .cookie(cookie)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .extract().as(CartResponse.class);

        System.out.println(response);
        assertEquals(response.getSuccess(), true);
    }

    @Test
    @DisplayName("Запрос Subscribe с использованием body")
    void subscribeBodyTest() {
        given()
                .body("email=" + email)
                .contentType(contentType)
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .statusCode(200)
                .body("Success", is(true))
                .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }

    @Test
    @DisplayName("Запрос Subscribe с использованием assertTrue")
    void subscribeAssertTest() {
        Response response = given()
                .body("email=" + email)
                .contentType(contentType)
                .when()
                .post("http://demowebshop.tricentis.com/subscribenewsletter")
                .then()
                .statusCode(200)
                .extract().response();

        assertTrue(response.asString().contains("Thank you for signing up! A verification email has been sent. We appreciate your interest."));
    }
}
