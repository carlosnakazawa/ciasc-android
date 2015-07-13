package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    public void buttonShareClick(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        Bundle params = getIntent().getExtras();
        if (params != null) {
            ChurrascoDto dto = (ChurrascoDto) params.get("DADOS");
            sendIntent.putExtra(Intent.EXTRA_TEXT, dto.toString());
        }
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }
}
