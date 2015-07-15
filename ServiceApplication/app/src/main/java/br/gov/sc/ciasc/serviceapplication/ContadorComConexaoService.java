package br.gov.sc.ciasc.serviceapplication;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ContadorComConexaoService extends ContadorService {
    private final IBinder conexao = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return conexao;
    }

    public int getContador() {
        return contador;
    }

    public class LocalBinder extends Binder {
        public ContadorComConexaoService getContador() {
            return ContadorComConexaoService.this;
        }
    }
}
