package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;

import br.gov.sc.ciasc.churrasco.dto.ChurrascoDto;


public class PessoaActivity extends Activity {

    private ChurrascoDto dto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        NumberPicker numberPessoa = (NumberPicker) findViewById(R.id.numberPessoa);

        numberPessoa.setMinValue(1);
        numberPessoa.setMaxValue(20);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            dto = (ChurrascoDto) params.get("DADOS");
        }
    }

    public void buttonNextClick(View view) {
        Intent irParaResultado = new Intent(this, ResultadoActivity.class);
        NumberPicker np = (NumberPicker) findViewById(R.id.numberPessoa);
        Log.d("PessoaActivity", String.valueOf(np.getValue()));
        dto.setPessoa(np.getValue());
        irParaResultado.putExtra("DADOS", dto);
        startActivity(irParaResultado);
    }
}
