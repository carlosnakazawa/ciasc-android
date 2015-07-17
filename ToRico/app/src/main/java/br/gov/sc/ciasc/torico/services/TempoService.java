package br.gov.sc.ciasc.torico.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

public class TempoService extends Service implements Runnable {

    private Handler myHandler = new Handler();
    private long marcadorTempo;
    private long tempoTrabalhado;

    private final IBinder conexao = new LocalBinder();

    public TempoService() {
        tempoTrabalhado = 0;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return conexao;
    }

    public void start() {
        marcadorTempo = System.nanoTime();
        myHandler.post(this);
    }

    public void pause() {
        myHandler.removeCallbacks(this);
    }

    public void stop() {

    }

    @Override
    public void run() {
        myHandler.postAtTime(this, SystemClock.uptimeMillis() + 1000);
        tempoTrabalhado = (System.nanoTime() - marcadorTempo) / 1000000000;
        Log.d("TempoService", "Tempo trabalhado: " + tempoTrabalhado);
    }

    public class LocalBinder extends Binder {
        public TempoService getTempoService() {
            return TempoService.this;
        }
    }
}
