package ru.annachemic.tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


class AccountTests extends BaseTest {
    @Test
    void getAccountInfoTest() {
        given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .when()
                .get("https://api.imgur.com/3/account/AnnaSmotrova")
                .then()
                .statusCode(200);
    }
    @Test
    void getAccountInfoWithLoggingTest() {
        given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/AnnaSmotrova")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void getAccountInfoWithAssertionsInGivenTest() {
        given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .expect()
                .statusCode(200)
                .body("data.url", equalTo("AnnaSmotrova"))
                .body("success", equalTo(true))
                .body("status", equalTo(200))
                .contentType("application/json")
                .when()
                .get("https://api.imgur.com/3/account/AnnaSmotrova")
                .prettyPeek();

    }

    @Test
    void getAccountInfoWithAssertionsAfterTest() {
        Response response = given()
                .header("Authorization", "Bearer 81ed217eee6d991be324edc8754a07e4ce686bb9")
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/AnnaSmotrova")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.url"), equalTo("AnnaSmotrova"));
    }

//    public static void getProperties(){
//        try (InputStream output = new FileInputStream("src/main/resourses/resourses.properties")) {
//            properties.load(output);
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
