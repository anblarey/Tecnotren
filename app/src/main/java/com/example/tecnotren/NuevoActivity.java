package com.example.tecnotren;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tecnotren.db.DbTrenes;

public class NuevoActivity extends AppCompatActivity {

    EditText txtSerie, txtServicio, txtNombre, txtTraccion, txtVelocidad, txtAnchovia, txtPlazas, txtPuestaservicio, txtUnidades;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtSerie = findViewById(R.id.txtSerie);
        txtServicio = findViewById(R.id.txtServicio);
        txtNombre = findViewById(R.id.txtNombre);
        txtTraccion = findViewById(R.id.txtTraccion);
        txtVelocidad = findViewById(R.id.txtVelocidad);
        txtAnchovia = findViewById(R.id.txtAnchovia);
        txtPlazas = findViewById(R.id.txtPlazas);
        txtPuestaservicio = findViewById(R.id.txtPuestaservicio);
        txtUnidades = findViewById(R.id.txtUnidades);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbTrenes dbTrenes = new DbTrenes(NuevoActivity.this);
                long id = dbTrenes.insertaTren(txtSerie.getText().toString(), txtServicio.getText().toString(), txtNombre.getText().toString(), txtTraccion.getText().toString(), txtVelocidad.getText().toString(), txtAnchovia.getText().toString(), txtPlazas.getText().toString(), txtPuestaservicio.getText().toString(), txtUnidades.getText().toString());

                if(id > 0){
                    Toast.makeText(NuevoActivity.this, "TREN REGISTRADO", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(NuevoActivity.this, "ERROR AL REGISTRAR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar(){
        txtSerie.setText("");
        txtServicio.setText("");
        txtNombre.setText("");
        txtTraccion.setText("");
        txtVelocidad.setText("");
        txtAnchovia.setText("");
        txtPlazas.setText("");
        txtPuestaservicio.setText("");
        txtUnidades.setText("");
    }
}