package com.contatos1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.contatos1.Model.Contato;
import com.contatos1.Model.ListaContatos;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linlayContainer;
    private LayoutInflater layoutInflater;
    private ListaContatos listaContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linlayContainer = findViewById(R.id.linlay_container);
        layoutInflater = LayoutInflater.from(this);
        listaContatos = new ListaContatos();

        this.coloqueCardsNaTela();
    }

    private void coloqueCardsNaTela() {
        for (Contato c : listaContatos.getListaContatos()) {
            View cardView = layoutInflater.inflate(R.layout.card_contatos,
                    linlayContainer, false);
            TextView tvNome = cardView.findViewById(R.id.tv_nome);
            TextView tvEmail = cardView.findViewById(R.id.tv_email);
            TextView tvTel = cardView.findViewById(R.id.tv_tel);
            tvNome.setText(c.getNome());
            tvEmail.setText(c.getEmail());
            tvTel.setText(c.getTel());
            linlayContainer.addView(cardView);
        }

    }
}