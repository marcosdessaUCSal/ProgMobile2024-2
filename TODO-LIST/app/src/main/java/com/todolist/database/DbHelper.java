package com.todolist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NOME = "todo_database";
    private static final int DB_VERSAO = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NOME, null, DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.criarBaseDeDados(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // DESNECESSÁRIO PARA ESTE EXERCÍCIO
    }


    private void criarBaseDeDados(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("CREATE TABLE TAREFA (")
                .append("  _id INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append("  TEXTO TEXT, ")
                .append("  MARCADO NUMERIC);"); // 0 = FALSE, 1 = TRUE
        db.execSQL(sb.toString());
    }
}
