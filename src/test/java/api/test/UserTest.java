package api.test;

import api.endpoints.UserEndPoints;
import api.payload.Userpayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {
    Userpayload userpayload;
    @BeforeClass
    public void setup()
    {
        Faker faker = new Faker();
        userpayload = new Userpayload();
        userpayload.setId(faker.idNumber().hashCode());
        userpayload.setUsername(faker.name().username());
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        userpayload.setPassword(faker.internet().password());
        userpayload.setPassword(faker.phoneNumber().cellPhone());
    }
    @Test(priority=1)
    public void testCreateUser()
    {
       Response response = UserEndPoints.createUser(userpayload);
       response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=2)
    public void testGetUser()
    {
        Response response = UserEndPoints.getUser(userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=3)
    public void testUpdateUser()
    {
        Response response = UserEndPoints.updateUser(userpayload,userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority=4)
    public void testDeleteUser()
    {
        Response response = UserEndPoints.deleteUser(userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
