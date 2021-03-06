package br.gov.sc.ciasc.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private String[] scriptSQLCreate;
    private String scriptSQLDelete;
    private static final  String NOME_DB = "tarefas";

    public SQLiteHelper(Context context, int versaoBanco, String[] scriptSQLCreate, String scriptSQLDelete) {
        super(context, NOME_DB, null, versaoBanco);

        this.scriptSQLCreate = scriptSQLCreate;
        this.scriptSQLDelete = scriptSQLDelete;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String script : scriptSQLCreate) {
            db.execSQL(script);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scriptSQLDelete);
        onCreate(db);
    }
}
