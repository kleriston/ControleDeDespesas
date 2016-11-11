package com.example.marcia.controledecontas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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

    private ArrayAdapter<Despesas> adpDespesas;



    private List<Despesas> listaDespesas;

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

        Iterator<Despesas> despesas = Despesas.findAll(Despesas.class);
        adpDespesas = new ArrayAdapter<Despesas>(this, android.R.layout.simple_list_item_checked);

        listaDespesas = new ArrayList<>();

        while (despesas.hasNext()) {
            Despesas d = despesas.next();
            listaDespesas.add(d);
        }


        Iterator<Mes> m = Mes.findAll(Mes.class);





            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    List<Despesas> teste= new ArrayList<>();
                    Mes person = new Mes();
                    person = Mes.findById(Mes.class, listaMeses.get(position).getId());
                    for (int i=0; i<listaDespesas.size();i++){
                        for(int  j=0; j<listaMeses.size();j++){
                            if( listaDespesas.get(i).getMes() ==listaMeses.get(j).getNumero()){
                                teste.add(listaDespesas.get(i));
                                person.getDespe().add(listaDespesas.get(i));
                                person.setDespe(listaMeses.get(j).getDespe());


                                person.save();
                        }




                        }

                    }
                    String posicao;
                    Intent i = new Intent(ListarMeses.this, Listar.class);
                    i.putExtra("posicao", listaMeses.get(position).getId().toString());
                    startActivity(i);
                }
            });

    }
}
