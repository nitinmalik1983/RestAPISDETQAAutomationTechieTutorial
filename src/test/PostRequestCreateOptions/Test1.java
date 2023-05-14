package PostRequestCreateOptions;

/*
By Default Rest Assured Supports Gherkin keywords/methods like below
given()
    In given(): we can pass: content type, set cookies ,authentication, query pr path parameters,
    headers info etc (pre requites)
when()
    Request specifications we need to pass here like get, post , put, delete
then()
    validate response here like status code, body, extract headers, cookies..etc
* */

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test1 {

    int id;

    @Test(enabled = false)
    void getUsers() {

        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .log().all();
    }

    @Test(priority = 0)
    void createUser() {

        HashMap data = new HashMap();
        data.put("name", "pawan");
        data.put("job", "trainer");

        id=given()
            .contentType("application/json")
            .body(data)

        .when()
            .post("https://reqres.in/api/users")
            .jsonPath().getInt("id");

//        .then()
//                .statusCode(201)
//                .log().all();
    }

    @Test(priority = 1,dependsOnMethods = {"createUser"})
    void updateUser() {

        HashMap data = new HashMap();
        data.put("name", "john");
        data.put("job", "teacher");

        given()
                .contentType("application/json")
                .body(data)

        .when()
                .put("https://reqres.in/api/users/" + id)

        .then()
                .statusCode(200)
                .log().all();

    }
}
