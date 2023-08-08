package com.example;

import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProductControllerIntegrationTest extends BaseIntegrationTest {
  @Test
  @DataSet("product.yaml")
  public void find_all_products_successfully() {
    given().when().get("/products").then().statusCode(200).body("size()", equalTo(3))
        .body("[0].id", equalTo("a362e6ed-c161-4982-9858-0939f21c7c3b"))
        .body("[1].id", equalTo("818b8686-cc26-4ddf-ba88-95667ae4c6b7"))
        .body("[2].id", equalTo("cdd7680b-a9f8-4f39-acfd-a4084ac456fb"));
  }


}
