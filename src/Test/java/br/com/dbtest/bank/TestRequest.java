package br.com.dbtest.bank;


import br.com.dbtest.bank.resource.rest.LancamentoRestController;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static io.restassured.RestAssured.given;

public class TestRequest extends LancamentoRestController {

    private static TestFiles f = new TestFiles();

      @BeforeClass
      public static void init()   {
          RestAssured.baseURI = "http://localhost";
          RestAssured.port = 8080;
      }

    @BeforeClass
    public static void postJsonPayloadSend() {

        InsertAccountPost(f.getConta1());
        InsertAccountPost(f.getConta2());
    }

    public static Response InsertAccountPost(String payload) {
        RestAssured.defaultParser = Parser.JSON;
        return    given().contentType("application/json")
                        .body(payload)
                        .post("/rest/transfs")
                              .then()
                              .extract()
                              .response()
                        ;
    }

    public static Response TestTransSucess(String payload) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().contentType("application/json")
                        .body(payload)
                        .post("/rest/lanc")
                        .then()
                        .statusCode(201)
                        .extract()
                        .response()
                ;
    }

    public static Response TestTransOverLimit(String payload) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().contentType("application/json")
                        .body(payload)
                        .post("/rest/lanc")
                        .then()
                        .statusCode(500)
                        .extract()
                        .response()
                ;
    }


    @Test
    @Category(br.com.dbtest.bank.resource.rest.LancamentoRestController.class)
    public void postJsonPayloadSend2() {
        TestTransSucess(f.getTransf1());

    }

    @Test
    public void PostTrasfOver() {
        TestTransOverLimit(f.getTransfOver());
    }


    //   @Test
//    public void TestTransSucess() {
    //       this.TestTransSucess(payload3);
    //  }
//        @DisplayName("Test 1")
//        @Test
//        public void  testGet() {
//           // RestAssured.defaultParser();
//            System.out.println("Test 1");
//            RestAssured.defaultParser = Parser.JSON;
////            System.out.println(given().contentType("application/json")
////                    .get("/rest/transfs").getBody().prettyPrint());
//
////              given().contentType("application/json")
////                      .get("/rest/transfs")
////                    .then()
////                    .body("$['conta']", hasItems('1')) ;
//
//              given().contentType("application/json")
//                      .param("agenciaOrig", "1")
//                      .param("contaOrig", "1")
//                      .param("agenciaDest", "2")
//                      .param("contaDest", "2")
//                      .param("valor", "100")
//                      .param("tipo", "corrente")
//                      .get("/rest/transfs/findaccount")
//                    .then()
//                    .body("conta", equalTo(1)) ;
//
//
//             System.out.println(
//                     given().contentType("application/json")
//                             .param("agenciaOrig", "1")
//                             .param("contaOrig", "1")
//                             .param("agenciaDest", "2")
//                             .param("contaDest", "2")
//                             .param("valor", "100")
//                             .param("tipo", "corrente")
//                             .get("/rest/transfs/findaccount").getBody().prettyPrint()
//
//                               );

//            System.out.println(
//                    get("/rest/transfs")
                   // .body("$", find(1, 2, 3))
 //                   );

//            System.out.println(
//                   "2"
//            );
//            System.out.println(
//                    given().body("{\"agencia\" : \"4\",\"conta\" : \"3\",\"saldo\" : \"2000\",\"limite\" : \"100\",\"tipo\" : \"corrente\"}")
//                    .post("/rest/transfs")
//
//            );
//
//            given().body("{\"agencia\" : \"4\",\"conta\" : \"3\",\"saldo\" : \"2000\",\"limite\" : \"100\",\"tipo\" : \"corrente\"}")
//                    .post("/rest/transfs");

        }