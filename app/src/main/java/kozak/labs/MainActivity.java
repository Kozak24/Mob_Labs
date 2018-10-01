package kozak.labs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
    private List<String> mList = new ArrayList<>();
    private ListView mCharList;
    private TextView charsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        charsTextView = findViewById(R.id.list_of_chars);

        makeCall();

    }

    void makeCall(){
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                Log.e("MainActivity", "onResponse: Server Response " + response.toString());
                //Log.e("MainActivity", "Received information " + response.body().toString());
                listOfCharacters.delete(0, listOfCharacters.length());
                List<Character> charactersList = response.body().getCharacters();
                for(int i = 0; i<charactersList.size(); i++) {
                    Log.e("MainActivity",
                    "name:" + charactersList.get(i).getName() + "\n" +
                            "image_url" + charactersList.get(i).getImageUrl() + "\n" +
                    "================================================");
                    //listOfCharacters.append("name:" + charactersList.get(i).getName());
                }
                charsTextView.setText(listOfCharacters.toString());
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.e("MainActivity", "onFailure: Something went wrong " + t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
