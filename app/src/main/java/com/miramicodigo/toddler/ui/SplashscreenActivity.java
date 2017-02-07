package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.miramicodigo.toddler.R;

import butterknife.BindView;

public class SplashscreenActivity extends BaseActivity {

    private Animation animation;
    private Animation animation_letra;

    @BindView(R.id.ivLogo) ImageView ivLogo;
    @BindView(R.id.tv_splash_titulo) TextView tvTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splashscreen);

        injectViews();
        ui();

        ivLogo.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvTitulo.startAnimation(animation_letra);
        animation_letra.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void ui() {
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        animation_letra = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_letra);
    }

}
