package br.gov.sc.ciasc.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class ContadorService extends Service implements Runnable {
    protected int contador;
    private static int MAXIMO = 10;
    private Handler myHandler = new Handler();
    private final IBinder conexao = new LocalBinder();

    public ContadorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        contador = 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return conexao;
    }

    public void iniciarContagem() {
        myHandler.post(this);
    }

    public void pararContagem() {
        myHandler.removeCallbacks(this);
    }

    @Override
    public void run() {
        if(contador < MAXIMO) {
            myHandler.postAtTime(this, SystemClock.uptimeMillis() + 1000);
            Log.d("ContadorService", "Contagem : " + contador);
            contador++;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ContadorService", "onDestroy");
        pararContagem();
    }

    public int getContador() {
        return contador;
    }

    public class LocalBinder extends Binder {
        public ContadorService getContador() {
            return ContadorService.this;
        }
    }
}
