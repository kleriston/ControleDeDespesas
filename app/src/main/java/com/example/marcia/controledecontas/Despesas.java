package com.example.marcia.controledecontas;

import android.renderscript.Sampler;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Marcia on 25/10/2016.
 */
public class Despesas extends SugarRecord {

    String despesa;
    double valor;

    Date data_vencimento;
    String status;

    public Despesas() {
    }

    public Despesas(String despesa, double valor, Date data_vencimento, String status) {
        this.despesa = despesa;
        this.valor = valor;
        this.data_vencimento = data_vencimento;
        this.status = status;
    }

    public String getDespesa() {
        return despesa;
    }

    public void setDespesa(String despesa) {
        this.despesa = despesa;
    }

    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return  this.getDespesa();
    }
}
