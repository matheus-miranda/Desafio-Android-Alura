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

    public FormularioNotaAdapter(Context context, List<Integer> coresList) {
        this.context = context;
        this.coresList = coresList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_cor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ivCor.setColorFilter(coresList.get(position));
    }

    @Override
    public int getItemCount() {
        return coresList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivCor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCor = itemView.findViewById(R.id.iv_cor_selecionada);
        }
    }
}