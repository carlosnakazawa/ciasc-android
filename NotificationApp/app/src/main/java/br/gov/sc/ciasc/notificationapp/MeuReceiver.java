package br.gov.sc.ciasc.notificationapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Aluno on 22/07/2015.
 */
public class MeuReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Exemplo", Toast.LENGTH_SHORT).show();
    }
}
