package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class CustomerControllerTest {

    @Test
    public void testGetAllCustomers() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/customers")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("[0].id", equalTo(1L))
                .body("[1].name", equalTo("jason"))
                .body("[2].lastname", equalTo("John"))
                .body("[3].age", equalTo(19))
                .body("[4].email", equalTo("jason@email.com"))
                .body("[0].id", equalTo(2L))
                .body("[1].name", equalTo("alice"))
                .body("[2].lastname", equalTo("John"))
                .body("[3].age", equalTo(19))
                .body("[4].email", equalTo("alice@email.com"));

    }
    @Test
    public void testGetCustomerById() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/customers/1")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("[0].id", equalTo(1L))
                .body("[1].name", equalTo("jason"))
                .body("[2].lastname", equalTo("John"))
                .body("[3].age", equalTo(19))
                .body("[4].email", equalTo("jason@email.com"));
    }

    @Test
    public void testGetCustomerById_CustomerNotFound() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/customers/100")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void testAddCustomer() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"id\": 1, \"name\": \"John\"}")
                .when()
                .post("/customers")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void testDeleteCustomer() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/customers/1")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }
}