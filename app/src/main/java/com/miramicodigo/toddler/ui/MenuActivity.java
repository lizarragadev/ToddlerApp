package com.miramicodigo.toddler.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miramicodigo.toddler.R;
import com.miramicodigo.toddler.data.entity.EvaluarEntity;
import com.miramicodigo.toddler.model.entity.Preguntas;
import com.miramicodigo.toddler.presenter.ToddlerPresenter;
import com.miramicodigo.toddler.view.ToddlerView;

import java.io.Serializable;
import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MenuActivity extends BaseActivity implements ToddlerView {

    private static final String TAG = "MenuActivity";
    private ToddlerPresenter toddlerPresenter;
    private List<Preguntas> listaPreguntas;
    private EvaluarEntity evaluarEntityRespuesta;
    private int resMG = 0;
    private int resMF = 0;
    private int resLeng = 0;

    private String nombre;
    private int ci;
    private int edad;
    private String tutor;

    @BindView(R.id.pbCarga)
    ProgressBar progressBar;
    @BindView(R.id.flCarga)
    FrameLayout frameLayout;
    @BindView(R.id.cl_menu_base)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.ll_base)
    LinearLayout linearLayoutBase;

    @BindView(R.id.tv_menu_mg_preguntas)
    TextView tvMGPreguntas;
    @BindView(R.id.tv_menu_mg_resultado)
    TextView tvMGResultado;
    @BindView(R.id.rb_menu_mg_rating)
    RatingBar rbMGRating;

    @BindView(R.id.tv_menu_mf_preguntas)
    TextView tvMFPreguntas;
    @BindView(R.id.tv_menu_mf_resultado)
    TextView tvMFResultado;
    @BindView(R.id.rb_menu_mf_rating)
    RatingBar rbMFRating;

    @BindView(R.id.tv_menu_leng_preguntas)
    TextView tvLengPreguntas;
    @BindView(R.id.tv_menu_leng_resultado)
    TextView tvLengResultado;
    @BindView(R.id.rb_menu_leng_rating)
    RatingBar rbLengRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        injectViews();
        Bundle bundle = getIntent().getExtras();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Evaluacion");

        nombre = bundle.getString("nombre", "").toString();
        ci = Integer.parseInt(bundle.getString("ci"));
        edad = Integer.parseInt(bundle.getString("edad"));
        tutor = bundle.getString("tutor", "").toString();

        linearLayoutBase.setVisibility(View.GONE);
        toddlerPresenter = new ToddlerPresenter();
        toddlerPresenter.addView(this);
        toddlerPresenter.obtienePreguntas();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showLoading() {
        linearLayoutBase.setVisibility(View.GONE);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
        frameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
        frameLayout.setVisibility(View.GONE);
        linearLayoutBase.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void gotoMain(List<Preguntas> preguntas) {
        listaPreguntas = preguntas;
        setCantidadPreguntas();
    }

    @Override
    public void gotoMainEvaluacion(EvaluarEntity evaluarEntity) {
        evaluarEntityRespuesta = evaluarEntity;
        Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
        intent.putExtra("objeto", (Serializable) evaluarEntity);
        intent.putExtra("resMG", resMG+"");
        intent.putExtra("resMF", resMF+"");
        intent.putExtra("resLeng", resLeng+"");
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessageError(String message) {

    }

    @OnClick(R.id.card_view_mg)
    void goTomotorGrueso() {
        enviaPreguntas(MOTOR_GRUESO);
    }

    @OnClick(R.id.card_view_mf)
    void goTomotorFino() {
        enviaPreguntas(MOTOR_FINO);
    }

    @OnClick(R.id.card_view_leng)
    void goToLenguaje() {
        enviaPreguntas(LENGUAJE);
    }

    public void setCantidadPreguntas() {
        int mg, mf, l;
        mg = mf = l = 0;
        List<Preguntas> aux = new ArrayList<Preguntas>();
        for (Preguntas pre : listaPreguntas) {
            if(pre.getTipo() == MOTOR_GRUESO) {
                mg++;
            } else {
                if(pre.getTipo() == MOTOR_FINO) {
                    mf++;
                } else {
                    l++;
                }
            }
        }
        tvMGPreguntas.setText(mg+" preguntas.");
        tvMFPreguntas.setText(mf+" preguntas.");
        tvLengPreguntas.setText(l+" preguntas.");
    }

    public void enviaPreguntas(int tipo) {
        Intent intent = new Intent(getApplicationContext(), PreguntasActivity.class);
        List<Preguntas> aux = new ArrayList<Preguntas>();
        for (Preguntas pre : listaPreguntas) {
            if(pre.getTipo() == tipo) {
                aux.add(pre);
            }
        }
        intent.putExtra("listaPreguntas", (Serializable) aux);
        intent.putExtra("tipoeval", tipo);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            showMessage(coordinatorLayout, "Se Cancelo", 2, 1000);
        } else {
            int resultado = data.getExtras().getInt("resultado");
            int tipo = data.getIntExtra("tipoeval", 0);
            String msg = "";
            switch (tipo) {
                case MOTOR_GRUESO:
                    msg = "MOTOR GRUESO";
                    break;
                case MOTOR_FINO:
                    msg = "MOTOR FINO";
                    break;
                case LENGUAJE:
                    msg = "DEL LENGUAJE";
                    break;
                case 0:
                    break;
            }
            switch (resultado) {
                case 1:
                    showMessage(coordinatorLayout, "El nivel de desarrollo "+msg+" es PREOCUPANTE.", 1, 4000);
                    break;
                case 2:
                    showMessage(coordinatorLayout, "El nivel de desarrollo "+msg+" es EN PROGRESO.", 2, 4000);
                    break;
                case 3:
                    showMessage(coordinatorLayout, "El nivel de desarrollo "+msg+" es DESARROLLADO.", 3, 4000);
                    break;
            }
            switch (tipo) {
                case MOTOR_GRUESO:
                    resMG = resultado;
                    actualizaMotorGrueso(resultado);
                    break;
                case MOTOR_FINO:
                    resMF = resultado;
                    actualizaMotorFino(resultado);
                    break;
                case LENGUAJE:
                    resLeng = resultado;
                    actualizaLenguaje(resultado);
                    break;
                case 0:
                    break;
            }
        }
    }

    public void actualizaMotorGrueso(int res) {
        int color;
        String valor = "";
        if(res == RES_DESARROLLADO) {
            valor = DESARROLLADO;
            color = ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado);
        } else {
            if(res == RES_EN_PROGRESO) {
                valor = EN_PROGRESO;
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso);
            } else {
                valor = PREOCUPANTE;
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante);
            }
        }
        tvMGResultado.setTextColor(color);
        tvMGResultado.setText(valor);
        rbMGRating.setRating(res);
    }

    public void actualizaMotorFino(int res) {
        int color;
        String valor = "";
        if(res == RES_DESARROLLADO) {
            valor = DESARROLLADO;
            color = ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado);
        } else {
            if(res == RES_EN_PROGRESO) {
                valor = EN_PROGRESO;
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso);
            } else {
                valor = PREOCUPANTE;
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante);
            }
        }
        tvMFResultado.setTextColor(color);
        tvMFResultado.setText(valor);
        rbMFRating.setRating(res);
    }

    public void actualizaLenguaje(int res) {
        int color;
        String valor = "";
        if(res == RES_DESARROLLADO) {
            valor = DESARROLLADO;
            color = ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado);
        } else {
            if(res == RES_EN_PROGRESO) {
                valor = EN_PROGRESO;
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso);
            } else {
                valor = PREOCUPANTE;
                color = ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante);
            }
        }
        tvLengResultado.setTextColor(color);
        tvLengResultado.setText(valor);
        rbLengRating.setRating(res);
    }

    @OnClick(R.id.btn_menu_evaluaciongeneral)
    void evaluaionGeneral() {
        if(validaEvaluacion()) {
            if(estaConectado(coordinatorLayout)){
                toddlerPresenter.getEvaluacion(nombre, ci, edad, tutor, resMG, resMF, resLeng);
            }
        } else {
            showMessage(coordinatorLayout, "Debe realizar las 3 evaluaciones", 2, 2000);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                lanzaMensaje();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public boolean validaEvaluacion() {
        if(resMG == 0 || resMF == 0 || resLeng == 0) {
            return false;
        }
        return true;
    }

    public void lanzaMensaje() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_info_details)
                .setTitle(getString(R.string.resultado_dialog_cerrar_app2))
                .setMessage(getString(R.string.resultado_dialog_cerrar_app1))
                .setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override
    public void onBackPressed() {
        lanzaMensaje();
    }

}
