package br.com.dbtest.bank;


import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;

public class TestRequest   {
      @BeforeClass
      public static void init()   {
          RestAssured.baseURI = "http://localhost";
          RestAssured.port = 8080;
      }
    private static String payload1 = "{\n" +
            "\"agencia\" : \"1\",\n" +
            "\"conta\" : \"1\",\n" +
            "\"saldo\" : \"2000\",\n" +
            "\"limite\" : \"100\",\n" +
            "\"tipo\" : \"corrente\"\n" +
            "}";

    private static String payload2 = "{\n" +
            "\"agencia\" : \"2\",\n" +
            "\"conta\" : \"2\",\n" +
            "\"saldo\" : \"2000\",\n" +
            "\"limite\" : \"100\",\n" +
            "\"tipo\" : \"corrente\"\n" +
            "}";

    private static String payload3 = "{\n" +
            "\"agenciaOrig\": 1,\n" +
            "    \"contaOrig\": 1,\n" +
            "    \"agenciaDest\": 2,\n" +
            "    \"contaDest\": 2,\n" +
            "    \"saldo\": 1000.0,\n" +
            "    \"limite\": 100.0,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"status\": \"ok\",\n" +
            "    \"data\": \"10\"\n" +
            "}";

    @DisplayName("Test INit")
    public Response postJsonPayload(String payload) {
        RestAssured.defaultParser = Parser.JSON;
        return    given().contentType("application/json")
                        .body(payload)
                        .post("/rest/transfs")
                              .then()
                              .extract()
                              .response()
                        ;
    }

    public Response postJsonPayload2(String payload) {
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

    @Test
    public void postJsonPayloadSend() {

        this.postJsonPayload(payload1);
        this.postJsonPayload(payload2);
    }


    @Test
    public void postJsonPayload2() {
        this.postJsonPayload2(payload3);
    }
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