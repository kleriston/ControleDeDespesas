package com.example.marcia.controledecontas;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Marcia on 25/10/2016.
 */
public class ListarDespesas extends AppCompatActivity {


    private ListView listaViewOk;
    private ListView listadepend;
    private  AlertDialog alerta;




    private ArrayAdapter<Despesas> adpOk;
    private ArrayAdapter<Despesas> adpPen;



    private List<Despesas> listaDespesasOk;
    private List<Despesas> getListaDespesasDep;
    private List<Despesas>listaTotal;

    private TextView total;
    private TextView pendencia;
    private double pen;
    private  double tot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listardespesas);




        listaViewOk = (ListView) findViewById(R.id.listViewOK);
        listadepend = (ListView) findViewById(R.id.listViewPend);


       total = (TextView) findViewById(R.id.totalID);
        pendencia = (TextView)findViewById(R.id.pendId);


        Iterator<Despesas> filmes = Despesas.findAll(Despesas.class);
        adpOk = new ArrayAdapter<Despesas>(this, android.R.layout.simple_list_item_checked);
        adpPen = new ArrayAdapter<Despesas>(this, android.R.layout.simple_list_item_1);


        listaDespesasOk = new ArrayList<>();
        getListaDespesasDep = new ArrayList<>();
        listaTotal = new ArrayList<>();


        while (filmes.hasNext()){
            Despesas d = filmes.next();
            listaTotal.add(d);

            if(d.getStatus().equals("OK")){
                listaDespesasOk.add(d);
            }
            if(d.getStatus().equals("Pendente")){

                getListaDespesasDep.add(d);

            }


        }
        for (int i=0; i<listaTotal.size();i++){
            tot+= listaTotal.get(i).getValor();
            if(listaTotal.get(i).getStatus().equals("Pendente")){
                pen+=listaTotal.get(i).getValor();
            }
        }


        adpOk.addAll(listaDespesasOk);
        adpPen.addAll(getListaDespesasDep);
        listadepend.setAdapter(adpPen);
        listaViewOk.setAdapter(adpOk);





        total.setText("Total= R$: " + formato(tot));
        pendencia.setText("Pendente= R$: "+ formato(pen));
        total.setTextColor(getResources().getColor(R.color.colorAccent));
        pendencia.setTextColor(getResources().getColor(R.color.colorPrimary));


        listaViewOk.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                String posicao;

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListarDespesas.this);
                alerta.setTitle(listaDespesasOk.get(position).getDespesa());
                alerta.setIcon(R.mipmap.lupa);
                alerta.setMessage("Despesa: " +listaDespesasOk.get(position).getDespesa() + "\n\nData: " + listaDespesasOk.get(position).data_vencimento+
                "\n\nValor: R$ " + formato(listaDespesasOk.get(position).getValor()) + "\n\nSituação: " + listaDespesasOk.get(position).getStatus());
                alerta.setCancelable(true);
                alerta.setNegativeButton("Atualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(ListarDespesas.this, Atualizar.class);

                        j.putExtra("posicao", listaDespesasOk.get(position).getId().toString());
                        startActivity(j);
                    }
                });



                alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder alertaDelete = new AlertDialog.Builder(ListarDespesas.this);
                        alertaDelete.setTitle("Dicas");
                        alertaDelete.setIcon(R.mipmap.deletar);
                        alertaDelete.setMessage("Quer Realmente Excluir?");
                        alertaDelete.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertaDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            Despesas    desp= Despesas.findById(Despesas.class, listaDespesasOk.get(position).getId());
                                desp.delete();
                                Toast.makeText(getApplicationContext(), "Despesa Deletada!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ListarDespesas.this, ListarDespesas.class);
                                startActivity(i);
                            }
                        });
                        AlertDialog alertaDialogDelete = alertaDelete.create();
                        alertaDelete.show();
                    }
                });
                AlertDialog alertaDialog = alerta.create();
                alertaDialog.show();

            }

        });

        listadepend.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                String posicao;

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListarDespesas.this);
                alerta.setTitle(getListaDespesasDep.get(position).getDespesa());
                alerta.setIcon(R.mipmap.lupa);
                alerta.setMessage("Despesa: " +getListaDespesasDep.get(position).getDespesa() + "\n\nData: " + getListaDespesasDep.get(position).data_vencimento+
                        "\n\nValor: R$ " + formato(getListaDespesasDep.get(position).getValor()) + "\n\nSituação: " + getListaDespesasDep.get(position).getStatus());
                alerta.setCancelable(true);
                alerta.setNegativeButton("Atualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(ListarDespesas.this, Atualizar.class);

                        j.putExtra("posicao", getListaDespesasDep.get(position).getId().toString());
                        startActivity(j);
                    }
                });



                alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder alertaDelete = new AlertDialog.Builder(ListarDespesas.this);
                        alertaDelete.setTitle("Dicas");
                        alertaDelete.setIcon(R.mipmap.deletar);
                        alertaDelete.setMessage("Quer Realmente Excluir?");
                        alertaDelete.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        alertaDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Despesas    desp= Despesas.findById(Despesas.class, getListaDespesasDep.get(position).getId());
                                desp.delete();
                                Toast.makeText(getApplicationContext(), "Despesa Deletada", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(ListarDespesas.this, ListarDespesas.class);
                                startActivity(i);
                            }
                        });
                        AlertDialog alertaDialogDelete = alertaDelete.create();
                        alertaDelete.show();
                    }
                });
                AlertDialog alertaDialog = alerta.create();
                alertaDialog.show();

            }

        });


    }







    public String formato(double valor){
        String retorno = "";
        DecimalFormat formatter = new DecimalFormat("#0.00");
        return retorno = formatter.format(valor);
    }

}
