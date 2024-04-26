package com.example.tecnotren;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tecnotren.db.DbTrenes;
import com.example.tecnotren.entidades.Trenes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtSerie, txtServicio, txtNombre, txtTraccion, txtVelocidad, txtAnchovia, txtPlazas, txtPuestaservicio, txtUnidades;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Trenes tren;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

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
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbTrenes dbTrenes = new DbTrenes(EditarActivity.this);
        tren = dbTrenes.verTren(id);

        if(tren != null){
            txtSerie.setText(tren.getSerie());
            txtServicio.setText(tren.getServicio());
            txtNombre.setText(tren.getNombre());
            txtTraccion.setText(tren.getTraccion());
            txtVelocidad.setText(tren.getVelocidad());
            txtAnchovia.setText(tren.getAnchovia());
            txtPlazas.setText(tren.getPlazas());
            txtPuestaservicio.setText(tren.getPuestaservicio());
            txtUnidades.setText(tren.getUnidades());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtSerie.getText().toString().equals("")) {
                    correcto = dbTrenes.editarTren(id, txtSerie.getText().toString(), txtServicio.getText().toString(), txtNombre.getText().toString(), txtTraccion.getText().toString(), txtVelocidad.getText().toString(), txtAnchovia.getText().toString(), txtPlazas.getText().toString(), txtPuestaservicio.getText().toString(), txtUnidades.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE INSERTAR LA SERIE", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
