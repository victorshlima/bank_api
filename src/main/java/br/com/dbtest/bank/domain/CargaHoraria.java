package br.com.dbtest.bank.domain;


import com.fasterxml.jackson.annotation.JsonValue;

public enum CargaHoraria {



VINTE_HORAS("20HS"), TRINTA_HORAS("30HS"), QUARENTA_HORAS("40HS"), SESSENTA_HORAS("60HS");



private String horas;

CargaHoraria (String horas) {this.horas = horas;}



//permite utilizar o valor da constante invés da nomenclatura da constante
@JsonValue
 public String getHoras(){



    return horas;
 }



}
