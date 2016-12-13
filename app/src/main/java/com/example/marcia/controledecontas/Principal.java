package com.example.marcia.controledecontas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Principal extends AppCompatActivity implements ActionMenuView.OnMenuItemClickListener, PopupMenu.OnMenuItemClickListener {





    private List<Despesas> listaTotal;
    Iterator<Despesas> filmes = Despesas.findAll(Despesas.class);

    ListView list;
    TextView mostrar;

    private ArrayAdapter<Mes> adpMes;

    private List<Mes> listaMeses;

    private ArrayAdapter<Despesas> adpDespesas;


    int  itens = 0;
    Mes person = new Mes();
    List<Mes> teste = new ArrayList<>();



    private List<Despesas> listaDespesas;
    private  AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        Iterator<Mes> filmes = Mes.findAll(Mes.class);
        adpMes = new ArrayAdapter<Mes>(this, android.R.layout.simple_list_item_1);
        listaMeses = new ArrayList<>();
        list = (ListView) findViewById(R.id.listMesPrincipal);
        mostrar = (TextView) findViewById(R.id.mostraQuantidade);



        while (filmes.hasNext()) {
            Mes d = filmes.next();
            listaMeses.add(d);

        }

        if (listaMeses == null){
            listaMeses = new ArrayList<>();
        }
        adpMes.addAll(listaMeses);
        if (adpMes == null){
            adpMes =  new ArrayAdapter<Mes>(this, android.R.layout.simple_list_item_1);
        }
        if (list == null){

        }
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
                Intent i = new Intent(Principal.this, Listar.class);
                i.putExtra("posicao", listaMeses.get(position).getId().toString());
                startActivity(i);
            }
        });


        verificaDependencia();


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {

                teste.add(listaMeses.get(position));
                itens+=1;
                if (itens==1){
                    mostrar.setText(""+itens +" Mês Selecionado");
                }else {
                    mostrar.setText(" "+itens +" Meses Selecionados");
                }

                view.setBackgroundColor(getResources().getColor(R.color.dark));
                view.setSelected(true);

                android.widget.PopupMenu popup = new android.widget.PopupMenu(Principal.this, list);
                popup.setOnMenuItemClickListener(Principal.this);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_mes, popup.getMenu());
                popup.show();
                return true;

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cadastrar_mes:
                Intent j = new Intent(Principal.this, CadastrarMeses.class);

                startActivity(j);

                return true;
            case R.id.cadatrar_Despesa:
                Intent i = new Intent(Principal.this, CadastrarDespesas.class);

                startActivity(i);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showPopUp(View v){

        android.widget.PopupMenu popup = new android.widget.PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_principal, popup.getMenu());
        popup.show();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cadastrar_mes:
                Intent j = new Intent(Principal.this, CadastrarMeses.class);

                startActivity(j);
                return true;
            case R.id.cadatrar_Despesa:
                Intent i = new Intent(Principal.this, CadastrarDespesas.class);

                startActivity(i);
                return true;

            case R.id.excluir_mes:
                AlertDialog.Builder alerta = new AlertDialog.Builder(Principal.this);
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
                        if (itens == 1){
                            Toast.makeText(getApplicationContext(), "Mes Deletado.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Meses Deletados.", Toast.LENGTH_SHORT).show();
                        }
                        Intent i = new Intent(Principal.this, Principal.class);

                        startActivity(i);

                    }
                });
                AlertDialog alertaDialog = alerta.create();
                alertaDialog.show();



                return true;

        }
        return false;
    }

    public void verificaDependencia(){
        for(int i=0; i< listaDespesas.size();i++){
            if(listaDespesas.get(i).getStatus().equals("Pendente")){
                NotificationCompat.Builder mBuilder =
                        (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                                .setSmallIcon(R.drawable.common_full_open_on_phone)
                                .setContentTitle("Atenção")
                                .setContentText("Você Ainda tem Despesas Pendentes!");
                Intent resultIntent = new Intent(this, ListarDespesas.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addParentStack(Principal.class);

                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                int mId = 0;
                mNotificationManager.notify(mId, mBuilder.build());
                break;
            }
        }

    }
}
