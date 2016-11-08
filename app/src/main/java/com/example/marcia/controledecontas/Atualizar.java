package com.example.marcia.controledecontas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Marcia on 27/10/2016.
 */
public class Atualizar extends ActionBarActivity{

    EditText desp;

    EditText valor;
    private double valor1=0;
    RadioButton pendente;
    RadioButton ok;
    String dt;


    private List<Despesas> listaDespesas;
    private Button altera;
    private Button btn;
    int ano, mes, dia;
    static  final int DIALOG_ID = 0;
    private TextView mostra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atualizar);
        mostra = (TextView) findViewById(R.id.mostraData);

        final Calendar cal = Calendar.getInstance();

        showDialogOnButtonClick();

        ano = cal.get(Calendar.YEAR);

        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        Intent i = getIntent();

        final int u = Integer.parseInt( i.getSerializableExtra("posicao").toString());
        altera = (Button) findViewById(R.id.idAt);



        desp = (EditText) findViewById(R.id.dispID);

        valor = (EditText) findViewById(R.id.editText3);
        pendente = (RadioButton) findViewById(R.id.radioButtonPendente);
        ok = (RadioButton) findViewById(R.id.radioButtonOk);


        Iterator<Despesas> filmes = Despesas.findAll(Despesas.class);

        listaDespesas = new ArrayList<>();
        int c =0;
        while (filmes.hasNext()){

            Despesas d = filmes.next();

            listaDespesas.add(d);


        }
        for (int ii=0; ii< listaDespesas.size();ii++){

            if(listaDespesas.get(ii).getId() == u){
                desp.setText(listaDespesas.get(ii).getDespesa());

                valor.setText(""+listaDespesas.get(ii).getValor());

                mostra.setText(listaDespesas.get(ii).getData_vencimento());
            }
        }



        altera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pendente.isChecked()){
                    valor1 = Double.parseDouble(valor.getText().toString());
                    Despesas des = Despesas.findById(Despesas.class, u);

                    des.setDespesa(desp.getText().toString());
                    des.setData_vencimento(dt );
                    des.setValor(valor1);
                    des.setStatus("Pendente");
                    des.save();
                    Toast.makeText(getApplicationContext(), "Despesa Atualizada Com Sucesso", Toast.LENGTH_SHORT).show();
                    listaDespesas = new ArrayList<Despesas>();
                    valor.setText("");
                    desp.setText("");
                    mostra.setText("");

                }
                if(ok.isChecked()){
                    valor1 = Double.parseDouble(valor.getText().toString());
                    Despesas des = Despesas.findById(Despesas.class, u);

                    des.setDespesa(desp.getText().toString());
                    des.setData_vencimento(dt );
                    des.setValor(valor1);
                    des.setStatus("OK");
                    des.save();
                    Toast.makeText(getApplicationContext(), "Despesa Atualizada!", Toast.LENGTH_SHORT).show();
                    listaDespesas = new ArrayList<Despesas>();
                    valor.setText("");
                    desp.setText("");
                    mostra.setText("");

                }

                Intent i = new Intent(Atualizar.this, ListarDespesas.class);
                startActivity(i);

            }
        });



    }

    public  void showDialogOnButtonClick(){
        btn = (Button) findViewById(R.id.dial);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){

        if(id == DIALOG_ID){
            return new DatePickerDialog(this, dpickerListener, ano, mes, dia);
        }else{
            return null;
        }
    }
    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear +1;
            dia = dayOfMonth;

            if(dia <10){
                dt =  "0"+dia+"/"+mes+"/"+ano;
            }else {
                dt = +dia+"/"+mes+"/"+ano;
            }
            mostra.setText(dt);

        }

    };


    }
