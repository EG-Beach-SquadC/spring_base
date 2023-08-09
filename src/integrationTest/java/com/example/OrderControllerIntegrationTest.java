package com.example;


import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OrderControllerIntegrationTest extends BaseIntegrationTest {
  @Test
  @DataSet("order.yaml")
  public void find_all_orders_successfully() {
    given().when().get("/orders?customerId=fdf2f34e-6be2-487e-ac0d-56823f267b35").then()
        .statusCode(200).body("size()", equalTo(2))
        .body("[0].id", equalTo("9e22c739-3656-446b-96c1-5a8a13e2771c"))
        .body("[1].id", equalTo("c0a8a4c8-5a51-46cc-9db0-f4a18d743ba8"));
  }

  @Test
  @DataSet("order.yaml")
  public void find_specific_order_successfully() {
    given().when().get("/orders/9e22c739-3656-446b-96c1-5a8a13e2771c").then().statusCode(200)
        .body("id", equalTo("9e22c739-3656-446b-96c1-5a8a13e2771c"))
        .body("status", equalTo("CREATED"));
  }
}
