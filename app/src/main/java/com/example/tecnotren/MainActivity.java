package com.example.tecnotren;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tecnotren.adaptadores.ListaTrenesAdapter;
import com.example.tecnotren.db.DbHelper;
import com.example.tecnotren.db.DbTrenes;
import com.example.tecnotren.entidades.Trenes;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView txtBuscar;
    RecyclerView listaTrenes;
    ArrayList<Trenes> listaArrayTrenes;
    ListaTrenesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtBuscar = findViewById(R.id.txtBuscar);
        listaTrenes = findViewById(R.id.listaTrenes);
        listaTrenes.setLayoutManager(new LinearLayoutManager(this));

        DbTrenes dbTrenes = new DbTrenes(MainActivity.this);

        listaArrayTrenes = new ArrayList<>();

        adapter = new ListaTrenesAdapter(dbTrenes.mostrarTrenes());
        listaTrenes.setAdapter(adapter);

        txtBuscar.setOnQueryTextListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
            nuevoRegistro();
            return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }
}