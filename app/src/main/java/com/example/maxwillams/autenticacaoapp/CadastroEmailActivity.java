package com.example.maxwillams.autenticacaoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroEmailActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;

    private FirebaseAuth userAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_email2);

        if (userAuth.getCurrentUser() != null){
            Log.i("AuthUser","Usuário autenticado");
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        }

        email = findViewById(R.id.txtEmail);
        senha = findViewById(R.id.txtSenha);

    }

    public void cadastrarUsuario(View v) {

        /*Log só para acompanhar se a criação do usuário
         * Não precisa utilizar isso profissionalmente*/
        Log.i("CreateUser", email.getText().toString()
                + " - " + senha.getText().toString());

        userAuth.createUserWithEmailAndPassword(
                email.getText().toString(), senha.getText().toString())
                .addOnCompleteListener(CadastroEmailActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Log.i("CreateUser",
                                            "Sucesso ao cadastrar o usuário "
                                                    + email.getText().toString());
                                } else {
                                    Log.i("CreateUser",
                                            "Erro ao cadastrar o usuário!");
                                }
                            }
                        });

        /*Verificar se o usuário está autenticado*/
        if (userAuth.getCurrentUser() != null) {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            this.finish();
        } else {
            Log.i("AuthUser",
                    "Erro ao autenticar usuário! Tente novamente.");

        }

    }
}
