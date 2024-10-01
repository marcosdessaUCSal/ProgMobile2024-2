package com.contatos2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.contatos2.R;
import com.contatos2.model.Contato;
import com.contatos2.model.ListaContatos;

import java.util.ArrayList;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.ContatosViewHolder> {

    // Aqui estão os dados que o adaptador deve receber (no array list 'contatos')
    private ListaContatos pacoteContatos;
    private ArrayList<Contato> contatos;
    private Listener listener;

    // CONSTRUTOR: precisa receber a estrutura de dados (com os contatos)
    public ContatosAdapter(ListaContatos pc) {
        this.pacoteContatos = pc;
        this.contatos = this.pacoteContatos.getListaContatos();
    }

    // Esta interface deve ser implementada no contexto que usa este adapter
    public interface Listener {
        void onClick(int position);
    }

    // O listener implementado externamente é introduzino aqui neste adaptador
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    // IMPORTANTE: use o ViewHolder desta classe aninhada (ContatosViewHolder)
    @NonNull
    @Override
    public ContatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Criando uma referência ao card view
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_contato, parent, false
        );
        return new ContatosViewHolder(cv);
    }

    // Insere os dados na view (neste caso, no card que contém o contato)
    @Override
    public void onBindViewHolder(@NonNull ContatosViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        TextView tvNome = cardView.findViewById(R.id.tv_nome);
        TextView tvEmail = cardView.findViewById(R.id.tv_email);
        TextView tvTel = cardView.findViewById(R.id.tv_tel);
        // inserindo os dados do contato no card
        tvNome.setText(contatos.get(position).getNome());
        tvEmail.setText(contatos.get(position).getEmail());
        tvTel.setText(contatos.get(position).getTel());
        // estabelece o listener para clicks do usuário
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posAtual = holder.getAdapterPosition();
                if (posAtual != RecyclerView.NO_POSITION) {
                    listener.onClick(posAtual);
                }
            }
        });
    }

    // Define a quantidade de elementos a serem exibidos
    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public static class ContatosViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ContatosViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
}
