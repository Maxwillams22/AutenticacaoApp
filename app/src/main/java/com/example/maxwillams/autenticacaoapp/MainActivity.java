package com.example.maxwillams.autenticacaoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth userAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (userAuth.getCurrentUser() != null){

            Log.i("AuthUser","Usuário Autenticado");
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);
        }else{
            Log.i("AuthUser","Usuário não autenticado");
        }

    }

    public void cadastroEmail(View v){
        startActivity(new Intent(this,CadastroEmailActivity.class));
    }
}
