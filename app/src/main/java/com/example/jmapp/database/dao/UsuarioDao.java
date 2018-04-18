package com.example.jmapp.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.jmapp.database.interfaces.IUsuarioDao;
import com.example.jmapp.database.schemas.UsuarioSchema;
import com.example.jmapp.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe com operacoes CRUD da tabela usuarios
 */
public class UsuarioDao implements IUsuarioDao{

    private Cursor cursor;
    private ContentValues contentValues;
    private SQLiteDatabase db;

    public UsuarioDao(SQLiteDatabase db) {
        this.db = db;
    }

    /* Salva um usuario */
    @Override
    public boolean save(Usuario usuario) {
        try{
            contentValues = new ContentValues();
            contentValues.put(UsuarioSchema.COLUMN_NOME, usuario.getNome());
            contentValues.put(UsuarioSchema.COLUMN_EMAIL, usuario.getEmail());
            contentValues.put(UsuarioSchema.COLUMN_SENHA, usuario.getSenha());
            return this.db.insert(UsuarioSchema.TABLE_NAME, null, contentValues) > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* Retorna todos os usuarios */
    @Override
    public List<Usuario> findAll() {
        try{
            List<Usuario> usuarios = new ArrayList<>();
            cursor = this.db.query(UsuarioSchema.TABLE_NAME, UsuarioSchema.USER_COLUMNS, null, null, null, null, UsuarioSchema.COLUMN_ID);
            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Usuario usuario = new Usuario();
                    if (cursor != null) {
                        if (cursor.getColumnIndex(UsuarioSchema.COLUMN_ID) != -1) {
                            UsuarioSchema.idIndex = cursor.getColumnIndexOrThrow(UsuarioSchema.COLUMN_ID);
                            usuario.setId(cursor.getInt(UsuarioSchema.idIndex));
                        }
                        if (cursor.getColumnIndex(UsuarioSchema.COLUMN_NOME) != -1) {
                            UsuarioSchema.nomeIndex = cursor.getColumnIndexOrThrow(UsuarioSchema.COLUMN_NOME);
                            usuario.setNome(cursor.getString(UsuarioSchema.nomeIndex));
                        }
                        if (cursor.getColumnIndex(UsuarioSchema.COLUMN_EMAIL) != -1) {
                            UsuarioSchema.emailIndex = cursor.getColumnIndexOrThrow(UsuarioSchema.COLUMN_EMAIL);
                            usuario.setEmail(cursor.getString(UsuarioSchema.emailIndex));
                        }
                        if (cursor.getColumnIndex(UsuarioSchema.COLUMN_SENHA) != -1) {
                            UsuarioSchema.senhaIndex = cursor.getColumnIndexOrThrow(UsuarioSchema.COLUMN_SENHA);
                            usuario.setSenha(cursor.getString(UsuarioSchema.senhaIndex));
                        }
                    }
                    usuarios.add(usuario);
                    cursor.moveToNext();
                }
            }
            return usuarios;
        }catch (SQLException e){
            return null;
        }finally {
            cursor.close();
        }
    }
}
