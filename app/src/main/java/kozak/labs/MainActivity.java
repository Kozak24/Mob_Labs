package kozak.labs;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kozak.labs.Adapter.RecyclerViewAdapter;
import kozak.labs.Entity.Character;
import kozak.labs.Entity.Characters;
import kozak.labs.Retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ApiClient apiClient = new ApiClient();
    private Call<Characters> call = apiClient.getApiService().getData();
    private ArrayList<String> mCharactersImageUrls = new ArrayList<>();
    private ArrayList<String> mCharactersNames = new ArrayList<>();
    private ArrayList<String> mCharactersRoles = new ArrayList<>();

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pull_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        makeCall();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clearLists();
                makeCall();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    void makeCall() {
        call.clone().enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                Log.e(TAG, getString(R.string.on_response)
                        + response.toString());

                if (response.body() != null) {
                    List<Character> charactersList = response.body().getCharacters();

                    for (int i = 0; i < charactersList.size(); i++) {
                        mCharactersNames.add(charactersList.get(i).getName());
                        mCharactersImageUrls.add(charactersList.get(i).getImageUrl());
                        mCharactersRoles.add(charactersList.get(i).getRole());
                    }
                    displayItems();
                } else {
                    clearLists();
                    noData();
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Toast.makeText(MainActivity.this, getString(R.string.on_failure),
                        Toast.LENGTH_SHORT).show();
                clearLists();
                noData();
            }
        });
    }

    private void displayItems() {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mCharactersNames,
                mCharactersImageUrls, mCharactersRoles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void clearLists() {
        mCharactersImageUrls.clear();
        mCharactersNames.clear();
        mCharactersRoles.clear();
    }

    private void noData() {
        mCharactersNames.add(getString(R.string.no_data));
        mCharactersImageUrls.add(getString(R.string.no_data));
        mCharactersRoles.add(getString(R.string.no_data));
        displayItems();
    }
}
