package kozak.labs.Retrofit;


import kozak.labs.Entity.Characters;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("anime/20/characters_staff")
    Call<Characters> getData();
}
