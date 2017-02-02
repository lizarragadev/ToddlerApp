package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.os.Bundle;

import com.miramicodigo.toddler.R;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectViews();
    }

    @OnClick(R.id.btnEmpezar)
    public void empezar() {
        System.out.println("LLEGA");
        startActivity(new Intent(getApplicationContext(), DatosActivity.class));
    }
}
