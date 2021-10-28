package br.com.alura.ceep.ui.recyclerview.formularionota.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;

public class FormularioNotaAdapter extends RecyclerView.Adapter<FormularioNotaAdapter.ViewHolder> {

    private final Context context;
    private final List<Integer> coresList;
    private final OnColorClickListener mOnColorClickListener;

    public FormularioNotaAdapter(Context context, List<Integer> coresList, OnColorClickListener onColorClickListener) {
        this.context = context;
        this.coresList = coresList;
        this.mOnColorClickListener = onColorClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cor, parent, false);
        return new ViewHolder(view, mOnColorClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivCor.setColorFilter(coresList.get(position));
    }

    @Override
    public int getItemCount() {
        return coresList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivCor;
        private final OnColorClickListener onColorClickListener;

        public ViewHolder(@NonNull View itemView, OnColorClickListener onColorClickListener) {
            super(itemView);
            ivCor = itemView.findViewById(R.id.iv_cor_selecionada);
            this.onColorClickListener = onColorClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onColorClickListener.onColorClick(getBindingAdapterPosition());
        }
    }

    public interface OnColorClickListener {
        void onColorClick(int posicao);
    }
}