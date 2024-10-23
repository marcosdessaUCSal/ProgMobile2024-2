package com.example.livrariacdf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.livrariacdf.model.Acervo;
import com.example.livrariacdf.model.Livro;
import com.google.android.material.snackbar.Snackbar;

public class FocoActivity extends AppCompatActivity {

    private int id;
    private String recursoImg;
    private Livro livro;

    private TextView tvTitulo;
    private TextView tvAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_foco);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Inclui a actionbar e o botão 'Up' (representado por uma seta para esquerda!)
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);



        // Obtendo id do livro
        Intent intent = getIntent();
        id = (int) intent.getSerializableExtra("ID");

        // Obtendo instância do livro selecionado
        livro = Acervo.getInstance().getLivroById(id);

        // Carregando a imagem do livro na toolbar (de fato, na image view associada)
        ImageView imageView = findViewById(R.id.capa_livro);
        recursoImg = livro.recursoImg;
        if (!livro.recursoImg.equals("")) {
            int imgResource = getResources().getIdentifier(livro.recursoImg, "drawable", getPackageName());
            imageView.setImageResource(imgResource);
        }

        // Alterando o título da toolbar
        actionBar.setTitle(livro.titulo);

        // Preenchendo os atributos do livro
        tvTitulo = findViewById(R.id.tv_nome);
        tvAutor = findViewById(R.id.tv_autor);
        tvTitulo.setText(livro.titulo);
        tvAutor.setText(livro.autor);


    }

    public void clickEnviar(View view) {
        CharSequence text = "Obrigado por comprar em nossa loja!";
        int duration = Snackbar.LENGTH_SHORT;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main), text, duration);
        snackbar.setAction("Reverter", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(FocoActivity.this, "Desfeito!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        snackbar.show();
    }
}