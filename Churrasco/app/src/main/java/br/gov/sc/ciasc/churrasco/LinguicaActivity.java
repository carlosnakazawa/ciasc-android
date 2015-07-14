package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

import br.gov.sc.ciasc.churrasco.dto.ChurrascoDto;


public class LinguicaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguica);

        NumberPicker numberLinguica = (NumberPicker) findViewById(R.id.numberLinguica);

        numberLinguica.setMinValue(1);
        numberLinguica.setMaxValue(10);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nome = prefs.getString("nome", "Alberto");
        Toast.makeText(this, nome, Toast.LENGTH_LONG).show();
    }

    public void buttonNextClick(View view) {
        Intent irParaRefri = new Intent(this, RefrigeranteActivity.class);
        NumberPicker np = (NumberPicker) findViewById(R.id.numberLinguica);
        Log.d("CarneActivity", String.valueOf(np.getValue()));
        Bundle params = getIntent().getExtras();
        if (params != null) {
            ChurrascoDto dto = (ChurrascoDto) params.get("DADOS");
            dto.setLinguica(np.getValue());
            irParaRefri.putExtra("DADOS", dto);
        }
        startActivity(irParaRefri);
    }
}
