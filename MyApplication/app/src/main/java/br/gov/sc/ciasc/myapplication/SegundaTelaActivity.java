package br.gov.sc.ciasc.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class SegundaTelaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SegundaTelaActivity", "Entrou onStart...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SegundaTelaActivity", "Entrou onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("SegundaTelaActivity", "Entrou onPause...");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SegundaTelaActivity", "Entrou onStop...");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("SegundaTelaActivity", "Entrou onRestart...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SegundaTelaActivity", "Entrou onDestroy...");
    }
}
