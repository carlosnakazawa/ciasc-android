package br.gov.sc.ciasc.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class ContadorService extends Service implements Runnable {
    private boolean ativo;
    protected int contador;
    private static int MAXIMO = 10;
    private Handler myHandler = new Handler();

    public ContadorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ativo = true;
        contador = 0;
        myHandler.post(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
        if(ativo && contador < MAXIMO) {
            myHandler.postAtTime(this, SystemClock.uptimeMillis() + 1000);
            Log.d("ContadorService", "Contagem : " + contador);
            contador++;
            return;
        }
        stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ContadorService", "onDestroy");
        ativo = false;
    }
}
