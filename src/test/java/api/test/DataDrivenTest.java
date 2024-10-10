package api.test;

import api.endpoints.UserEndPoints;
import api.payload.Userpayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {
    Userpayload userpayload;
    @Test(priority=1,dataProvider="Data",dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId,String userName,String fName,String lName,String userEmail,String pwd,String ph)
    {
        userpayload = new Userpayload();
        userpayload.setId(Integer.parseInt(userId.replace(".0","")));
        userpayload.setUsername(userName);
        userpayload.setFirstName(fName);
        userpayload.setLastName(lName);
        userpayload.setEmail(userEmail);
        userpayload.setPassword(pwd);
        userpayload.setPhone(ph);

       Response response =  UserEndPoints.createUser(userpayload);
       response.then().log().all();
    }
    @Test(priority = 2,dataProvider="UserNames",dataProviderClass = DataProviders.class)
    public void testGetUser(String userName)
    {
       Response response = UserEndPoints.getUser(userName);
               response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 3,dataProvider="Data",dataProviderClass= DataProviders.class )
    public void testUpdateUser(String userId,String userName,String fName,String lName,String userEmail,String pwd,String ph)
    {

        userpayload.setFirstName(fName+"update");
        userpayload.setPhone(ph+"1111");

       Response response = UserEndPoints.updateUser(userpayload,userpayload.getUsername());
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 4,dataProvider="UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName)
    {
       Response response = UserEndPoints.deleteUser(userName);
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
    }
}
