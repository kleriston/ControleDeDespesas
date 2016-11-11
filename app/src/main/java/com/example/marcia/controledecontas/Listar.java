package com.example.marcia.controledecontas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        List<Despesas> l = new ArrayList<>();

        for (int j=0; j<listaMeses.size();j++){



            l.add((Despesas) listaMeses.get(j).despe);

        }








        adpMes = new ArrayAdapter<Despesas>(this,android.R.layout.simple_list_item_checked);
        adpMes.addAll(l);
        listaDeDespesas.setAdapter(adpMes);


    }
}
