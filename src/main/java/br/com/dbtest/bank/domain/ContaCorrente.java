package br.com.dbtest.bank.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "contacorrente"
        ,        indexes = { @Index(
        columnList = "agencia, conta",
                unique = true,
        name = "unique_agencia_conta")

        })

@JsonFormat
@JsonAutoDetect
public class ContaCorrente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private int agencia;

    @Column( nullable = false)
    private int conta;

    @Column( nullable = false)
    private Double saldo;

    @Column( nullable = false)
    private Double limite;

    @Column( nullable = false)
    private String tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaCorrente conta1 = (ContaCorrente) o;
        return agencia == conta1.agencia &&
                conta == conta1.conta &&
                Objects.equals(id, conta1.id) &&
                Objects.equals(saldo, conta1.saldo) &&
                Objects.equals(limite, conta1.limite) &&
                Objects.equals(tipo, conta1.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, agencia, conta, saldo, limite, tipo);
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "id=" + id +
                ", agencia=" + agencia +
                ", conta=" + conta +
                ", saldo=" + saldo +
                ", limite=" + limite +
                ", tipo='" + tipo + '\'' +
                '}';
    }

}
