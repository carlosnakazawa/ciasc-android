package br.gov.sc.ciasc.torico.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

import java.util.concurrent.TimeUnit;

import br.gov.sc.ciasc.torico.MainActivity;
import br.gov.sc.ciasc.torico.R;

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

    @Override
    public boolean onUnbind(Intent intent) {
        this.atualizaHandler = null;
        return super.onUnbind(intent);
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
        rodando = false;
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("TempoFormatado", formatarDuracao(0));
        bundle.putString("ValorGanho", String.format("R$ %.2f", 0.0));
        bundle.putBoolean("Zerado", true);
        msg.setData(bundle);
        atualizaHandler.sendMessage(msg);
        tempoAcumulado = 0;
        tempoTrabalhado = 0;
    }

    @Override
    public void run() {
        myHandler.postAtTime(this, SystemClock.uptimeMillis() + 1000);
        tempoTrabalhado = (System.nanoTime() - marcadorTempo) / 1000000;
        Log.d("TempoService", "Tempo trabalhado: " + tempoTrabalhado);
        Log.d("TempoService", "Tempo acumulado : " + tempoAcumulado);
        if (atualizaHandler != null) {
            Message msg = new Message();
            Bundle bundle = new Bundle();

            long tempoEmMillis = tempoAcumulado + tempoTrabalhado;
            String tempoFormatado = formatarDuracao(tempoEmMillis);
            Log.d("TempoService", tempoFormatado);
            bundle.putString("TempoFormatado", tempoFormatado);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String salarioBruto = prefs.getString("salario_bruto", "3000");
            String horasMes = prefs.getString("horas_mes", "176");
            String percentualExtra = prefs.getString("percentual_extra", "50");
            double valorHora = Double.parseDouble(salarioBruto) / Double.parseDouble(horasMes);
            Log.d("TempoService", "" + valorHora);
            double valorHoraComAdicional = valorHora * (1 + (Double.parseDouble(percentualExtra) / 100));
            Log.d("TempoService", "" + valorHoraComAdicional);
            Log.d("TempoService", "" + (tempoAcumulado + tempoTrabalhado));
            double valorGanho = valorHoraComAdicional * (((double) (tempoAcumulado + tempoTrabalhado)) / (1000 * 3600));
            Log.d("TempoService", "" + valorGanho);
            bundle.putString("ValorGanho", String.format("R$ %.2f", valorGanho));

            msg.setData(bundle);
            atualizaHandler.sendMessage(msg);
        } else {
            PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String salarioBruto = prefs.getString("salario_bruto", "3000");
            String horasMes = prefs.getString("horas_mes", "176");
            String percentualExtra = prefs.getString("percentual_extra", "50");
            double valorHora = Double.parseDouble(salarioBruto) / Double.parseDouble(horasMes);
            Log.d("TempoService", "" + valorHora);
            double valorHoraComAdicional = valorHora * (1 + (Double.parseDouble(percentualExtra) / 100));
            Log.d("TempoService", "" + valorHoraComAdicional);
            Log.d("TempoService", "" + (tempoAcumulado + tempoTrabalhado));
            double valorGanho = valorHoraComAdicional * (((double) (tempoAcumulado + tempoTrabalhado)) / (1000 * 3600));
            Log.d("TempoService", "" + valorGanho);
            Notification n = new Notification.Builder(this)
                    .setTicker("Valor ganho")
                    .setContentTitle("Tô Rico")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText(String.format("R$ %.2f", valorGanho))
                    .setContentIntent(p)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .build();
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(R.string.app_name, n);
        }
    }

    public class LocalBinder extends Binder {
        public TempoService getTempoService() {
            return TempoService.this;
        }
    }

    public void setAtualizaHandler(Handler handler) {
        this.atualizaHandler = handler;
    }

    public String formatarDuracao(long tempoEmMillis) {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(tempoEmMillis), TimeUnit.MILLISECONDS.toMinutes(tempoEmMillis) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(tempoEmMillis) % TimeUnit.MINUTES.toSeconds(1));
    }
}
