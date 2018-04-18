package com.example.jmapp.database.interfaces;

import com.example.jmapp.model.Usuario;
import java.util.List;

/**
 * Interface com metodos CRUD a ser implementada pelo UsuarioDao
 */
public interface IUsuarioDao {

    boolean save(Usuario usuario);
    List<Usuario> findAll();

}
