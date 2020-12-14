package mx.com.softwell.fragmentos.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

import mx.com.softwell.fragmentos.R;
import mx.com.softwell.fragmentos.core.FragmentosApplication;
import mx.com.softwell.fragmentos.gui.components.NavigationHost;

public class MainActivity extends AppCompatActivity implements NavigationHost {

    private MaterialButton mbTopgames;
    private MaterialButton mbTopRanked;
    private MaterialButton mbFreeToPlay;
    private MaterialButton mbViejaEscuela;
    private MaterialButton mbCategorias;

    public static HashMap<String, Object> GLOBALS = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbTopgames =findViewById(R.id.mnTopJuegos);
        mbTopRanked=findViewById(R.id.mnTopRanked);
        mbFreeToPlay=findViewById(R.id.mnFreetoPlay);
        mbCategorias=findViewById(R.id.mnCategorias);
        mbViejaEscuela=findViewById(R.id.mnOldSchool);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        configContext();
        configGlobals();
        configFragmentManager(savedInstanceState);
    }

    private void configContext() {
        FragmentosApplication.setAppContext(getApplicationContext());
    }

    private void configGlobals() {
        GLOBALS.put("app", this);
    }

    private void configFragmentManager(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentPanel, new LoginFragment())
                    .commit();
        }
    }

    @Override
    public void navigateTo(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction =
                getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.slide_in_right, R.animator.slide_out_left)
                        .replace(R.id.contentPanel, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public void TopJuegosClick(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel, new TopJuegos())
                .commit();

    }

    public void TopRankedClick(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel, new TopRanked())
                .commit();

    }

    public void laViejaEscuelaClick(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel, new LaViejaEscuela())
                .commit();

    }
    public void freeToPlayClick(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contentPanel, new FreeToPlay())
                .commit();

    }

    }

