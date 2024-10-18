package com.esferas;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esferas.canvas.MeuCanvas;
import com.esferas.globo.Globo;

public class MainActivity extends AppCompatActivity {

    private boolean girando = false;

    private MeuCanvas mc;

    private SeekBar sbHorizontal;
    private SeekBar sbVertical;
    private SeekBar sbRot;

    private Handler handler;

    private Globo globo;


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

        globo = Globo.getInstance();

        handler = new Handler();

        sbHorizontal = findViewById(R.id.sb_horizontal);
        sbVertical = findViewById(R.id.sb_vertical);
        sbRot = findViewById(R.id.sb_rot);

        mc = findViewById(R.id.meu_canvas);

        this.iniciaTemporizador();
    }


    private void iniciaTemporizador() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                globo.variaOmega(Float.valueOf(sbRot.getProgress()) / 5000);
                globo.setEscalaX(Float.valueOf(sbHorizontal.getProgress()) / 50);
                globo.setEscalaY(Float.valueOf(sbVertical.getProgress()) / 50);
                mc.atualizaImagem();
                if (girando) {
                    handler.postDelayed(this, 15);
                }

            }
        });
    }

    public void iniciar(View view) {
        girando = true;
        iniciaTemporizador();
    }

    public void parar(View view) {
        girando = false;
    }


}