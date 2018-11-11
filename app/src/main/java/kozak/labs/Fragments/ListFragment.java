package kozak.labs.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kozak.labs.Adapter.OnCharacterClickListener;
import kozak.labs.Adapter.RecyclerViewAdapter;
import kozak.labs.Constants;
import kozak.labs.Entity.Character;
import kozak.labs.Entity.Characters;
import kozak.labs.MainActivity;
import kozak.labs.R;
import kozak.labs.Retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {

    private List<Character> charactersList;

    private RecyclerViewAdapter adapter;

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.pull_refresh)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_data)
    protected TextView noDataTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            initRecyclerView();

            noDataTextView.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    makeCall();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        makeCall();

        return view;
    }

    void makeCall() {
        ApiClient.getApiService().getData().clone().enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if (getActivity() != null) {
                    Log.e(getActivity().toString(), getString(R.string.on_response)
                            + response.toString());

                    if (response.body() != null) {
                        charactersList = response.body().getCharacters();
                        displayItems();
                    } else {
                        noData();
                    }
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.on_failure),
                        Toast.LENGTH_SHORT).show();
                noData();
            }
        });
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter();
        adapter.setOnCharacterClickListener( new OnCharacterClickListener() {
            @Override
            public void onCharacterClick(Character character) {
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable( Constants.ARG_TITLE, character);

                    ListItemFragment listItemFragment = new ListItemFragment();
                    listItemFragment.setArguments(bundle);

                    mainActivity.setFragment(listItemFragment);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
