package br.gov.sc.ciasc.todo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.List;

public class AsyncTarefaDao extends AsyncTask<Void, Void, List<Tarefa>> {

    private ProgressDialog progressDialog;

    private Activity activity;

    public AsyncTarefaDao(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(activity, "...", "Buscando dados de tarefas");

    }

    @Override
    protected List<Tarefa> doInBackground(Void... params) {
        TarefaDao dao = new TarefaDao(activity);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Tarefa> listaTarefas = dao.findAll();
        dao.fechar();
        return listaTarefas;
    }

    @Override
    protected void onPostExecute(List<Tarefa> tarefas) {
        super.onPostExecute(tarefas);
        ListView listView = (ListView) activity.findViewById(R.id.listaTarefas);
        listView.setAdapter(new ListaTarefasAdapter(activity, tarefas));
        progressDialog.dismiss();
    }
}
