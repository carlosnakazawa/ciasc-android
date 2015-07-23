package br.gov.sc.ciasc.notificationapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String tickerText = "Você recebeu uma mensagem";
        String titulo = "Ricardo";
        String mensagem = "Exemplo de notificação";

        PendingIntent p = PendingIntent.getActivity(this, 0, new Intent(this, ExecutaNotificacaoActivity.class), 0);
        Notification n = new Notification.Builder(this)
                .setTicker(tickerText)
                .setContentTitle(titulo)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(mensagem)
                .setContentIntent(p)
                .setWhen(System.currentTimeMillis())
                .build();
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(R.string.app_name, n);

        TextView textView = new TextView(this);
        textView.setText("Notificação criada...");
        setContentView(textView);
    }

}
