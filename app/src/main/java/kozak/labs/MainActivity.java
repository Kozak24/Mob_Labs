package kozak.labs;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<Character> charactersList;

    private RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.pull_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_data)
    TextView noDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initRecyclerView();

        makeCall();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
                    charactersList = response.body().getCharacters();
                    displayItems();
                } else {
                    noData();
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Toast.makeText(MainActivity.this, getString(R.string.on_failure),
                        Toast.LENGTH_SHORT).show();
                noData();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        noDataTextView.setVisibility(View.INVISIBLE);
    }

    private void displayItems() {
        adapter.setItems(charactersList);
        adapter.notifyDataSetChanged();
        noDataTextView.setVisibility(View.INVISIBLE);
    }

    private void noData() {
        adapter.setItems(null);
        adapter.notifyDataSetChanged();
        noDataTextView.setVisibility(View.VISIBLE);
    }
}
