package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    User userPayload;

    @BeforeClass
    public void setupData(){
        userPayload=new User();

        userPayload.setId(0);
        userPayload.setUsername("indika");
        userPayload.setFirstname("Indika");
        userPayload.setLastname("senarathna");
        userPayload.setEmail("indika@gmail.com");
        userPayload.setPhone("123456789");
        userPayload.setUserStatus(0);
    }

    @Test(priority = 1)
    public void testPostUser(){
        Response response= UserEndPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 2)
    public void testGetUser(){
        Response response= UserEndPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUser(){
        userPayload.setFirstname("Isuru");
        userPayload.setLastname("perera");
        Response response= UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().body();
        Assert.assertEquals(response.statusCode(),200);

        Response responseAfterUpdate= UserEndPoints.readUser(this.userPayload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 4)
    public void testDeleteUser(){
        Response response= UserEndPoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(response.statusCode(),200);
    }
}
