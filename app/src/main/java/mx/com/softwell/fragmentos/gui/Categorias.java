package mx.com.softwell.fragmentos.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.com.softwell.fragmentos.R;
import mx.com.softwell.fragmentos.core.CategoriaController;
import mx.com.softwell.fragmentos.core.JuegoController;
import mx.com.softwell.fragmentos.core.MiscController;
import mx.com.softwell.fragmentos.databinding.FragmentCategoriasBinding;
import mx.com.softwell.fragmentos.databinding.FragmentTopJuegosBinding;
import mx.com.softwell.fragmentos.gui.components.CategoriasAdapter;
import mx.com.softwell.fragmentos.gui.components.JuegosAdapter;
import mx.com.softwell.fragmentos.gui.components.NavigationHost;
import mx.com.softwell.fragmentos.gui.components.NavigationIconClickListener;
import mx.com.softwell.fragmentos.model.CategoriaJuego;
import mx.com.softwell.fragmentos.model.Juego;

public class Categorias extends Fragment {

    private MiscController miscController = MiscController.Instance();
    private CategoriaController categoriaController = CategoriaController.Instance();
    private FragmentCategoriasBinding binding;
    private View view;
    private Context context;
    private static String TAG="Categorias";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Modificar top bar
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobals();
        configView(inflater, container);
        configToolbar();
        configUI();
        configRecycler();
        actualizar();

        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("categoriasFragment", this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentCategoriasBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridCategorias),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }

    private void configUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.findViewById(R.id.gridCategorias).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));
        }
    }

    private void configRecycler() {

        binding.rclvCategorias.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        binding.rclvCategorias.setLayoutManager(layoutManager);
    }

    private void actualizar(){
        miscController.ShowWait(context, "Consultando juegos...");
        categoriaController.GetAll();
    }

    public void actualizar(List<CategoriaJuego> categoriaJuegos){
        binding.rclvCategorias.setAdapter(new CategoriasAdapter(categoriaJuegos));
        miscController.CloseWait();
    }
}
