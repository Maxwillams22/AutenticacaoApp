package com.example.maxwillams.autenticacaoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.io.FileReader;

public class CadastroProdutosActivity extends AppCompatActivity {

    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference dbProdutosReference;

    private EditText descricaoProduto;
    private EditText qtdProduto;
    private EditText valorProduto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);

        initComponentes();

        dbProdutosReference = ConfiguracaoFirebase.getFirebase()
        .child("Produtos")
        .child(ConfiguracaoFirebase.getIdUser());
    }

    private void initComponentes(){

        if (auth.getCurrentUser() == null){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            Log.i("check", "Usuário Logado");
        }

        getSupportActionBar().setTitle("Cadastro de Produtos");

        descricaoProduto = findViewById(R.id.inpDescProduto);
        qtdProduto = findViewById(R.id.inpQtdProduto);
        valorProduto = findViewById(R.id.inpValorProdutos);
    }

    public void cadastrarProduto(View v){

        Produto p = new Produto();
        p.setNomeProduto(descricaoProduto.getText().toString());
        p.setQuantidadeProduto(Integer.parseInt(qtdProduto.getText().toString()) );
        p.setPrecoProduto(Double.parseDouble(valorProduto.getText().toString()));

        dbProdutosReference.push().setValue(p);

        startActivity(new Intent(this,HomeActivity.class));
        this.finish();
    }
}
