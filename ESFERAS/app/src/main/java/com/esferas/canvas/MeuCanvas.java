package com.esferas.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.esferas.globo.Globo;
import com.esferas.globo.Particula;

public class MeuCanvas extends View {

    private float cX;
    private float cY;
    private int cvWidth;
    private int cvHeight;

    private Paint paint;

    private Globo globo;


    public MeuCanvas(Context context) {
        super(context);
    }

    public MeuCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);

        globo = Globo.getInstance();

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        this.cX = (float) cvWidth / 2;
        this.cY = (float) cvHeight / 2;
        this.desenhaTras(canvas);
        this.desenhaFrente(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.cvWidth = w;
        this.cvHeight = h;
        invalidate();
    }

    public void atualizaImagem() {
        invalidate();
    }


    // Desenha bolinhas de trás
    private void desenhaTras(Canvas canvas) {
        float x;
        float y;
        float r;
        float teta;
        float h;
        float sx = (float) globo.getEscalaX();
        float sy = (float) globo.getEscalaY();
        for (Particula p : globo.getParcitulasAtras()) {
            r = (float) p.pontoPolar.r;
            teta = (float) p.pontoPolar.teta;
            h = (float) p.h;
            x = (float) (cX + sx * r * Math.cos(teta));
            y = (float) (cY - r * Math.sin(teta) / 5 + h * sy);
            paint.setColor(Color.parseColor(p.cor.toString()));
            canvas.drawCircle(x, y, (float) p.tamanho, paint);
        }
    }

    // Desenha as bolinhas da frente
    private void desenhaFrente(Canvas canvas) {
        paint.setColor(Color.RED);
        float x;
        float y;
        float r;
        float teta;
        float h;
        float sx = (float) globo.getEscalaX();
        float sy = (float) globo.getEscalaY();
        for (Particula p : globo.getParticulasFrente()) {
            r = (float) p.pontoPolar.r;
            teta = (float) p.pontoPolar.teta;
            h = (float) p.h;
            x = (float) (cX + sx * r * Math.cos(teta));
            y = (float) (cY - r * Math.sin(teta) / 5 + h * sy);
            paint.setColor(Color.parseColor(p.cor.toString()));
            canvas.drawCircle(x, y, (float) p.tamanho, paint);
        }
    }
}
