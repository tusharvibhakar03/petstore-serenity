package com.petstore.steps;

import com.petstore.constants.EndPoints;
import com.petstore.constants.Path;
import com.petstore.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {

    @Step("Creating pet with   id : {0}, category: {1}, name: {2},photourl:{3},tags:{4} and status: {5}")
    public ValidatableResponse createUser(int id,String userName,String firstName,String lastName,String email,String password,String phone,int status){

        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(userName);
        userPojo.setPassword(password);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(status);

        return SerenityRest.given().log().all()
                .basePath(Path.USER)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .when()
                .body(userPojo)
                .post().then();
    }
    @Step("Get product details of id : {0}")
    public HashMap<String, Object> getProductInfoById(String userName){
        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .basePath(Path.USER)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .when()
                .pathParams("name",userName)
                .get(EndPoints.GET_SINGLE_USER_BY_NAME)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;

    }
    @Step("Update product details of id: {0}")
    public ValidatableResponse updateProduct(int id,String userName,String firstName,String lastName,String email,String password,String phone,int status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setId(id);
        userPojo.setUsername(userName);
        userPojo.setPassword(password);
        userPojo.setFirstName(firstName);
        userPojo.setLastName(lastName);
        userPojo.setEmail(email);
        userPojo.setPhone(phone);
        userPojo.setUserStatus(status);
        return SerenityRest.given().log().all()
                .basePath(Path.PET)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(userPojo)
                .when()
                .put()
                .then();
    }
    @Step("Deleting pet information with Id: {0}")

    public ValidatableResponse deleteProduct(String userName){
        return  SerenityRest.given().log().all()
                .basePath(Path.USER)
                .header("accept", "application/json")
                .pathParam("name",userName)
                .when()
                .delete(EndPoints.DELETE_USER_BY_NAME)
                .then();
    }
    @Step("Getting pet information with Id: {0}")

    public ValidatableResponse getProductId(String userName){
        return SerenityRest.given().log().all()
                .basePath(Path.USER)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("name",userName)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_NAME)
                .then();

    }
}