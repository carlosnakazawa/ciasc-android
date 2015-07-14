package br.gov.sc.ciasc.churrasco;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class SplashActivity extends Activity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(SplashActivity.this, CarneActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.sendMessageDelayed(new Message(), 3000);
    }

}
