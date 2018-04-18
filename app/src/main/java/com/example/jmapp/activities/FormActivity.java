package com.example.jmapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.example.jmapp.R;
import com.example.jmapp.database.config.Database;
import com.example.jmapp.model.Usuario;

/**
 * Tela de formulario
 */
public class FormActivity extends AppCompatActivity {

    private Database db;
    private EditText et_nome;
    private EditText et_email;
    private EditText et_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        findViews();

        //  Impede que o teclado nao abra assim que entra na tela
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /* Realiza o cadastro do usuario */
    public void cadastrar(View view){
        try{
            db = new Database(this);
            db.open();
            String nome = et_nome.getText().toString();
            String email = et_email.getText().toString();
            String senha = et_senha.getText().toString();
            if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos...", Toast.LENGTH_SHORT).show();
            }else{
                boolean isOk = Database.usuarioDao.save(new Usuario(nome, email, senha));
                if(isOk){
                    Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Falha ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Falha ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
        }finally {
            db.close();
        }
    }

    /* Encontra os componentes do layout pelo id */
    private void findViews(){
        et_nome = findViewById(R.id.et_nome);
        et_email = findViewById(R.id.et_email);
        et_senha = findViewById(R.id.et_senha);
    }
}
