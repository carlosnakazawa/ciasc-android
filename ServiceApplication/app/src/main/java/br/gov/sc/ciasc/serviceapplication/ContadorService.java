package br.gov.sc.ciasc.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ContadorService extends Service implements Runnable {
    private boolean ativo;
    private int contador;
    private static int MAXIMO = 10;

    public ContadorService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ativo = true;
        contador = 0;
        new Thread(this).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void run() {
        while(ativo && contador < MAXIMO) {
            try {
                Log.d("ContadorService", "Contagem : " + contador);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador++;
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
