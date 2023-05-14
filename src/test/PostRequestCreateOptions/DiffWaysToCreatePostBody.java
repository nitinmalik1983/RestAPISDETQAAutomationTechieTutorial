package PostRequestCreateOptions;

/* Different approaches to create Post requests
* 1) Post request body using HashMap
* 2) Post request body creation using Org.JSON
* 3) post request body creation using POJO class
* 4) Post request using external Json file
* */

import PojoDummy.PojoPostRequest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DiffWaysToCreatePostBody {

    //Post using HashMap
//    @Test(priority = 1)
    void testPostUsingHashMap() {

        HashMap data = new HashMap();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone",123456);

        String courseArr[] = {"C", "C++"};

        data.put("courses",courseArr);

        given()
                .contentType("application/json")
                        .body(data)
                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo(123456))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-type", "application/json; charset=utf-8")
                .log().all();
    }

    @Test(priority = 2)
    void testDelete() {

        given()

                .when()
                        .delete("http://localhost:3000/students/4")
                .then()
                .statusCode(200);
    }

//    Post request body using org.Json
//    @Test(priority = 1)
    void testPostUsingJsonLibrary() {

        JSONObject data = new JSONObject();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone",123456);

        String courseArr[] = {"C", "C++"};
        data.put("courses",courseArr);

        given()
                .contentType("application/json")
                .body(data.toString())
                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo(123456))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-type", "application/json; charset=utf-8")
                .log().all();
    }

//    Request Body using POJO Class
//    @Test(priority = 1)
    void testPostUsingPOJO() {

        PojoPostRequest data = new PojoPostRequest();
        data.setName("Scott");
        data.setLocation("France");
        data.setPhone("123456");

        String coursesArr[] = {"C", "C++"};
        data.setCourses(coursesArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-type", "application/json; charset=utf-8")
                .log().all();
    }

// Post request body using external JSON file
    @Test(priority = 1)
    void testPostUsingExternalJsonfile() throws FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);

        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++"))
                .header("Content-type", "application/json; charset=utf-8")
                .log().all();
    }

}
