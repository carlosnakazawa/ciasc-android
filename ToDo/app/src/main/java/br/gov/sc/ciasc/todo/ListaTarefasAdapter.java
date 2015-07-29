package br.gov.sc.ciasc.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

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
