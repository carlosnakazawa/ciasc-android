package br.gov.sc.ciasc.serviceapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
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
                contador.iniciarContagem();
            }
        });

        Button parar = (Button) findViewById(R.id.parar);
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador.pararContagem();
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
    protected void onResume() {
        super.onResume();
        bindService(new Intent(ContadorActivity.this, ContadorService.class), this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
        Log.d("ContadorActivity", "onPause Service");
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("ContadorActivity", "onServiceConnected");
        ContadorService.LocalBinder localBinder = (ContadorService.LocalBinder) service;
        contador = localBinder.getContador();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d("ContadorActivity", "onServiceDisconnected");
        contador = null;

    }
}
