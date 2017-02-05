package com.miramicodigo.toddler.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.miramicodigo.toddler.R;
import com.miramicodigo.toddler.model.entity.Preguntas;
import com.miramicodigo.toddler.presenter.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gusn8 on 03-02-17.
 */

public class AdapterPregunta extends RecyclerView.Adapter<AdapterPregunta.PreguntaHolder> {

    private List<Preguntas> preguntasList;
    private int itemLayout;
    private RecyclerItemClickListener recyclerItemClickListener;
    private Context context;
    private int position;

    public void setRecyclerItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    public AdapterPregunta() {
    }

    public AdapterPregunta(List<Preguntas> listaPreguntas, int itemLayout, Context con) {
        this.preguntasList = listaPreguntas;
        this.itemLayout = itemLayout;
        this.context = con;
    }

    @Override
    public AdapterPregunta.PreguntaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new PreguntaHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterPregunta.PreguntaHolder holder, int position) {
        final Preguntas pregunta = preguntasList.get(position);
        holder.tvPregunta.setText(pregunta.getPregunta());
        holder.tvDescripcion.setText(pregunta.getDescripcion());
        //cambiaValor(holder);
    }

    public void cambiaValor(AdapterPregunta.PreguntaHolder holder) {
        int valor = Integer.parseInt(holder.tvAdapterPuntaje.getText().toString());
        String desc = "";
        int color = 0;
        if (valor == 1) {
            valor = valor + 1;
            desc = "En proceso";
            color = Color.parseColor("#F2930C");
        }else {
            if(valor == 2) {
                valor = valor + 1;
                desc = "Desarrollado";
                color = Color.parseColor("#4B9609");
            } else {
                valor = 1;
                desc = "Preocupante";
                color = Color.parseColor("#CC060C");
            }
        }
        preguntasList.get(position).setPuntaje(valor);
        holder.tvAdapterPuntaje.setText(valor+"");
        holder.tvAdapterPuntajeTexto.setText(desc);
        holder.tvAdapterPuntaje.setTextColor(color);
        holder.tvAdapterPuntajeTexto.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return preguntasList.size();
    }

    public class PreguntaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_preguntas_pregunta)
        TextView tvPregunta;
        @BindView(R.id.tv_preguntas_descripcion)
        TextView tvDescripcion;
        @BindView(R.id.tv_adapter_puntaje)
        TextView tvAdapterPuntaje;
        @BindView(R.id.tv_adapter_puntaje_texto)
        TextView tvAdapterPuntajeTexto;

        public PreguntaHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (recyclerItemClickListener != null) {
                recyclerItemClickListener.onItemClickListener(getAdapterPosition());
                position = getAdapterPosition();
                cambiaValor(this);
            }

        }
    }
}
