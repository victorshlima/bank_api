package br.com.dbtest.bank;

import java.io.Serializable;

public class TestFiles implements Serializable {


    public static String conta1 = "{\n" +
            "\"agencia\" : \"1\",\n" +
            "\"conta\" : \"1\",\n" +
            "\"saldo\" : \"100\",\n" +
            "\"limite\" : \"100\",\n" +
            "\"tipo\" : \"corrente\"\n" +
            "}";

    public static String conta2 = "{\n" +
            "\"agencia\" : \"2\",\n" +
            "\"conta\" : \"2\",\n" +
            "\"saldo\" : \"100\",\n" +
            "\"limite\" : \"100\",\n" +
            "\"tipo\" : \"corrente\"\n" +
            "}";


    public static String transf1 = "{\n" +
            "\t\"agenciaOrig\": 1,\n" +
            "    \"contaOrig\": 1,\n" +
            "    \"agenciaDest\": 2,\n" +
            "    \"contaDest\": 2,\n" +
            "    \"valor\": 100.0,\n" +
            "    \"limite\": 100.0,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";

    public static String transfOver = "{\n" +
            "\t\"agenciaOrig\": 1,\n" +
            "    \"contaOrig\": 1,\n" +
            "    \"agenciaDest\": 2,\n" +
            "    \"contaDest\": 2,\n" +
            "    \"valor\": 1000000.0,\n" +
            "    \"limite\": 100.0,\n" +
            "    \"tipo\": \"corrente\",\n" +
            "    \"date\": \"2019101000123\",\n" +
            "    \"status\": \"ok\"\n" +
            "}";


    public String getTransfOver() {
        return transfOver;
    }

    public String getConta1() {
        return conta1;
    }

    public String getConta2() {
        return conta2;
    }

    public String getTransf1() {
        return transf1;
    }
}
