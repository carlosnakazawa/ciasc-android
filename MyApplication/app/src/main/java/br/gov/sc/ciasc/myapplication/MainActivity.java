package br.gov.sc.ciasc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Teste do Toast", Toast.LENGTH_LONG).show();
        Button avancar = (Button) findViewById(R.id.botaoAvancar);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaSegundaTela = new Intent(MainActivity.this, SegundaTelaActivity.class);
                startActivity(irParaSegundaTela);
            }
        });
        /*LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout);
        for (int i = 0; i < 100; i++) {
            TextView tv = new TextView(this);
            tv.setText("Texto " + i);
            ll.addView(tv);
        }*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "Entrou onStart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "Entrou onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "Entrou onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "Entrou onStop...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "Entrou onRestart...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "Entrou onDestroy...");
    }
}
