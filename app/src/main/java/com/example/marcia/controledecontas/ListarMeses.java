package com.example.marcia.controledecontas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Button botaoDeletar;
    int  id = 0;
    Mes person = new Mes();
    List<Mes> teste = new ArrayList<>();
    private  AlertDialog alerta;


    private List<Despesas> listaDespesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listarmeses);
        list = (ListView) findViewById(R.id.listViewMes);

        botaoDeletar = (Button) findViewById(R.id.buttonExcluirMes);


        Iterator<Mes> filmes = Mes.findAll(Mes.class);
        adpMes = new ArrayAdapter<Mes>(this, android.R.layout.simple_list_item_1);
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


                String posicao;

                Intent i = new Intent(ListarMeses.this, Listar.class);
                i.putExtra("posicao", listaMeses.get(position).getId().toString());
                startActivity(i);
            }
        });




        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {



                teste.add(listaMeses.get(position));



                botaoDeletar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListarMeses.this);
                        alerta.setTitle("Dicas");
                        alerta.setIcon(R.mipmap.deletar);
                        alerta.setMessage("Quer Realmente Excluir?");
                        alerta.setCancelable(true);
                        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                for(int i=0;i<teste.size();i++){

                                    person = Mes.findById(Mes.class, teste.get(i).getId());
                                    person.delete();
                                }
                                Toast.makeText(getApplicationContext(), "Mes/Meses Deletador.", Toast.LENGTH_SHORT).show();

                            }
                        });
                        AlertDialog alertaDialog = alerta.create();
                        alertaDialog.show();
                    }
                });
                view.setBackgroundColor(getResources().getColor(R.color.dark));
                view.setSelected(true);
                View v = view;

                android.widget.PopupMenu popup = new android.widget.PopupMenu(ListarMeses.this, v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_principal, popup.getMenu());
                popup.show();
                return true;
            }
        });

    }

}
