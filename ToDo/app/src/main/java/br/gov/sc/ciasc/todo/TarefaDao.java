package br.gov.sc.ciasc.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TarefaDao {

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public TarefaDao(Context context) {
        String[] scriptsCriacao = new String[] {
                "CREATE DATABASE tarefas;",
                "CREATE TABLE tarefa (_id integer primary key autoincrement, titulo text not null, descricao text);"
        };
        dbHelper = new SQLiteHelper(context, "tarefas", 1, scriptsCriacao, "DROP database tarefas;");

        db = dbHelper.getWritableDatabase();
    }

    public List<Tarefa> findAll() {
        Cursor cursor = db.query("tarefa", new String[]{"tarefa"}, "", new String[0], null, null, null);
        List<Tarefa> retorno = new ArrayList<Tarefa>();
        while (cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.id = cursor.getLong(cursor.getColumnIndex("_id"));
            tarefa.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            retorno.add(tarefa);
        }
        return retorno;
    }
}

