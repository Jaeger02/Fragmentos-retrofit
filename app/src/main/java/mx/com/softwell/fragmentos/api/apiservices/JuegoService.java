package mx.com.softwell.fragmentos.api.apiservices;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JuegoService {
    @GET("juegos/top")
    Call<JsonObject> getTop();
    @GET("juegos/rank")
    Call<JsonObject> getRank();
    @GET("juegos/oldSchool")
    Call<JsonObject> getOldSchool();
    @GET("juegos/freetoplay")
    Call<JsonObject> getFreeToPlay();
    @GET("juegos/categories")
    Call<JsonObject> getCategorias();

    //@GET("juegos/top/{id}")
    //Call<JsonObject> getByID(@Path("id") int id);
}
