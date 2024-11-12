package com.todolist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.todolist.adapters.TarefaAdapter;
import com.todolist.database.Dao;
import com.todolist.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private TarefaAdapter tarefaAdapter;
    private RecyclerView recyclerView;
    private EditText etTarefa;
    private LinearLayout linlayTop;
    private ImageView imgviewUndo;
    private ImageView imgviewGo;
    private CardView cardviewTexto;
    private ViewGroup.MarginLayoutParams layoutParams;

    @SuppressLint("ClickableViewAccessibility")
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

        Dao.getInstance().setContext(MainActivity.this);
        Dao.getInstance().obterTodosOsRegistros(); // para a exibição inicial

        tarefaAdapter = new TarefaAdapter();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tarefaAdapter);
        imgviewUndo = findViewById(R.id.imgv_undo);
        imgviewGo = findViewById(R.id.imgv_go);
        cardviewTexto = findViewById(R.id.cardview_texto);
        layoutParams = (ViewGroup.MarginLayoutParams) cardviewTexto.getLayoutParams();
        etTarefa = findViewById(R.id.et_tarefa);
        linlayTop = findViewById(R.id.linlay_top);

        // Listener para evento em que o input da nova tarefa ganha foco
        etTarefa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    linlayTop.setVisibility(View.GONE);
                    etTarefa.setCursorVisible(true);
                    estadoOriginalEntradaTarefas(false);
                }
                return false;
            }
        });

        // Listener implementado da interface contida no TarefaAdapter
        tarefaAdapter.setListener(new TarefaAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Tarefa tarefa = Dao.getInstance().getTarefas().get(position);
                if (Dao.getInstance().marcarOuDesmarcarTarefa(tarefa)) {
                    tarefa.marcado = !tarefa.marcado;
                }
                tarefaAdapter.notifyItemChanged(position);
            }
        });

    }


    // Para remover todas as tarefas marcadas
    @SuppressLint("NotifyDataSetChanged")
    public void onClickRemoverMarcados(View view) {
        if (Dao.getInstance().apagarMarcados()) {
            tarefaAdapter.atualizaTarefas(Dao.getInstance().getTarefas());
            tarefaAdapter.notifyDataSetChanged();
        } else {
            this.toastBdException();
        }
    }

    // Para restaurar os dados de exemplo e remover todos os demais
    @SuppressLint("NotifyDataSetChanged")
    public void onClickReset(View view) {
        if (Dao.getInstance().reset()) {
            tarefaAdapter.notifyDataSetChanged();
        } else {
            this.toastBdException();
        }
    }

    // Para adicionar uma nova tarefa
    @SuppressLint("NotifyDataSetChanged")
    public void onClickNovaTarefa(View view) {
        String texto = etTarefa.getText().toString().trim();
        if (texto.length() == 0) return;
        etTarefa.setText("");
        etTarefa.setCursorVisible(false);
        if (Dao.getInstance().novaTarefa(texto)) {
            linlayTop.setVisibility(View.VISIBLE);
            tarefaAdapter.notifyDataSetChanged();
            this.estadoOriginalEntradaTarefas(true);
        } else {
            this.toastBdException();
        }
    }

    // Onclick para voltar à configuração original do input das tarefas
    public void onClickBackEdit(View view) {
        linlayTop.setVisibility(View.VISIBLE);
        etTarefa.setText("");
        etTarefa.setCursorVisible(false);
        this.estadoOriginalEntradaTarefas(true);
    }

    // Para marcar todas as tarefas
    @SuppressLint("NotifyDataSetChanged")
    public void onClickMarcarTudo(View view) {
        if (Dao.getInstance().marcarTudo()) {
            tarefaAdapter.notifyDataSetChanged();
        } else {
            this.toastBdException();
        }
    }

    // Para desmarcar todas as tarefas
    @SuppressLint("NotifyDataSetChanged")
    public void onClickDesmarcarTudo(View view) {
        if(Dao.getInstance().desmarcarTudo()) {
            tarefaAdapter.notifyDataSetChanged();
        } else {
            this.toastBdException();
        }
    }

    // Restaura o aspecto (dimensões) do input das tarefas
    private void estadoOriginalEntradaTarefas(boolean pequeno) {
        if (pequeno) {
            imgviewUndo.setVisibility(View.GONE);
            imgviewGo.setVisibility(View.GONE);
            layoutParams.setMargins(128, 32, 128, 32);
        } else {
            imgviewUndo.setVisibility(View.VISIBLE);
            imgviewGo.setVisibility(View.VISIBLE);
            layoutParams.setMargins(16, 16, 16, 16);
        }
        cardviewTexto.setLayoutParams(layoutParams);
    }

    // Toast para evidenciar falhas na persistência no bd
    private void toastBdException() {
        Toast toast = Toast.makeText(this, "Problema no banco de dados", Toast.LENGTH_SHORT);
        toast.show();
    }
}