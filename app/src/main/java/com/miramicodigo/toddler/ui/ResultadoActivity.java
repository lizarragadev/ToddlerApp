package com.miramicodigo.toddler.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Rating;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.miramicodigo.toddler.R;
import com.miramicodigo.toddler.data.entity.EvaluarEntity;

import butterknife.BindView;
import butterknife.OnClick;

public class ResultadoActivity extends BaseActivity {
    private int resMG;
    private int resMF;
    private int resLeng;
    private EvaluarEntity evaluarEntity;

    @BindView(R.id.tv_resultado_nombre)
    TextView tvNombre;
    @BindView(R.id.tv_resultado_ci)
    TextView tvCi;
    @BindView(R.id.tv_resultado_edad)
    TextView tvEdad;
    @BindView(R.id.tv_resultado_tutor)
    TextView tvTutor;
    @BindView(R.id.tv_resultado_grueso)
    TextView tvGrueso;
    @BindView(R.id.tv_resultado_fino)
    TextView tvFino;
    @BindView(R.id.tv_resultado_lenguaje)
    TextView tvLenguaje;
    @BindView(R.id.rb_resultado_grueso_rating)
    RatingBar rbGrueso;
    @BindView(R.id.rb_resultado_fino_rating)
    RatingBar rbFino;
    @BindView(R.id.rb_resultado_lenguaje_rating)
    RatingBar rbLenguaje;
    @BindView(R.id.iv_resultado_grueso_gesto)
    ImageView ivGrueso;
    @BindView(R.id.iv_resultado_fino_gesto)
    ImageView ivFino;
    @BindView(R.id.iv_resultado_lenguaje_gesto)
    ImageView ivLenguaje;

    @BindView(R.id.rb_resultado_global_rating)
    RatingBar rbGlobal;
    @BindView(R.id.tv_resultado_global)
    TextView tvGlobal;
    @BindView(R.id.iv_resultado_global_gesto)
    ImageView ivGlobal;
    @BindView(R.id.tv_resultado_consejo)
    TextView tvConsejo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_resultado);

        injectViews();

        Bundle bundle = getIntent().getExtras();
        resMG = Integer.parseInt(bundle.getString("resMG"));
        resMF = Integer.parseInt(bundle.getString("resMF"));
        resLeng = Integer.parseInt(bundle.getString("resLeng"));

        evaluarEntity = (EvaluarEntity) getIntent().getSerializableExtra("objeto");

        llenaViews();

    }

    public void llenaViews() {
        tvNombre.setText(evaluarEntity.getNombre().toString());
        tvCi.setText(evaluarEntity.getCi()+" (CI)");
        tvEdad.setText(evaluarEntity.getEdad()+" años.");
        tvTutor.setText(evaluarEntity.getNombreTutor());
        llenaResultado();
        rbGlobal.setEnabled(false);
        if (evaluarEntity.getResultado().toString().equals(DESARROLLADO)) {
            tvGlobal.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado));
            tvGlobal.setText(DESARROLLADO);
            rbGlobal.setRating(RES_DESARROLLADO);
            ivGlobal.setImageResource(R.drawable.ic_mood_black_24dp);
            tvConsejo.setText(getString(R.string.resultado_msg_desarrollado));
        } else {
            if (evaluarEntity.getResultado().toString().equals(EN_PROGRESO)) {
                tvGlobal.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso));
                tvGlobal.setText(EN_PROGRESO);
                rbGlobal.setRating(RES_EN_PROGRESO);
                ivGlobal.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp);
                tvConsejo.setText(getString(R.string.resultado_msg_enprogreso));
            } else {
                tvGlobal.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante));
                tvGlobal.setText(PREOCUPANTE);
                rbGlobal.setRating(RES_PREOCUPANTE);
                ivGlobal.setImageResource(R.drawable.ic_mood_bad_black_24dp);
                tvConsejo.setText(getString(R.string.resultado_msg_preocupante));
            }
        }
    }

    public void llenaResultado() {
        rbGrueso.setRating(resMG);
        rbFino.setRating(resMF);
        rbLenguaje.setRating(resLeng);

        if (resMG == RES_DESARROLLADO) {
            tvGrueso.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado));
            tvGrueso.setText(DESARROLLADO);
            ivGrueso.setImageResource(R.drawable.ic_mood_black_24dp);
        } else {
            if (resMG == RES_EN_PROGRESO) {
                tvGrueso.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso));
                tvGrueso.setText(EN_PROGRESO);
                ivGrueso.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp);
            } else {
                tvGrueso.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante));
                tvGrueso.setText(PREOCUPANTE);
                ivGrueso.setImageResource(R.drawable.ic_mood_bad_black_24dp);
            }
        }

        if (resMF == RES_DESARROLLADO) {
            tvFino.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado));
            tvFino.setText(DESARROLLADO);
            ivFino.setImageResource(R.drawable.ic_mood_black_24dp);
        } else {
            if (resMF == RES_EN_PROGRESO) {
                tvFino.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso));
                tvFino.setText(EN_PROGRESO);
                ivFino.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp);
            } else {
                tvFino.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante));
                tvFino.setText(PREOCUPANTE);
                ivFino.setImageResource(R.drawable.ic_mood_bad_black_24dp);
            }
        }

        if (resLeng == RES_DESARROLLADO) {
            tvLenguaje.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_desarrollado));
            tvLenguaje.setText(DESARROLLADO);
            ivLenguaje.setImageResource(R.drawable.ic_mood_black_24dp);
        } else {
            if (resLeng == RES_EN_PROGRESO) {
                tvLenguaje.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_enprogreso));
                tvLenguaje.setText(EN_PROGRESO);
                ivLenguaje.setImageResource(R.drawable.ic_sentiment_neutral_black_24dp);
            } else {
                tvLenguaje.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_preocupante));
                tvLenguaje.setText(PREOCUPANTE);
                ivLenguaje.setImageResource(R.drawable.ic_mood_bad_black_24dp);
            }
        }
    }

    @OnClick(R.id.btn_resultado_nuevo)
    void irNuevo() {
        Intent intent = new Intent(getApplicationContext(), DatosActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_resultado_salir)
    void salir() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_menu_info_details)
                .setTitle(getString(R.string.resultado_dialog_cerrar))
                .setMessage(getString(R.string.resultado_dialog_cerrar2))
                .setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                })
                .setNegativeButton("No", null)
                .show();
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
