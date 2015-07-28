package br.gov.sc.ciasc.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TarefaDao {

    public static final String TABELA = "tarefa";
    public static final String SCRIPT_CREATE = String.format("CREATE TABLE %s (%s integer primary key autoincrement, %s text not null, %s text);",
            TABELA, TarefaColumns._ID, TarefaColumns.TITULO, TarefaColumns.DESCRICAO);
    public static final String SCRIPT_DROP = String.format("DROP database %s;", TABELA);

    private SQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public TarefaDao(Context context) {
        String[] scriptsCriacao = new String[] {
                SCRIPT_CREATE
        };
        dbHelper = new SQLiteHelper(context, 1, scriptsCriacao, SCRIPT_DROP);

        db = dbHelper.getWritableDatabase();
    }

    public List<Tarefa> findAll() {
        Cursor cursor = db.query("tarefa", new String[]{"_id", "titulo", "descricao"}, "", new String[0], null, null, null);
        List<Tarefa> retorno = new ArrayList<Tarefa>();
        while (cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();
            tarefa.id = cursor.getLong(cursor.getColumnIndex("_id"));
            tarefa.titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            retorno.add(tarefa);
        }
        return retorno;
    }

    public static final class TarefaColumns implements BaseColumns {
        private TarefaColumns() {}

        //public static final String DEFAULT_SORT_ORDER = "_id ASC";

        public static final String TITULO = "titulo";
        public static final String DESCRICAO = "descricao";
        public static final String DATA_DO_ALARME = "dataalarme";
    }
}

