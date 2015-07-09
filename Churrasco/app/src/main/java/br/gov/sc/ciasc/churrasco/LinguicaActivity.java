package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import br.gov.sc.ciasc.churrasco.dto.ChurrascoDto;


public class LinguicaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguica);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            ChurrascoDto dto = (ChurrascoDto) params.get("DADOS");
            TextView tv = (TextView) findViewById(R.id.mostraCarne);
            tv.setText(String.valueOf(dto.getCarne()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_linguica, menu);
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
