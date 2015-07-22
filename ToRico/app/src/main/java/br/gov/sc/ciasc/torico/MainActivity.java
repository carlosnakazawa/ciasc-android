package br.gov.sc.ciasc.torico;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import br.gov.sc.ciasc.torico.services.TempoService;


public class MainActivity extends AppCompatActivity implements ServiceConnection {

    TempoService tempoService;
    Handler atualizaHandler = new AtualizaHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(this, TempoService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        bindService(new Intent(this, TempoService.class), this, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
        Log.d("MainActivity", "onPause Service");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonIniciarClick(View view) {
        Button botaoIniciar = (Button) findViewById(R.id.buttonIniciar);
        if (!tempoService.rodando) {
            tempoService.start();
            botaoIniciar.setText(R.string.button_pausar);
            botaoIniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pause, 0, 0, 0);
        } else {
            tempoService.pause();
            botaoIniciar.setText(R.string.button_iniciar);
            botaoIniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play, 0, 0, 0);
        }
    }

    public void buttonZerarClick(View view) {
        tempoService.stop();
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.d("MainActivity", "onServiceConnected");
        tempoService = ((TempoService.LocalBinder) service).getTempoService();
        tempoService.setAtualizaHandler(atualizaHandler);

        Button botaoIniciar = (Button) findViewById(R.id.buttonIniciar);
        if (tempoService.rodando) {
            botaoIniciar.setText(R.string.button_pausar);
            botaoIniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_pause, 0, 0, 0);
        } else {
            botaoIniciar.setText(R.string.button_iniciar);
            botaoIniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play, 0, 0, 0);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d("MainActivity", "onServiceDisconnected");
    }

    public class AtualizaHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            TextView textView = (TextView) findViewById(R.id.textViewTempo);
            textView.setText(msg.getData().getString("TempoFormatado"));

            TextView textView1 = (TextView) findViewById(R.id.textViewValor);
            textView1.setText(msg.getData().getString("ValorGanho"));
            Button botaoIniciar = (Button) findViewById(R.id.buttonIniciar);
            if (msg.getData().getBoolean("Zerado")) {
                botaoIniciar.setText(R.string.button_iniciar);
                botaoIniciar.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play, 0, 0, 0);
            }
        }
    }
}
