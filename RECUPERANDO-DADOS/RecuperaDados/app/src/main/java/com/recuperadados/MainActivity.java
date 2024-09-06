package com.recuperadados;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int digitado;
    private EditText etNumero;

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

        etNumero = findViewById(R.id.et_numero);

        if (savedInstanceState != null) {
            etNumero.setText(String.valueOf(savedInstanceState.getInt("NUMERO")));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!etNumero.getText().toString().isEmpty()) {
            int numero = Integer.parseInt(etNumero.getText().toString());
            outState.putInt("NUMERO", numero);
        }
    }
}