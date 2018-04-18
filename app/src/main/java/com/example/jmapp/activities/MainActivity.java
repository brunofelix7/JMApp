package com.example.jmapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.jmapp.R;
import com.example.jmapp.database.config.Database;
import com.example.jmapp.model.Usuario;
import java.util.List;

/**
 * Tela principal
 */
public class MainActivity extends AppCompatActivity {

    private Database db;
    private TextView tv_dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        getData();
    }

    /* Encontra os componentes do layout pelo id */
    private void findViews(){
        tv_dados = findViewById(R.id.tv_dados);
    }

    /* Recupera os dados dos usuarios no SQLite */
    private void getData(){
        try{
            StringBuilder stringBuilder = new StringBuilder();
            db = new Database(this);
            db.open();
            List<Usuario> usuarios = Database.usuarioDao.findAll();
            if(usuarios != null){
                for (Usuario usuario : usuarios){
                    stringBuilder.append("Id: " + usuario.getId() + "\n");
                    stringBuilder.append("Nome: " + usuario.getNome() + "\n");
                    stringBuilder.append("E-mail: " + usuario.getEmail() + "\n");
                    stringBuilder.append("Senha: " + usuario.getSenha() + "\n");
                    stringBuilder.append("------------------------\n");
                }
                tv_dados.setText(stringBuilder.toString());
            }else{
                tv_dados.setText("");
                tv_dados.setText(this.getResources().getString(R.string.txt_dados_nulos));
            }
        }catch (Exception e){
            e.printStackTrace();
            tv_dados.setText("");
            tv_dados.setText(this.getResources().getString(R.string.txt_dados_erro));
        }finally {
            db.close();
        }
    }
}
