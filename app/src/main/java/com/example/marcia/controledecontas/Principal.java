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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Principal extends AppCompatActivity {

    Button despesa;
    TextView mostraMes;
    Button listaDisp;
    Button deletar;
    Button cadastrarMes;
    Button listarMeses;
    private  AlertDialog alerta;
    private List<Despesas> listaTotal;
    Iterator<Despesas> filmes = Despesas.findAll(Despesas.class);

    int mes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        despesa = (Button) findViewById(R.id.cadaDesp);
        mostraMes = (TextView) findViewById(R.id.mesId);

        listaDisp = (Button) findViewById(R.id.buttonDisp);
        deletar = (Button) findViewById(R.id.cadaM);
        cadastrarMes = (Button) findViewById(R.id.buttonMes);
        listarMeses = (Button) findViewById(R.id.buttonListarMes);

        listaTotal = new ArrayList<>();

        final Calendar cal = Calendar.getInstance();




        cadastrarMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, CadastrarMeses.class);
                startActivity(i);
            }
        });
        listarMeses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Principal.this, ListarMeses.class);
                startActivity(i);
            }
        });

        mes = cal.get(Calendar.MONTH);
        mes = mes+1;



        verificaPendencia();
        verificaMes(mes);


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

    public void verificaMes(int mes){
        if(mes == 1){
        mostraMes.setText("Controle De Janeiro");
            mostraMes.setTextColor(getResources().getColor(R.color.red));
        }else if(mes == 2){
            mostraMes.setText("Controle De Fevereiro");
            mostraMes.setTextColor(getResources().getColor(R.color.yellow));
        }else if(mes == 3){
            mostraMes.setText("Controle De Março");
            mostraMes.setTextColor(getResources().getColor(R.color.siena1));
        }else if(mes == 4){
            mostraMes.setText("Controle De Abriu");
            mostraMes.setTextColor(getResources().getColor(R.color.colorAccent));
        }else if(mes == 5){
            mostraMes.setText("Controle De Maio");
            mostraMes.setTextColor(getResources().getColor(R.color.lightgreen));
        }else if(mes == 6){
            mostraMes.setText("Controle De Junho");
            mostraMes.setTextColor(getResources().getColor(R.color.gold2));
        }else if(mes == 7){
            mostraMes.setText("Controle De Julho");
            mostraMes.setTextColor(getResources().getColor(R.color.dodgerBlue));
        }else if(mes == 8){
            mostraMes.setText("Controle De Agosto");
            mostraMes.setTextColor(getResources().getColor(R.color.dark));
        }else if(mes == 9){
            mostraMes.setText("Controle De Setembro");
            mostraMes.setTextColor(getResources().getColor(R.color.common_signin_btn_text_light));
        }else if(mes == 10){
            mostraMes.setText("Controle De Outubro");
            mostraMes.setTextColor(getResources().getColor(R.color.red));
        }else if(mes == 11){
            mostraMes.setText("Controle De Novembro");
            mostraMes.setTextColor(getResources().getColor(R.color.siena1));
        }else if(mes == 12){
            mostraMes.setText("Controle De Dezembro");
            mostraMes.setTextColor(getResources().getColor(R.color.dodgerBlue));
        }



    }

}
