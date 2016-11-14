package com.example.marcia.controledecontas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Marcia on 11/11/2016.
 */
public class Listar extends AppCompatActivity {

ListView listaDeDespesas;
    private ArrayAdapter<Despesas> adpMes;
    private List<Mes> listaMeses;
    private List<Despesas> listad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);
        listaDeDespesas =(ListView) findViewById(R.id.listViewListDM);

        Intent i = getIntent();
        final int u = Integer.parseInt( i.getSerializableExtra("posicao").toString());

        Iterator<Mes> m = Mes.findAll(Mes.class);
        Iterator<Despesas> de = Despesas.findAll(Despesas.class);


        listaMeses = new ArrayList<>();
        listad = new ArrayList<>();

        while (de.hasNext()) {
            Despesas des = de.next();
            listad.add(des);

        }
        while (m.hasNext()) {
            Mes d = m.next();

            listaMeses.add(d);

        }

       Despesas [] teste = new Despesas[30];
        Mes me = Mes.findById(Mes.class, u);




        for (int t =0; t< teste.length;t++){

            if (teste[t] == null){
                teste[t] = me.listaDespesas[t];
            }
        }







        adpMes = new ArrayAdapter<Despesas>(this,android.R.layout.simple_list_item_checked);

        adpMes.addAll(teste);
        listaDeDespesas.setAdapter(adpMes);


    }
}
