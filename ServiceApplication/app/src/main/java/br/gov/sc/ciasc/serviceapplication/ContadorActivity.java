package br.gov.sc.ciasc.serviceapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;


public class ContadorActivity extends Activity implements ServiceConnection {

    private ContadorComConexaoService contadorComConexao;

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
                bindService(new Intent(ContadorActivity.this, ContadorComConexaoService.class), conexao, BIND_AUTO_CREATE);
            }
        });

        Button parar = (Button) findViewById(R.id.parar);
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conexao);
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ContadorComConexaoService.LocalBinder localBinder = (ContadorComConexaoService.LocalBinder) service;
        contadorComConexao = localBinder.getContador();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        contadorComConexao = null;

    }
}
