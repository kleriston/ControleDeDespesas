package com.example.marcia.controledecontas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Marcia on 10/11/2016.
 */
public class ListarMeses extends AppCompatActivity {

    ListView list;

    private ArrayAdapter<Mes> adpMes;



    private List<Mes> listaMeses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listarmeses);
        list = (ListView) findViewById(R.id.listViewMes);



        Iterator<Mes> filmes = Mes.findAll(Mes.class);
        adpMes = new ArrayAdapter<Mes>(this,android.R.layout.simple_list_item_1);
        listaMeses = new ArrayList<>();

        while (filmes.hasNext()) {
            Mes d = filmes.next();
            listaMeses.add(d);

        }
        adpMes.addAll(listaMeses);
        list.setAdapter(adpMes);
    }
}
