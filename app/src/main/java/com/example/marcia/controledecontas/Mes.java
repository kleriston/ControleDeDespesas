package com.example.marcia.controledecontas;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Marcia on 25/10/2016.
 */
public class Mes extends SugarRecord {

    String nome;
    int numero;

    Despesas [] listaDespesas = new Despesas[30];


    public Mes() {
    }

    public Mes(String nome,  int numero, Despesas[] listaDespesas) {
        this.nome = nome;

        this.numero = numero;
        this.listaDespesas = listaDespesas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Despesas[] getListaDespesas() {
        if (listaDespesas == null){
            listaDespesas = new Despesas[30];
        }
        return listaDespesas;
    }

    public void setListaDespesas(Despesas[] listaDespesas) {
        this.listaDespesas = listaDespesas;
    }

    public String toString() {

        return this.getNome();
    }
}
