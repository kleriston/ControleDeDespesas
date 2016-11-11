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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    Date data;
    Date da;

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
        DateFormat format  = DateFormat.getDateInstance(DateFormat.MEDIUM);



        for (int ii=0; ii< listaDespesas.size();ii++){

            if(listaDespesas.get(ii).getId() == u){
                desp.setText(listaDespesas.get(ii).getDespesa());
                da=  listaDespesas.get(ii).data_vencimento;
                dt = format.format(da);
                mostra.setText(dt);
                valor.setText(""+listaDespesas.get(ii).getValor());

            }
        }



        altera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor1 = Double.parseDouble(valor.getText().toString());
                Despesas des = Despesas.findById(Despesas.class, u);

                des.setDespesa(desp.getText().toString());
                des.setData_vencimento(data );
                des.setValor(valor1);

                if(pendente.isChecked()){


                    des.setDespesa(desp.getText().toString());
                des.setData_vencimento(data );
                    des.setValor(valor1);

                    des.setStatus("Pendente");
                    des.save();
                    Toast.makeText(getApplicationContext(), "Despesa Atualizada.", Toast.LENGTH_SHORT).show();



                }
                if(ok.isChecked()){


                    des.setDespesa(desp.getText().toString());
                   des.setData_vencimento(data );
                    des.setValor(valor1);

                    des.setStatus("OK");

                    des.save();
                    Toast.makeText(getApplicationContext(), "Despesa Atualizada.", Toast.LENGTH_SHORT).show();


                }

                des.save();
                valor.setText("");
                desp.setText("");
                mostra.setText("");
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
            mes = monthOfYear;
            dia = dayOfMonth;


            Calendar c = Calendar.getInstance();

            c.set(ano, mes, dia);

            data = c.getTime();



            DateFormat format  = DateFormat.getDateInstance(DateFormat.MEDIUM);

            dt = format.format(data);

            mostra.setText(dt);

        

        }

    };


    }
