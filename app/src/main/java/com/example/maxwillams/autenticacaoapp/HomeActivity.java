package com.example.maxwillams.autenticacaoapp;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {

    private RecyclerView recyclerProdutos;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter adapterProdutos;

    private List<Produto> produtosFirebase = new ArrayList<>();

    private DatabaseReference dbProdutosRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        dbProdutosRef = ConfiguracaoFirebase.getFirebase()
                .child("Produtos")
                .child(ConfiguracaoFirebase.getIdUser());

        dbProdutosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                produtosFirebase.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    produtosFirebase.add(ds.getValue(Produto.class));
                }

                adapterProdutos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerProdutos = findViewById(R.id.recyclerViewProdutos);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerProdutos.setLayoutManager(mLayoutManager);

        recyclerProdutos.setHasFixedSize(true);

        adapterProdutos = new ProdutoAdapter(produtosFirebase, this);
        recyclerProdutos.setAdapter(adapterProdutos);
    }

    public void irFormProdutos(View v){
        Intent i = new Intent(this, CadastroProdutosActivity.class);
        startActivity(i);
    }

}
