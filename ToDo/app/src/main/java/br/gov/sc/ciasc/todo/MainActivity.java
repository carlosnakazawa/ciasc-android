package br.gov.sc.ciasc.todo;

import android.app.Activity;
import android.content.Context;
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

    TarefaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new TarefaDao(this);

        //List<Tarefa> listaTarefas = dao.findAll();

        //ListaTarefasAdapter adapter = new ListaTarefasAdapter(this, listaTarefas);

        ListView listView = (ListView) findViewById(R.id.listaTarefas);
        //listView.setAdapter(adapter);
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
            View view = inflater.inflate(R.layout.tarefa_item, parent, false);
            TextView textViewTitulo = (TextView) view.findViewById(R.id.textViewTitulo);

            TextView textViewDescricao = (TextView) view.findViewById(R.id.textViewDescricao);
            Tarefa tarefa = (Tarefa) getItem(position);
            textViewTitulo.setText(tarefa.titulo);
            textViewDescricao.setText(tarefa.descricao);
            return convertView;
        }
    }
}

