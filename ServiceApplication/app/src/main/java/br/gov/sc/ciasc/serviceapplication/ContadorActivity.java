package br.gov.sc.ciasc.serviceapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ContadorActivity extends Activity implements ServiceConnection {

    private ContadorService contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        final Intent intent = new Intent(this, ContadorService.class);

        final ServiceConnection conexao = this;

        Button iniciar = (Button) findViewById(R.id.iniciar);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(ContadorActivity.this, ContadorService.class), conexao, BIND_AUTO_CREATE);
            }
        });

        Button parar = (Button) findViewById(R.id.parar);
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conexao);
            }
        });

        Button visualizar = (Button) findViewById(R.id.visualizar);
        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = contador.getContador();
                Toast.makeText(ContadorActivity.this, "Contagem: " + count, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ContadorService.LocalBinder localBinder = (ContadorService.LocalBinder) service;
        contador = localBinder.getContador();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        contador = null;

    }
}
