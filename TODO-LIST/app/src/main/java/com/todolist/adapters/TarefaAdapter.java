package com.todolist.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.todolist.R;
import com.todolist.database.Dao;
import com.todolist.model.Tarefa;

import java.util.ArrayList;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.TarefasViewHolder> {

    private ArrayList<Tarefa> tarefas;
    private Listener listener;

    public TarefaAdapter() {
        this.tarefas = Dao.getInstance().getTarefas();
    }

    public void atualizaTarefas(ArrayList<Tarefa> tf) {
        this.tarefas = tf;
    }

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TarefasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardTarefa  = (CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_tarefa, parent, false
        );
        return new TarefasViewHolder(cardTarefa);
    }

    @Override
    public void onBindViewHolder(@NonNull TarefasViewHolder holder, int position) {
        CardView cardTarefa = holder.cardTarefa;
        TextView tvTexto = cardTarefa.findViewById(R.id.tv_texto_tarefa);
        tvTexto.setText(tarefas.get(position).texto);
        CheckBox checkBox = cardTarefa.findViewById(R.id.checkbox_tarefa);
        checkBox.setChecked(tarefas.get(position).marcado);
        if (tarefas.get(position).marcado) {
            tvTexto.setTextColor(Color.LTGRAY);
            cardTarefa.setCardBackgroundColor(Color.parseColor("#4682B4"));
        } else {
            tvTexto.setTextColor(Color.WHITE);
            cardTarefa.setCardBackgroundColor(Color.parseColor("#5993C3"));
        }
        cardTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posAtual = holder.getAdapterPosition();
                if (posAtual != RecyclerView.NO_POSITION) {
                    listener.onClick(posAtual);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.tarefas.size();
    }

    public static class TarefasViewHolder extends RecyclerView.ViewHolder {
        private CardView cardTarefa;
        public TarefasViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardTarefa = itemView;
        }
    }
}
