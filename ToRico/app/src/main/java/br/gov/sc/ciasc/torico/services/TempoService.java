package br.gov.sc.ciasc.torico.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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

        long tempoEmMillis = tempoAcumulado + tempoTrabalhado;
        String tempoFormatado = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(tempoEmMillis), TimeUnit.MILLISECONDS.toMinutes(tempoEmMillis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(tempoEmMillis) % TimeUnit.MINUTES.toSeconds(1));
        Log.d("TempoService", tempoFormatado);
        bundle.putString("TempoFormatado", tempoFormatado);
        bundle.putLong("Tempo", tempoEmMillis);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String salarioBruto = prefs.getString("salario_bruto", "3000");
        String horasMes = prefs.getString("horas_mes", "176");
        String percentualExtra = prefs.getString("percentual_extra", "50");
        double valorHora = Double.parseDouble(salarioBruto) / Double.parseDouble(horasMes);
        Log.d("TempoService", "" + valorHora);
        double valorHoraComAdicional = valorHora * (1+(Double.parseDouble(percentualExtra)/100));
        Log.d("TempoService", "" + valorHoraComAdicional);
        Log.d("TempoService", "" + (tempoAcumulado + tempoTrabalhado));
        double valorGanho = valorHoraComAdicional * (((double)(tempoAcumulado + tempoTrabalhado))/(1000 * 3600));
        Log.d("TempoService", "" + valorGanho);
        bundle.putString("ValorGanho", String.format("R$ %.2f", valorGanho));

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
