package kozak.labs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import kozak.labs.Entity.Character;
import kozak.labs.Entity.Characters;
import kozak.labs.Retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    private StringBuilder listOfCharacters = new StringBuilder();
    private ApiClient apiClient = new ApiClient();
    private Call<Characters> call = apiClient.getApiService().getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeCall();
    }

    void makeCall(){
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                Log.e( getString(R.string.TAG), getString(R.string.on_response)
                        + response.toString());
                listOfCharacters.delete(0, listOfCharacters.length());
                List<Character> charactersList = response.body().getCharacters();
                for(int i = 0; i<charactersList.size(); i++) {
                    Log.e( getString(R.string.TAG),
                            String.format("%s%s\n%s%s\n", getString(R.string.character_name),
                                    charactersList.get(i).getName(),
                                    getString(R.string.character_image_url),
                                    charactersList.get(i).getImageUrl()));
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.e(getString(R.string.TAG), getString(R.string.on_failure) +
                        t.getMessage());
                Toast.makeText(MainActivity.this, getString(R.string.on_failure),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
