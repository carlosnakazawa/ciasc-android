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
import android.widget.NumberPicker;

import br.gov.sc.ciasc.churrasco.dto.ChurrascoDto;


public class CarneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carne);

        NumberPicker numberCarne = (NumberPicker) findViewById(R.id.numberCarne);
        String[] values = new String[20];

        for (int i = 0; i < values.length; i++) {
            String number = Integer.toString((i+1)*50);
            values[i] = number;
        }

        numberCarne.setDisplayedValues(values);
        numberCarne.setMinValue(1);
        numberCarne.setMaxValue(20);
    }

    public void buttonNextClick(View view) {
        Intent irParaLinguica = new Intent(this, LinguicaActivity.class);
        NumberPicker np = (NumberPicker) findViewById(R.id.numberCarne);
        Log.d("CarneActivity", String.valueOf(np.getValue()*50));
        ChurrascoDto dto = new ChurrascoDto();
        dto.setCarne(np.getValue() * 50);
        irParaLinguica.putExtra("DADOS", dto);
        startActivity(irParaLinguica);
    }
}
