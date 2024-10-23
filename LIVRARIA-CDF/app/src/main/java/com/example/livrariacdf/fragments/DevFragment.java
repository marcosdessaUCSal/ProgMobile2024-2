package com.example.livrariacdf.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;

import com.example.livrariacdf.FocoActivity;
import com.example.livrariacdf.R;
import com.example.livrariacdf.model.Acervo;
import com.example.livrariacdf.model.EnumGenero;
import com.example.livrariacdf.model.Livro;

import java.util.ArrayList;
import java.util.HashMap;

public class DevFragment extends ListFragment {

    private ArrayList<Integer> arrayIds = new ArrayList<>();
    private ArrayList<String> arrayTitulos = new ArrayList<>();


    public DevFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.definirTitulosIds();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                arrayTitulos
        );
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // Ao se clicar em uma linha, o livro terá seu id identificado e esse valor
    // será repassado para a FocoActivity, onde o livro será exibido
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getContext(), FocoActivity.class);
        intent.putExtra("ID", arrayIds.get(position));
        startActivity(intent);

    }

    // Cria dois arrays: um para conter os ids, outro para títulos do livro
    // Ambos os arrays são indexados de acordo com a 'posição' da exibição dos títulos na lista
    private void definirTitulosIds() {
        ArrayList<Livro> livros = Acervo.getInstance().getLivros(EnumGenero.DEV);
        for (int i = 0; i < livros.size(); i++) {
            arrayIds.add(livros.get(i).id);
            arrayTitulos.add(livros.get(i).titulo);
        }
    }
}