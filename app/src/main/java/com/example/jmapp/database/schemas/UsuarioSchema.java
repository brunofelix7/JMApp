package com.example.jmapp.database.schemas;

/**
 * Classe de definicao do schema da tabela usuario no SQLite
 */
public class UsuarioSchema {

    //  Definicoes da tabela usuarios
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NOME = "NOME";
    public static final String COLUMN_EMAIL = "EMAIL";
    public static final String COLUMN_SENHA = "SENHA";
    public static final String TABLE_NAME = "usuarios";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_NOME + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_SENHA + " TEXT"
            + ")";

    //  Lista com todas as colunas da tabela usuarios
    public static String[] USER_COLUMNS = new String[]{COLUMN_ID, COLUMN_NOME, COLUMN_EMAIL, COLUMN_SENHA};

    //  Indices das colunas da tabela usuarios
    public static int idIndex;
    public static int nomeIndex;
    public static int emailIndex;
    public static int senhaIndex;
}
