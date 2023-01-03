package com.petstore.steps;

import com.petstore.constants.EndPoints;
import com.petstore.constants.Path;
import com.petstore.model.PetPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

public class PetSteps {
    @Step("Creating pet with   id : {0}, category: {1}, name: {2},photourl:{3},tags:{4} and status: {5}")
    public ValidatableResponse createUser(int id, HashMap<String, Object>pet, String name,List<String> photo,List<HashMap<String,Object>>petList,String status){

        PetPojo petPojo= new PetPojo();
        petPojo.setId(id);
        petPojo.setCategory(pet);
        petPojo.setName(name);
        petPojo.setPhotourl(photo);
        petPojo.setTags(petList);
        petPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .basePath(Path.PET)
                .contentType(ContentType.JSON)
                .header("Content-Type", "application/json")
                .when()
                .body(petPojo)
                .post().then();
    }
    @Step("Get product details of id : {0}")
    public HashMap<String, Object> getProductInfoById(int petId){
        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .basePath(Path.PET)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .when()
                .pathParams("id", petId)
                .get(EndPoints.GET_SINGLE_PET_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;

    }
    @Step("Update product details of id: {0}")
    public ValidatableResponse updateProduct(int id, HashMap<String, Object>pet, String name,List<String> photo,List<HashMap<String,Object>>petList,String status) {
        PetPojo petPojo= new PetPojo();
        petPojo.setId(id);
        petPojo.setCategory(pet);
        petPojo.setName(name);
        petPojo.setPhotourl(photo);
        petPojo.setTags(petList);
        petPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .basePath(Path.PET)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body(petPojo)
                .when()
                .put()
                .then();
    }
    @Step("Deleting pet information with Id: {0}")

    public ValidatableResponse deleteProduct(int petId){
        return  SerenityRest.given().log().all()
                .basePath(Path.PET)
                .header("accept", "application/json")
                .pathParam("id",petId)
                .when()
                .delete(EndPoints.DELETE_PET_BY_ID)
                .then();
    }
    @Step("Getting pet information with Id: {0}")

    public ValidatableResponse getProductId(int petId){
        return SerenityRest.given().log().all()
                .basePath(Path.PET)
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .pathParam("id",petId)
                .when()
                .get(EndPoints.GET_SINGLE_PET_BY_ID)
                .then();

    }
}

