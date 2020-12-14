package mx.com.softwell.fragmentos.core;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import mx.com.softwell.fragmentos.api.API;
import mx.com.softwell.fragmentos.api.apiservices.JuegoService;
import mx.com.softwell.fragmentos.gui.Categorias;
import mx.com.softwell.fragmentos.gui.MainActivity;
import mx.com.softwell.fragmentos.gui.TopJuegos;
import mx.com.softwell.fragmentos.model.CategoriaJuego;
import mx.com.softwell.fragmentos.model.Juego;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriaController {

    private static CategoriaController instance=null;
    private MiscController miscController=MiscController.Instance();
    private boolean status=false;
    private String message="";
    private String data="";
    private List<CategoriaJuego> categoriaJuegos;
    Type juegosType = new TypeToken<List<CategoriaJuego>>(){}.getType();
    private static String TAG="CategoriaController";

    private CategoriaController(){}

    public static CategoriaController Instance(){
        if(instance==null)
            instance=new CategoriaController();
        return instance;
    }

    public void GetAll(){
        API.getApi().create(JuegoService.class).getCategorias().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().toString());
                    status = jsonObject.getBoolean("status");
                    message = jsonObject.getString("message");
                    if (status){
                        data = jsonObject.getJSONArray("data").toString();
                        categoriaJuegos = new Gson().fromJson(data,juegosType);
                        // miscController.CloseWait();
                        Log.e(TAG, data);
                        Log.e(TAG, categoriaJuegos.toString());
                        ((Categorias) MainActivity.GLOBALS.get("categoriasFragment")).actualizar(categoriaJuegos);
                    }else{
                        miscController.CloseWait();
                        Log.e(TAG, message);
                    }
                }catch (JSONException e){
                    miscController.CloseWait();
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
