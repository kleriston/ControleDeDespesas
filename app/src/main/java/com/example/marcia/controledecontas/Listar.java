package com.example.marcia.controledecontas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Marcia on 11/11/2016.
 */
public class Listar extends AppCompatActivity {

    ListView listDespesasOK;
    ListView listDespesasPendentes;
    private ArrayAdapter<Despesas> adpDespesasOK;
    private ArrayAdapter<Despesas> adpDespesasPendentes;

    TextView tot;
    TextView pen;


    private List<Despesas> listaDespesasOK;
    private List<Despesas> listaDespesasPendentes;

    private List<Despesas> listadespesas;

    private double total =0, pendente = 0;

    String dt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar);
        listDespesasOK =(ListView) findViewById(R.id.listViewListDM);
        listDespesasPendentes = (ListView) findViewById(R.id.listViewPendentesId);

        Intent i = getIntent();
        final int u = Integer.parseInt( i.getSerializableExtra("posicao").toString());

        Iterator<Mes> m = Mes.findAll(Mes.class);
        Iterator<Despesas> despesas = Despesas.findAll(Despesas.class);
        adpDespesasOK = new ArrayAdapter<Despesas>(this,android.R.layout.simple_list_item_checked);
        adpDespesasPendentes = new ArrayAdapter<Despesas>(this, android.R.layout.simple_list_item_1);
        tot = (TextView) findViewById(R.id.textViewTotal) ;
        pen = (TextView) findViewById(R.id.textViewPendente);

        listaDespesasOK = new ArrayList<>();
        listaDespesasPendentes = new ArrayList<>();
        listadespesas = new ArrayList<>();

        while (despesas.hasNext()) {
            Despesas des = despesas.next();
            listadespesas.add(des);

        }

      for (int t=0; t<listadespesas.size();t++){

          if (listadespesas.get(t).getMesReferente().getId() == u){
              total = total + listadespesas.get(t).getValor();
          if (listadespesas.get(t).getStatus().equals("OK")){
              listaDespesasOK.add(listadespesas.get(t));
          }
              if (listadespesas.get(t).getStatus().equals("Pendente")){
                  listaDespesasPendentes.add(listadespesas.get(t));
                  pendente = pendente + listadespesas.get(t).getValor();
              }

          }
      }

        tot.setText("Total: R$ " + formato(total));
        tot.setTextColor(getResources().getColor(R.color.red));
        pen.setText("Pendente: R$ " + formato(pendente));
        pen.setTextColor(getResources().getColor(R.color.dark));





        adpDespesasPendentes.addAll(listaDespesasPendentes);

        adpDespesasOK.addAll(listaDespesasOK);

        listDespesasOK.setAdapter(adpDespesasOK);
        listDespesasPendentes.setAdapter(adpDespesasPendentes);

        listDespesasOK.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                DateFormat format  = DateFormat.getDateInstance(DateFormat.MEDIUM);

                Date da=  listaDespesasOK.get(position).getData_vencimento();
                dt = format.format(da);

                String posicao;

                AlertDialog.Builder alerta = new AlertDialog.Builder(Listar.this);
                alerta.setTitle(listaDespesasOK.get(position).getDespesa());
                alerta.setIcon(R.mipmap.lupa);
                alerta.setMessage("Despesa: " +listaDespesasOK.get(position).getDespesa() + "\n\nData: " + dt+
                        "\n\nValor: R$ " + formato(listaDespesasOK.get(position).getValor()) + "\n\nSituação: " + listaDespesasOK.get(position).getStatus());
                alerta.setCancelable(true);
                alerta.setNegativeButton("Atualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(Listar.this, Atualizar.class);

                        j.putExtra("posicao", listaDespesasOK.get(position).getId().toString());
                        startActivity(j);
                    }
                });



                alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder alertaDelete = new AlertDialog.Builder(Listar.this);
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

                                Despesas    desp= Despesas.findById(Despesas.class, listaDespesasOK.get(position).getId());
                                desp.delete();
                                Toast.makeText(getApplicationContext(), "Despesa Deletada.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Listar.this, ListarMeses.class);
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


        listDespesasPendentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                DateFormat format  = DateFormat.getDateInstance(DateFormat.MEDIUM);

                Date da=  listaDespesasPendentes.get(position).getData_vencimento();
                dt = format.format(da);
                String posicao;

                AlertDialog.Builder alerta = new AlertDialog.Builder(Listar.this);
                alerta.setTitle(listaDespesasPendentes.get(position).getDespesa());
                alerta.setIcon(R.mipmap.lupa);
                alerta.setMessage("Despesa: " +listaDespesasPendentes.get(position).getDespesa() + "\n\nData: " + dt+
                        "\n\nValor: R$ " + formato(listaDespesasPendentes.get(position).getValor()) + "\n\nSituação: " + listaDespesasPendentes.get(position).getStatus());
                alerta.setCancelable(true);
                alerta.setNegativeButton("Atualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j = new Intent(Listar.this, Atualizar.class);

                        j.putExtra("posicao", listaDespesasPendentes.get(position).getId().toString());
                        startActivity(j);
                    }
                });



                alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder alertaDelete = new AlertDialog.Builder(Listar.this);
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

                                Despesas    desp= Despesas.findById(Despesas.class, listaDespesasPendentes.get(position).getId());
                                desp.delete();
                                Toast.makeText(getApplicationContext(), "Despesa Deletada.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Listar.this, ListarMeses.class);
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
