package com.sicred.receita.model;

import lombok.Data;

@Data
public class Conta  {

    private String agencia;
    private String conta;
    private double saldo;
    private String status;
    private String resultado;

    public Conta(String[] cols) {
       this.agencia = cols[0];
       this.conta = cols[1].replace("-","");
       this.saldo = Double.parseDouble(cols[2].replace(",", "."));
       this.status = cols[3];
    }

    public Conta() { }

}
