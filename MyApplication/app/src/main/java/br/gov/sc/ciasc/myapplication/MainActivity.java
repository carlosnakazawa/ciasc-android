package br.gov.sc.ciasc.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
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
                irParaSegundaTela.putExtra("mensagem", "Opa");
                startActivity(irParaSegundaTela);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Cadastrar").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "Menu Cadastrar", Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse("http://www.google.com/");
                Intent intencao = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intencao);
                return false;
            }
        });

        menu.add("Ligar").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(MainActivity.this, "Menu Ligar", Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse("tel:829734983");
                Intent intencao = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intencao);
                return false;
            }
        });

        menu.add("Contatos").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Uri uri = Uri.parse("content://com.android.contacts/contacts");
                Intent intencao = new Intent(Intent.ACTION_PICK, uri);
                startActivity(intencao);
                return false;
            }
        });

        SubMenu subMenuOutros = menu.addSubMenu("Outros");
        subMenuOutros.add("Sair").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                Toast.makeText(MainActivity.this, "Sair...", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        return true;
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
