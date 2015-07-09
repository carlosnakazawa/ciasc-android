package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.gov.sc.ciasc.churrasco.dto.ChurrascoDto;


public class CarneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carne);

        Button botaoAvancar = (Button) findViewById(R.id.buttonNext);
        botaoAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent irParaLinguica = new Intent(CarneActivity.this, LinguicaActivity.class);
                EditText et = (EditText) findViewById(R.id.editText);
                Log.d("CarneActivity", et.getText().toString());
                ChurrascoDto dto = new ChurrascoDto();
                dto.setCarne(Integer.parseInt(et.getText().toString()));
                irParaLinguica.putExtra("DADOS", dto);
                startActivity(irParaLinguica);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carne, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
