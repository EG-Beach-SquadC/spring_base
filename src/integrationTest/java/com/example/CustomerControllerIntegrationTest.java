package com.example;


import com.github.database.rider.core.api.dataset.DataSet;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CustomerControllerIntegrationTest extends BaseIntegrationTest {

  @Test
  @DataSet("customer.yaml")
  public void findById_should_success() {
    given().when().get("/customers/{id}", "1").then().statusCode(200).body("name",
        equalTo("第一个大客户"));
  }
}
