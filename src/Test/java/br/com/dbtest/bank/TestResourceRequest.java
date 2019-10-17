package br.com.dbtest.bank;

import java.io.Serializable;


public class TestResourceRequest implements Serializable {


    public static String conta1 = "{\n" +
            "\"agencia\" : 12301,\n" +
            "\"conta\" : 123000123,\n" +
            "\"saldo\" : \"1000.00\",\n" +
            "\"limite\" : \"100.00\",\n" +
            "\"tipo\" : \"corrente\"\n" +
            "}";

    public static String conta2 = "{\n" +
            "\"agencia\" : \"22301\",\n" +
            "\"conta\" : \"223000123\",\n" +
            "\"saldo\" : \"1000.00\",\n" +
            "\"limite\" : \"500.00\",\n" +
            "\"tipo\" : \"corrente\"\n" +
            "}";

    public static String conta3 = "{\n" +
            "\"agencia\" : \"32301\",\n" +
            "\"conta\" : \"323000123\",\n" +
            "\"saldo\" : \"1000.00\",\n" +
            "\"limite\" : \"500.00\",\n" +
            "\"tipo\" : \"corrente\"\n" +
            "}";


    public static String transfSameOrigin = "{\n" +
            "\"agenciaOrig\":12301,\n" +
            "    \"contaOrig\":123000123,\n" +
            "    \"agenciaDest\":12301,\n" +
            "    \"contaDest\":123000123,\n" +
            "    \"valor\": 100.00,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";
    public static String transfOk = "{\n" +
            "\"agenciaOrig\":12301,\n" +
            "    \"contaOrig\":123000123,\n" +
            "    \"agenciaDest\":22301,\n" +
            "    \"contaDest\":223000123,\n" +
            "    \"valor\": 100.00,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";

    public static String transfOver = "{\n" +
            "\"agenciaOrig\":12301,\n" +
            "    \"contaOrig\":123000123,\n" +
            "    \"agenciaDest\":22301,\n" +
            "    \"contaDest\":223000123,\n" +
            "    \"valor\": 100000000000.00,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";


    public static String agencyAbasent = "{\n" +
            "\"\":12301,\n" +
            "    \"contaOrig\":123000123,\n" +
            "    \"agenciaDest\":22301,\n" +
            "    \"contaDest\":223000123,\n" +
            "    \"valor\": 100.00,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";


    public static String invalidType = "{\n" +
            "\"agenciaOrig\":12301X,\n" +
            "    \"contaOrig\":123000123,\n" +
            "    \"agenciaDest\":22301,\n" +
            "    \"contaDest\":223000123,\n" +
            "    \"valor\": 100.00,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";

    public static String noEntityFound = "{\n" +
            "\"agenciaOrig\":12301,\n" +
            "    \"contaOrig\":9999999,\n" +
            "    \"agenciaDest\":22301,\n" +
            "    \"contaDest\":223000123,\n" +
            "    \"valor\": 100.00,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";
    public String urlTransfError = "/restInvalid/lanc";
    public String urlInsertError = "/restInvalid/transfs";

    public static String getTransfSameOrigin() {
        return transfSameOrigin;
    }

    public static String getAgencyAbasent() {
        return agencyAbasent;
    }

    public static String getConta3() {
        return conta3;
    }

    public String getUrlTransfError() {
        return urlTransfError;
    }

    public String getUrlInsertError() {
        return urlInsertError;
    }

    public String getInvalidType() {
        return invalidType;
    }

    public String getNoEntityFound() {
        return noEntityFound;
    }

    public String getFieldAbsent() {
        return agencyAbasent;
    }

    public String getTransfOver() {
        return transfOver;
    }

    public String getConta1() {
        return conta1;
    }

    public String getConta2() {
        return conta2;
    }

    public String getTransfOk() {
        return transfOk;
    }
}