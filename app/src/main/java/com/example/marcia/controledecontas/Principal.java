package com.example.marcia.controledecontas;

import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Principal extends AppCompatActivity {

    Button despesa;
    Button mes;
    Button listaDisp;
    Button deletar;
    private  AlertDialog alerta;
    private List<Despesas> listaTotal;
    Iterator<Despesas> filmes = Despesas.findAll(Despesas.class);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        despesa = (Button) findViewById(R.id.cadaDesp);
        mes = (Button) findViewById(R.id.cadaM);
        listaDisp = (Button) findViewById(R.id.buttonDisp);
        deletar = (Button) findViewById(R.id.cadaM);

        listaTotal = new ArrayList<>();

        verificaPendencia();


        despesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, CadastrarDespesas.class);
                startActivity(i);
            }
        });
        listaDisp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, ListarDespesas.class);
                startActivity(i);
            }
        });
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               AlertDialog.Builder alerta = new AlertDialog.Builder(Principal.this);
                alerta.setTitle("Dicas");
                alerta.setIcon(R.mipmap.deletar);
                alerta.setMessage("Deseja Realmente Excluir?");
                alerta.setCancelable(true);
                alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

              alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      Despesas d = new Despesas();

                      d.deleteAll(Despesas.class);
                      Toast.makeText(getApplicationContext(), "Despesas Excluidas", Toast.LENGTH_SHORT).show();
                  }
              });

                AlertDialog alertDialog = alerta.create();
                alertDialog.show();


            }
        });
    }


    public void verificaPendencia(){
        while (filmes.hasNext()){
            Despesas d = filmes.next();
            listaTotal.add(d);


        }

        for(int i=0;i<listaTotal.size();i++){

            if(listaTotal.get(i).getStatus().equals("Pendente")){
                Toast.makeText(getApplicationContext(), "Atenção Você Ainda Tem Despesas Pendentes", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

}
