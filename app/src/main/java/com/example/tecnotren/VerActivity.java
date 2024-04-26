package com.example.tecnotren;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tecnotren.db.DbTrenes;
import com.example.tecnotren.entidades.Trenes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class VerActivity extends AppCompatActivity {

    EditText txtSerie, txtServicio, txtNombre, txtTraccion, txtVelocidad, txtAnchovia, txtPlazas, txtPuestaservicio, txtUnidades;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;

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
        fabEliminar = findViewById(R.id.fabEliminar);

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

        DbTrenes dbTrenes = new DbTrenes(VerActivity.this);
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
            btnGuarda.setVisibility(View.INVISIBLE);
            txtSerie.setInputType(InputType.TYPE_NULL);
            txtServicio.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTraccion.setInputType(InputType.TYPE_NULL);
            txtVelocidad.setInputType(InputType.TYPE_NULL);
            txtAnchovia.setInputType(InputType.TYPE_NULL);
            txtPlazas.setInputType(InputType.TYPE_NULL);
            txtPuestaservicio.setInputType(InputType.TYPE_NULL);
            txtUnidades.setInputType(InputType.TYPE_NULL);
        }
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                builder.setMessage("¿está seguro de eliminar este tren?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                                if(dbTrenes.eliminarTren(id)){
                                    lista();
                                }


                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        }).show();
            }
        });
    }

    private void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}