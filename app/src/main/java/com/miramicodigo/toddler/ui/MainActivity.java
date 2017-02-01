package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miramicodigo.toddler.R;

public class MainActivity extends BaseActivity {

    private Button btnEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DatosActivity.class));
            }
        });
    }

    public void initUI() {
        btnEmpezar = (Button) findViewById(R.id.btnEmpezar);
    }
}
