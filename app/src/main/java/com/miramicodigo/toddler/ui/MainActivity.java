package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.miramicodigo.toddler.R;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_main_logo)
    ImageView imageView;

    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mainActivity = this;

        injectViews();

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        imageView.startAnimation(animation);
    }

    @OnClick(R.id.btnEmpezar)
    public void empezar() {
        startActivity(new Intent(getApplicationContext(), DatosActivity.class));
    }
}
