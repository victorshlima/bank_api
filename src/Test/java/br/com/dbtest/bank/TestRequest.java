package br.com.dbtest.bank;


import br.com.dbtest.bank.resource.rest.LancamentoRestController;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.restassured.RestAssured.given;

public class TestRequest extends LancamentoRestController {

    private static final Logger logger = LogManager.getLogger(TestRequest.class);

    private static TestResourceRequest f = new TestResourceRequest();

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @BeforeClass
    public static void InsertTestResource() {

        PostInsert(f.getConta1());
        PostInsert(f.getConta2());
        PostInsert(TestResourceRequest.getConta3());
    }

    public static Response PostTransf(String payload, int status) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().contentType("application/json")
                        .body(payload)
                        .post("/rest/lanc")
                        .then()
                        .statusCode(status)
                        .extract()
                        .response();
    }

    public static Response PostInsert(String payload) {
        RestAssured.defaultParser = Parser.JSON;
        return given().contentType("application/json")
                .body(payload)
                .post("/rest/transfs")
                .then()
                .extract()
                .response();
    }

    public static Response PostTransfUrl(String payload, int status, String URL) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().contentType("application/json")
                        .body(payload)
                        .post(URL)
                        .then()
                        .statusCode(status)
                        .extract()
                        .response();
    }

    @Test
    public void TestTransSucess() {
        PostTransf(f.getTransfOk(), 201);
    }

    @Test
    public void TestInvalidType() {
        PostTransf(f.getInvalidType(), 400);
    }

    @Test
    public void TestTransfOverLimit() {
        PostTransf(f.getTransfOver(), 500);
    }

    @Test
    public void TestFieldBasent() {
        PostTransf(f.getFieldAbsent(), 500);
    }

    @Test
    public void TestNoEntityFoundForQuery() {
        PostTransf(f.getNoEntityFound(), 500);
    }

    @Test
    @Category(br.com.dbtest.bank.resource.rest.LancamentoRestController.class)
    public void TestValidateTransfUrl() {
        PostTransfUrl(f.getUrlTransfError(), 404, f.getUrlTransfError());
    }


}