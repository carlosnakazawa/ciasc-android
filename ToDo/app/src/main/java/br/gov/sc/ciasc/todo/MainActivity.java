package br.gov.sc.ciasc.todo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTarefaDao().execute();
    }

    public class ListaTarefasAdapter extends BaseAdapter {

        List<Tarefa> listaTarefas;
        Context ctx;

        public ListaTarefasAdapter(Context ctx, List<Tarefa> listaTarefas) {
            this.listaTarefas = listaTarefas;
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            return listaTarefas.size();
        }

        @Override
        public Object getItem(int position) {
            return listaTarefas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return listaTarefas.get(position).id;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.tarefa_item, null);
            TextView textViewTitulo = (TextView) view.findViewById(R.id.textViewTitulo);

            TextView textViewDescricao = (TextView) view.findViewById(R.id.textViewDescricao);
            Tarefa tarefa = listaTarefas.get(position);
            textViewTitulo.setText(tarefa.titulo);
            textViewDescricao.setText(tarefa.descricao);
            return view;
        }
    }

    public class AsyncTarefaDao extends AsyncTask<Void, Void, List<Tarefa>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "...", "Buscando dados de tarefas");

        }

        @Override
        protected List<Tarefa> doInBackground(Void... params) {
            TarefaDao dao = new TarefaDao(MainActivity.this);
            try {
                Thread.sleep(10000);
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
            ListView listView = (ListView) findViewById(R.id.listaTarefas);
            listView.setAdapter(new ListaTarefasAdapter(MainActivity.this, tarefas));
            progressDialog.dismiss();
        }
    }
}
