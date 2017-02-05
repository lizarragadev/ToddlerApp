package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.miramicodigo.toddler.R;
import com.miramicodigo.toddler.model.entity.Preguntas;
import com.miramicodigo.toddler.presenter.RecyclerItemClickListener;
import com.miramicodigo.toddler.ui.adapter.AdapterPregunta;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PreguntasActivity extends BaseActivity implements RecyclerItemClickListener{

    private static final String TAG = "PreguntasActivity";
    private AdapterPregunta adapterPregunta;
    private List<Preguntas> listaPreguntas;
    private static final int DESARROLLADO = 3;
    private static final int EN_PROGRESO = 2;
    private static final int PREOCUPANTE = 1;
    private int tipoeval;

    @BindView(R.id.rv_preguntas_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        injectViews();

        Bundle b = getIntent().getExtras();

        listaPreguntas = (List<Preguntas>)getIntent().getSerializableExtra("listaPreguntas");
        tipoeval = b.getInt("tipoeval");

        adapterPregunta = new AdapterPregunta(listaPreguntas, R.layout.adapter_pregunta, getApplicationContext());

        adapterPregunta.setRecyclerItemClickListener(this);

        recyclerView.setAdapter(adapterPregunta);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClickListener(int position) {
    }

    @OnClick(R.id.btn_preguntas_evaluar)
    void evaluar() {
        Intent intent = new Intent();
        intent.putExtra("resultado", getResultado(listaPreguntas));
        intent.putExtra("tipoeval", tipoeval);
        setResult(RESULT_OK, intent);
        finish();
    }

    public int getResultado(List<Preguntas> preg) {
        int numPreguntas = preg.size();
        int puntajeMaximo = numPreguntas * 3;
        int puntajeMinimo = puntajeMaximo - numPreguntas;
        int rango = Math.round((float)puntajeMinimo / 3);
        int sumaRespuestas = 0;

        for(Preguntas pre : preg) {
            sumaRespuestas += pre.getPuntaje();
        }
        if (sumaRespuestas < (rango + numPreguntas)) {
            return PREOCUPANTE;
        } else {
            if (sumaRespuestas >= ((rango * 2) + numPreguntas)) {
                return DESARROLLADO;
            } else {
                return EN_PROGRESO;
            }
        }
    }
}