package com.example.jmapp.database.config;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.jmapp.database.dao.UsuarioDao;
import com.example.jmapp.database.schemas.UsuarioSchema;

/**
 * Classe de configuracao SQLite
 */
public class Database {

    private static final String DB_NAME = "my_database.db";
    private static final int DB_VERSION = 1;
    private DatabaseHelper mDbHelper;
    private final Context context;
    private SQLiteDatabase db;
    public static UsuarioDao usuarioDao;

    public Database(Context context) {
        this.context = context;
    }

    /* Abre a conexao com o SQLite */
    public Database open() throws SQLException {
        mDbHelper = new DatabaseHelper(context);
        db = mDbHelper.getWritableDatabase();
        usuarioDao = new UsuarioDao(db);
        return this;
    }

    /* Fecha a conexao com o SQLite */
    public void close() {
        mDbHelper.close();
    }

    /**
    * Classe de configuracao do SQLite
    */
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        /* Realiza a criacao do banco de dados e as tabelas */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(UsuarioSchema.CREATE_TABLE);
        }

        /* Executado somente se a versao do banco de dados mudar */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + UsuarioSchema.TABLE_NAME);
            onCreate(db);
        }
    }
}
