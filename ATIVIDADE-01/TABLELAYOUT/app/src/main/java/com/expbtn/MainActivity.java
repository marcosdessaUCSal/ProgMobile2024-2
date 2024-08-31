package com.expbtn;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final int NUM_LINHAS = 10;
    private final int NUM_COLUNAS = 10;
    private final String COR_SELECT = "#FFFBC02D";
    private final String COR_UNSELECT = "#44000000";
    private final boolean[] arrayQuads = new boolean[NUM_COLUNAS * NUM_LINHAS];

    private LayoutInflater inflater;
    private TableLayout tableLayout;

    private final HashMap<Integer, Button> btnMap = new HashMap<>();

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

        inflater = LayoutInflater.from(this);
        tableLayout = findViewById(R.id.table_layout);

        int btnId = 0;

        for (int i = 0; i < NUM_LINHAS; i++) {
            TableRow tableRow = new TableRow(this);

            for (int j = 0; j < NUM_COLUNAS; j++) {
                Button btn = (Button) inflater.inflate(R.layout.btn_template,
                        tableRow, false);
                btn.setId(btnId);
                btn.setBackgroundColor(Color.parseColor(COR_UNSELECT));
                tableRow.addView(btn);
                // definindo um listener para o botão
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clicar(view);
                    }
                });
                // armazenando referência para os botões
                btnMap.put(btnId, btn);
                btnId++;
            }

            tableLayout.addView(tableRow);
        }
        reset();

    }

    // Handler para o botão RESET
    public void handleReset(View view) {
        this.reset();
    }

    // Handler para o botão INVERTER
    public void handleInverter(View view) {
        this.inverter();
    }

    // Handler para alterar a cor do quadradinho
    private void clicar(View view) {
        int id = view.getId();
        if (!arrayQuads[id]) {
            view.setBackgroundColor(Color.parseColor(COR_SELECT));
        } else {
            view.setBackgroundColor(Color.parseColor(COR_UNSELECT));
        }
        arrayQuads[id] = !arrayQuads[id];
    }

    // inverte todas as seleções dos quadradinhos
    private void inverter() {
        // TODO: IMPLEMENTAR -> Esta é a sua tarefa. Modifique apenas isto.
    }

    // limpa todos os quadradinhos
    private void reset() {
        Button btn;
        int id;
        for (Map.Entry<Integer, Button> e : btnMap.entrySet()) {
            btn = e.getValue();
            id = e.getKey();
            btn.setBackgroundColor(Color.parseColor(COR_UNSELECT));
            arrayQuads[id] = false;
        }
    }
}