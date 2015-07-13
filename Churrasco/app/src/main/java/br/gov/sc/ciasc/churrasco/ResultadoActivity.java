package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import br.gov.sc.ciasc.churrasco.dto.ChurrascoDto;


public class ResultadoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            ChurrascoDto dto = (ChurrascoDto) params.get("DADOS");
            TextView titulo = (TextView) findViewById(R.id.textViewTitulo);
            titulo.setText("Festa para " + dto.getPessoa() + " pessoas");
            TextView resultado = (TextView) findViewById(R.id.textViewResultado);
            resultado.setText(dto.toString());
        }
    }

}
