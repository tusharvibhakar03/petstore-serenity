package com.petstore.testsutie;

import com.petstore.model.PetPojo;
import com.petstore.steps.PetSteps;
import com.petstore.steps.UserSteps;
import com.petstore.testbase.TestBase;
import com.petstore.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.hasValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SerenityRunner.class)
public class UserCrud extends TestBase {
    static String userName ="Doggie"+ TestUtils.getRandomValue();
    static int id =100;
    static String password = "welcome";
    static String firstName = "Prime"+TestUtils.getRandomValue();
    static String lastName ="Testing";
    static String email = "Prime"+TestUtils.getRandomValue()+"gmail.com";
    static String phone = "01923565899";
    static int status =10;

    static int userId;
    @Steps
    UserSteps userSteps;
    @Title("This will create new user")
    @Test
    public void test001() {

        ValidatableResponse response = userSteps.createUser(id,userName,firstName,lastName,email,password,phone,status);
        response.log().all().statusCode(200);
//        userId = response.log().all().extract().path("id");
//        System.out.println("user ID"+userId);
    }
    @Title("This test will Update the user information")
    @Test
    public void test002() {
        firstName=firstName+TestUtils.getRandomValue();
        PetPojo petPojo= new PetPojo();
        HashMap<String,Object> pet = new HashMap<String, Object>();
        pet.put("id",10);
        pet.put("name","Pluto");
        List<String> photo=new ArrayList<String>();
        photo.add("ABCD");
        List<HashMap<String,Object>>petList = new ArrayList<HashMap<String,Object>>();
        petList.add(pet);
        userSteps.updateProduct(id,userName,firstName,lastName,email,password,phone,status).statusCode(200).log().all();
        HashMap<String,Object> productMapData =userSteps.getProductInfoById(userName);
        Assert.assertThat(productMapData,hasValue(userName));
    }
    @Title("Delete the pet and verify if the user is deleted!")
    @Test
    public void test003() {
        System.out.println("Id which wanted to delete"+userName);
        userSteps.deleteProduct(userName).statusCode(200);
        userSteps.getProductId(userName).statusCode(404);

    }
}