package PathAndQueryParametresTutorial;

import static io.restassured.RestAssured.*;
import static io.restassured.response.Response.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

// https://reqres.in/api/users?page=2&id=5

public class PathAndQueryParameters {

    @Test
    void testQueryAndPathParameters() {

        given()
                .pathParam("mypath", "users") //path parameters
                .queryParam("page", 2)  // query param
                .queryParam("id", 5)    //query param

                .when()
                        .get("https://reqres.in/api/{mypath}")
                .then()
                .statusCode(200);

    }
}
