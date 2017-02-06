package com.miramicodigo.toddler.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.miramicodigo.toddler.R;
import com.miramicodigo.toddler.ui.components.DialogoDetalle;

import butterknife.BindView;
import butterknife.OnClick;

public class DatosActivity extends BaseActivity {

    @BindView(R.id.tiet_datos_nombre)
    TextInputEditText tietNombre;
    @BindView(R.id.tiet_datos_ci)
    TextInputEditText tietCI;
    @BindView(R.id.tiet_datos_edad)
    TextInputEditText tietEdad;
    @BindView(R.id.tiet_datos_nombretutor)
    TextInputEditText tietNombreTutor;

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
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra("nombre", tietNombre.getText().toString());
        intent.putExtra("ci", tietCI.getText().toString());
        intent.putExtra("edad", tietEdad.getText().toString());
        intent.putExtra("tutor", tietNombreTutor.getText().toString());
        startActivity(intent);
    }
}
