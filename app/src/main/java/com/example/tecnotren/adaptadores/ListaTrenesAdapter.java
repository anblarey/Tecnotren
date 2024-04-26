package com.example.tecnotren.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tecnotren.R;
import com.example.tecnotren.VerActivity;
import com.example.tecnotren.entidades.Trenes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaTrenesAdapter extends RecyclerView.Adapter<ListaTrenesAdapter.TrenViewHolder> {

    ArrayList<Trenes> listaTrenes;
    ArrayList<Trenes> listaOriginal;

    public ListaTrenesAdapter(ArrayList<Trenes> listaTrenes){

        this.listaTrenes = listaTrenes;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaTrenes);
    }

    @NonNull
    @Override
    public TrenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_tren, null, false);
        return new TrenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrenViewHolder holder, int position) {
        holder.viewSerie.setText(listaTrenes.get(position).getSerie());
        holder.viewServicio.setText(listaTrenes.get(position).getServicio());
        holder.viewNombre.setText(listaTrenes.get(position).getNombre());
        holder.viewTraccion.setText(listaTrenes.get(position).getTraccion());
        holder.viewVelocidad.setText(listaTrenes.get(position).getVelocidad());
        holder.viewAnchovia.setText(listaTrenes.get(position).getAnchovia());
        holder.viewPlazas.setText(listaTrenes.get(position).getPlazas());
        holder.viewPuestaservicio.setText(listaTrenes.get(position).getPuestaservicio());
        holder.viewUnidades.setText(listaTrenes.get(position).getUnidades());

    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            listaTrenes.clear();
            listaTrenes.addAll(listaOriginal);
        } else {
            List<Trenes> collection = listaTrenes.stream().filter(i -> i.getSerie().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
            listaTrenes.clear();
            listaTrenes.addAll(collection);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaTrenes.size();
    }

    public class TrenViewHolder extends RecyclerView.ViewHolder {

        TextView viewSerie, viewServicio, viewNombre, viewTraccion, viewVelocidad, viewAnchovia, viewPlazas, viewPuestaservicio, viewUnidades;

        public TrenViewHolder(@NonNull View itemView) {
            super(itemView);

            viewSerie = itemView.findViewById(R.id.viewSerie);
            viewServicio = itemView.findViewById(R.id.viewServicio);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTraccion = itemView.findViewById(R.id.viewTraccion);
            viewVelocidad = itemView.findViewById(R.id.viewVelocidad);
            viewAnchovia = itemView.findViewById(R.id.viewAnchovia);
            viewPlazas = itemView.findViewById(R.id.viewPlazas);
            viewPuestaservicio = itemView.findViewById(R.id.viewPuestaservicio);
            viewUnidades = itemView.findViewById(R.id.viewUnidades);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaTrenes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
