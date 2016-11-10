package com.example.marcia.controledecontas;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.IllegalFormatException;

/**
 * Created by Marcia on 25/10/2016.
 */
public class CadastrarDespesas extends AppCompatActivity {


    EditText disp;

    EditText valor;
    Button cadastrar;
    double valor1 = 0;
    RadioButton pendente;
    RadioButton ok;

    String dt = "";
    private Button btn;
    private TextView mostra;
    int ano, mes, dia;
    static  final int DIALOG_ID = 0;
    Date data;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrardespesas);
        mostra = (TextView) findViewById(R.id.mostraData);
        disp = (EditText) findViewById(R.id.dispID);

        valor = (EditText) findViewById(R.id.editText3);
        pendente = (RadioButton) findViewById(R.id.radioButtonPendente);
        ok = (RadioButton) findViewById(R.id.radioButtonOk);


        final Calendar cal = Calendar.getInstance();

        showDialogOnButtonClick();

        ano = cal.get(Calendar.YEAR);

        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);




        cadastrar = (Button) findViewById(R.id.idCad);


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    if (pendente.isChecked()) {
                        valor1 = Double.parseDouble(valor.getText().toString());


                        Despesas d = new Despesas(disp.getText().toString(), valor1, data, pendente.getText().toString());


                        d.save();



                    }
                    if (ok.isChecked()) {
                        valor1 = Double.parseDouble(valor.getText().toString());

                        Despesas d = new Despesas(disp.getText().toString(), valor1, data, ok.getText().toString());

                        d.save();

                    }


                    Toast.makeText(getApplicationContext(), "Despesa Cadastrada Com Sucesso", Toast.LENGTH_SHORT).show();
                } catch (IllegalFormatException e) {
                    Toast.makeText(getApplicationContext(), "Opa! Erro ao Cadatrar", Toast.LENGTH_SHORT).show();


                }

                disp.setText("");
                mostra.setText("");
                valor.setText("");
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


