package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import br.gov.sc.ciasc.churrasco.dto.ChurrascoDto;


public class RefrigeranteActivity extends Activity {

    private ChurrascoDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerante);

        NumberPicker numberRefrigerante = (NumberPicker) findViewById(R.id.numberRefrigerante);

        String[] values = new String[20];

        for (int i = 0; i < values.length; i++) {
            String number = Integer.toString((i+1)*50);
            values[i] = number;
        }

        numberRefrigerante.setDisplayedValues(values);
        numberRefrigerante.setMinValue(1);
        numberRefrigerante.setMaxValue(20);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            dto = (ChurrascoDto) params.get("DADOS");
        }
    }

    public void buttonNextClick(View view) {
        Intent irParaPessoa = new Intent(this, PessoaActivity.class);
        NumberPicker np = (NumberPicker) findViewById(R.id.numberRefrigerante);
        Log.d("RefrigeranteActivity", String.valueOf(np.getValue()));
        dto.setRefrigerante(np.getValue()*50);
        irParaPessoa.putExtra("DADOS", dto);
        startActivity(irParaPessoa);
    }
}
