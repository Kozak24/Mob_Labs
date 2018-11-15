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
import kozak.labs.ApplicationEx;
import kozak.labs.Constants;
import kozak.labs.Entity.Character;
import kozak.labs.Entity.Characters;
import kozak.labs.MVPInterfaces.ListFragmentContract;
import kozak.labs.MainActivity;
import kozak.labs.Presenter.ListPresenter;
import kozak.labs.R;
import kozak.labs.Retrofit.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment implements ListFragmentContract.View {

    private RecyclerViewAdapter adapter;

    private ListFragmentContract.Presenter mPresenter;

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

        mPresenter = new ListPresenter(this);
        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            initRecyclerView();

            noDataTextView.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mPresenter.loadData();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
        mPresenter.loadData();

        return view;
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

                   ApplicationEx.getFragmentNavigation().setFragment(listItemFragment);
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void displayItems(List<Character> charactersList) {
        adapter.setItems(charactersList);
        adapter.notifyDataSetChanged();
        noDataTextView.setVisibility(View.INVISIBLE);
    }

    public void noData() {
        adapter.setItems(null);
        adapter.notifyDataSetChanged();
        noDataTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDetachView();
    }
}
