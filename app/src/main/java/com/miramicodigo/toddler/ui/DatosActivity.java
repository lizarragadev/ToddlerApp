package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.miramicodigo.toddler.R;
import com.miramicodigo.toddler.ui.components.DialogoDetalle;

import butterknife.OnClick;

public class DatosActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        injectViews();
        //showEditDialog();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogoDetalle editNameDialogFragment = DialogoDetalle.newInstance("Gus gus");
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    @OnClick(R.id.btn_datos_siguiente)
    public void goToMain(Button button) {
        System.out.println("HIZO CLICK BOTON");
        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
    }
}
