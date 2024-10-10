package api.endpoints;
import api.payload.Userpayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    public static Response createUser(Userpayload payload)
    {
       Response response = given()
                .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);
               return response;

    }
    public static Response getUser(String userName)
    {
        Response response = given()
                .pathParam("username",userName)
                .when()
                .get(Routes.get_url);
        return response;

    }
    public static Response updateUser(Userpayload payload,String userName)
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username",userName)
                .when()
                .put(Routes.update_url);
        return response;

    }
    public static Response deleteUser(String userName)
    {
        Response response = given()

                .pathParam("username",userName)
                .when()
                .delete(Routes.delete_url);
        return response;

    }

}
