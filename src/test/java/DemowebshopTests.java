import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;

public class DemowebshopTests {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
    }

    @Test
    void addToCartTest() {
        String bodyValue = "product_attribute_72_5_18=53&" +
                "product_attribute_72_6_19=54&" +
                "product_attribute_72_3_20=57&" +
                "addtocart_72.EnteredQuantity=1";
        given()
//                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyValue)
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void searchComputerTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .when()
                .get("/search?q=Computer")
                .then()
                .log().status()
                .statusCode(200);
    }

    @Test
    void addToWishlistTest() {
        String bodyValue = "giftcard_3.RecipientName=Some&" +
                "giftcard_3.SenderName=Evgenii+S&" +
                "giftcard_3.Message=&addtocart_3.EnteredQuantity=1";
        given()
//                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(bodyValue)
                .when()
                .post("/addproducttocart/details/3/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                .body("updatetopwishlistsectionhtml", is("(1)"));
    }
}
