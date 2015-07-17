package br.gov.sc.ciasc.torico.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import java.util.Date;

public class TempoService extends Service implements Runnable {

    private Handler myHandler = new Handler();
    private long marcadorTempo;
    private long tempoTrabalhado;
    private long tempoAcumulado;
    private Handler atualizaHandler;
    public boolean rodando;

    private final IBinder conexao = new LocalBinder();


    public TempoService() {
        tempoTrabalhado = 0;
        tempoAcumulado = 0;
        rodando = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return conexao;
    }

    public void start() {
        marcadorTempo = System.nanoTime();
        myHandler.post(this);
        rodando = true;
    }

    public void pause() {
        myHandler.removeCallbacks(this);
        tempoAcumulado += tempoTrabalhado;
        rodando = false;
    }

    public void stop() {
        myHandler.removeCallbacks(this);
        tempoAcumulado = 0;
        tempoTrabalhado = 0;
    }

    @Override
    public void run() {
        myHandler.postAtTime(this, SystemClock.uptimeMillis() + 1000);
        tempoTrabalhado = (System.nanoTime() - marcadorTempo) / 1000000;
        Log.d("TempoService", "Tempo trabalhado: " + tempoTrabalhado);
        Log.d("TempoService", "Tempo acumulado : " + tempoAcumulado);
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putLong("Tempo", tempoAcumulado + tempoTrabalhado);
        msg.setData(bundle);
        atualizaHandler.sendMessage(msg);
    }

    public class LocalBinder extends Binder {
        public TempoService getTempoService() {
            return TempoService.this;
        }
    }

    public void setAtualizaHandler(Handler handler) {
        this.atualizaHandler = handler;
    }
}
