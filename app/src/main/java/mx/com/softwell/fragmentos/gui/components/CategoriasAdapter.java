package mx.com.softwell.fragmentos.gui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.com.softwell.fragmentos.R;
import mx.com.softwell.fragmentos.model.CategoriaJuego;
import mx.com.softwell.fragmentos.model.Juego;

public class CategoriasAdapter extends RecyclerView.Adapter <CategoriasAdapter.ViewHolder> {

    private List<CategoriaJuego> categoriaJuegos;
    private Context context;

    public CategoriasAdapter(List<CategoriaJuego> categoriaJuegos) {
        this.categoriaJuegos = categoriaJuegos;
    }

    @NonNull
    @Override
    public CategoriasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_categoria,parent,false);
        context = parent.getContext();
        return new CategoriasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriasAdapter.ViewHolder holder, int position) {
        CategoriaJuego categoriaJuego = categoriaJuegos.get(position);
        String imgUri = "@drawable/" + categoriaJuego.getImagen();
        int imgResource = context.getResources().getIdentifier(imgUri,null,context.getPackageName());
        holder.imgCategoria.setImageResource(imgResource);
        holder.txtNombreCategoria.setText(categoriaJuego.getNombreCategoria());
    }

    @Override
    public int getItemCount() {
        return categoriaJuegos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        private AppCompatImageView imgCategoria;
        private TextView txtNombreCategoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategoria = itemView.findViewById(R.id.imgCategoria);
            txtNombreCategoria= itemView.findViewById(R.id.txtNombreCategoria);
            this.view = itemView;
        }
    }
}
