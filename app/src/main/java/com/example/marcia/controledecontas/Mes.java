package com.example.marcia.controledecontas;

import com.orm.SugarRecord;

/**
 * Created by Marcia on 25/10/2016.
 */
public class Mes extends SugarRecord {

    String nome;
    int numero;

    public Mes() {
    }

    public Mes(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
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

    public String toString() {
        return  this.getNome();
    }
}
