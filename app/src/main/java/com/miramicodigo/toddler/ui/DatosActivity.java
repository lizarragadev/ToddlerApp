package com.miramicodigo.toddler.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle("Información");

        injectViews();
        showEditDialog();
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        DialogoDetalle editNameDialogFragment = DialogoDetalle.newInstance("");
        editNameDialogFragment.show(fm, "fragment_edit_name");
    }

    @OnClick(R.id.btn_datos_siguiente)
    public void goToMain(Button button) {
        if(valida()) {
            if (estaConectado(coordinatorLayout)) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.putExtra("nombre", tietNombre.getText().toString());
                intent.putExtra("ci", tietCI.getText().toString());
                intent.putExtra("edad", tietEdad.getText().toString());
                intent.putExtra("tutor", tietNombreTutor.getText().toString());
                startActivity(intent);
                DatosActivity.this.finish();
            }
        } else {
            showMessage(coordinatorLayout, "Debe llenar todos los datos", 1, 2000);
        }
    }

    public boolean valida() {
        if(tietNombre.getText().toString().equals("") || tietCI.getText().toString().equals("") ||
                tietEdad.getText().toString().equals("") || tietNombreTutor.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_datos_limpiar:
                limpiarFormulario();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_datos, menu);
        return true;
    }

    public void limpiarFormulario() {
        tietNombre.setText("");
        tietCI.setText("");
        tietEdad.setText("");
        tietNombreTutor.setText("");
        tietNombre.requestFocus();
    }


}
