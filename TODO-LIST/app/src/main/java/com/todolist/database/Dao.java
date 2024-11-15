package com.todolist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.todolist.model.Tarefa;

import java.util.ArrayList;

public class Dao {
    private static Dao instance = null;

    private ArrayList<Tarefa> tarefas;
    private DbHelper dbHelper;
    private Context ctx;

    private Dao() {
        this.dbHelper = new DbHelper(ctx);
        this.tarefas = new ArrayList<>();
    }


    public static Dao getInstance() {
        if (instance == null) {
            instance = new Dao();
        }
        return instance;
    }

    public void setContext(Context ctx) {
        this.ctx = ctx;
    }

    public ArrayList<Tarefa> getTarefas() {
        return this.tarefas;
    }

    public boolean apagarMarcados() {
        dbHelper = new DbHelper(this.ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            // db
            db.delete(
                    "TAREFA",
                    "MARCADO = ?",
                    new String[]{"1"}
            );
            db.close();
            // estrutura de dados (para sincronia)
            ArrayList<Tarefa> novasTarefas = new ArrayList<>();
            for (Tarefa t : tarefas) {
                if (!t.marcado) novasTarefas.add(t);
            }
            this.tarefas = novasTarefas;
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    public boolean refresh() {
        this.tarefas.clear();
        return this.obterTodosOsRegistros();
    }

    public boolean novaTarefa(String text) {
        dbHelper = new DbHelper(this.ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("TEXTO", text);
        cv.put("MARCADO", false);
        try {
            // db
            db.insert("TAREFA", null, cv);
            db.close();
            // estrutura de dados (para sincronia)
            this.tarefas.clear();
            this.obterTodosOsRegistros();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    public boolean marcarOuDesmarcarTarefa(Tarefa tarefa) {
        String valorMarcadoInvertido = tarefa.marcado ? "0" : "1";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MARCADO", valorMarcadoInvertido);
        try {
            // db
            db.update(
                    "TAREFA",
                    cv,
                    "_id = ?",
                    new String[]{String.valueOf(tarefa.id)}
            );
            db.close();
            // estrutura de dados (para sincronia)
            // Se retornar true, a estr. de dados será atualizada na main activity

        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    public boolean limparBanco() {
        dbHelper = new DbHelper(this.ctx);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete("TAREFA", null, null);
            db.close();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    public boolean marcarTudo() {
        dbHelper = new DbHelper(this.ctx);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MARCADO", true);
        try {
            // db
            db.update(
                    "TAREFA",
                    cv,
                    null,
                    null
            );
            db.close();
            // estrutura de dados (para sincronia)
            for (Tarefa t : tarefas) {
                t.marcado = true;
            }
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    public boolean desmarcarTudo() {
        dbHelper = new DbHelper(this.ctx);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("MARCADO", false);
        try {
            // db
            db.update(
                    "TAREFA",
                    cv,
                    null,
                    null
            );
            db.close();
            // estrutura de dados (para sincronia)
            for (Tarefa t : tarefas) {
                t.marcado = false;
            }
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    public boolean obterTodosOsRegistros() {
        this.tarefas.clear();
        dbHelper = new DbHelper(this.ctx);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(
                    "TAREFA",
                    new String[]{"_id", "TEXTO", "MARCADO"},
                    null, null, null, null, null
            );
            while (cursor.moveToNext()) {
                Integer id = cursor.getInt(0);
                String texto = cursor.getString(1);
                boolean marcado = cursor.getInt(2) == 1;
                tarefas.add(new Tarefa(id, texto, marcado));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    public boolean reset() {
        return
        this.limparBanco() &&
        this.novaTarefa("Escovar dentes") &&
        this.novaTarefa("Pentear o cabelo") &&
        this.novaTarefa("Lavar os pratos") &&
        this.novaTarefa("Tomar banho") &&
        this.novaTarefa("Fazer o dever de casa") &&
        this.novaTarefa("Ir ao cinema");
    }
}
