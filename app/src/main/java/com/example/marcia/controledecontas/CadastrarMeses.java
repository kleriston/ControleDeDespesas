package com.example.marcia.controledecontas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcia on 10/11/2016.
 */
public class CadastrarMeses extends AppCompatActivity {

    RadioButton janeiro;
    RadioButton fevereiro;
    RadioButton marco;
    RadioButton abril;
    RadioButton maio;
    RadioButton junho;
    RadioButton julho;
    RadioButton agosto;
    RadioButton setembro;
    RadioButton outubro;
    RadioButton novembro;
    RadioButton desembro;

    Button cadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrarmeses);
        janeiro = (RadioButton) findViewById(R.id.janeiroId);
        fevereiro = (RadioButton) findViewById(R.id.fevereiroId);
        marco = (RadioButton) findViewById(R.id.marcoId);
        abril = (RadioButton) findViewById(R.id.abrilId);
        maio = (RadioButton) findViewById(R.id.maioId);
        junho = (RadioButton) findViewById(R.id.junhoId);
        julho  = (RadioButton) findViewById(R.id.julhoId);
       agosto = (RadioButton) findViewById(R.id.agostoId);
        setembro = (RadioButton) findViewById(R.id.setembroId);
       outubro = (RadioButton) findViewById(R.id.outubroId);
        novembro = (RadioButton) findViewById(R.id.novembroId);
        desembro = (RadioButton) findViewById(R.id.desembroId);

        cadastrar = (Button) findViewById(R.id.idCad);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (janeiro.isChecked()){
                    Mes m = new Mes("Janeiro", 1,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +janeiro.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();



                }
                if(fevereiro.isChecked()){
                    Mes m = new Mes("Fevereiro", 2,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +fevereiro.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if(marco.isChecked()){
                    Mes m = new Mes("Março", 3,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +marco.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if(abril.isChecked()){
                    Mes m = new Mes("Abril", 4,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +abril.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if (maio.isChecked()){
                    Mes m = new Mes("Maio", 5,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +maio.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if(junho.isChecked()){
                    Mes m = new Mes("Junho", 6,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +junho.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if (julho.isChecked()){
                    Mes m = new Mes("Julho", 7,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +julho.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if (agosto.isChecked()){
                    Mes m = new Mes("Agosto", 8,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +agosto.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if (setembro.isChecked()){
                    Mes m = new Mes("Setembro", 9,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +setembro.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if (outubro.isChecked()){
                    Mes m = new Mes("Outubro", 10,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +outubro.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if (novembro.isChecked()){



                    Mes m = new Mes("Novembro", 11,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +novembro.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }
                if (desembro.isChecked()){


                    Mes m = new Mes("Dezembro", 12,null);
                    m.save();
                    Toast.makeText(getApplicationContext(), "Mês de " +desembro.getText()+" Cadastrado.", Toast.LENGTH_SHORT).show();


                }

            }
        });

    }
}
