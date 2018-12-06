package com.example.maxwillams.autenticacaoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter{

    private List<Produto> produtos;
    private Context context;

    public ProdutoAdapter(List<Produto> produtos, Context context){
        this.produtos = produtos;
        this.context = context;
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView descricaoProduto;

        public MyViewHolder(View v){
            super(v);
            descricaoProduto = v.findViewById(R.id.inpDescProduto);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_item_home,viewGroup,false);

        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

        String nProduto = produtos.get(i).getNomeProduto();
        myViewHolder.descricaoProduto.setText(nProduto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }


}
