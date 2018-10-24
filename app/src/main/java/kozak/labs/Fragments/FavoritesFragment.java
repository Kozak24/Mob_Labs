package kozak.labs.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import kozak.labs.Adapter.OnCharacterClickListener;
import kozak.labs.Adapter.RecyclerViewAdapter;
import kozak.labs.Entity.Character;
import kozak.labs.Constants;
import kozak.labs.MainActivity;
import kozak.labs.R;

public class FavoritesFragment extends Fragment {

    private RecyclerViewAdapter adapter;
    private List<Character> charactersList;

    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);
        charactersList = null;

        if(getActivity() != null) {
            initRecyclerView();

            getPreferences();
        }

        return view;
    }

    private void getPreferences() {
        SharedPreferences preferences;
        preferences = getActivity().getSharedPreferences(
                Constants.favorites, Context.MODE_PRIVATE);
        Map<String, ?> map = preferences.getAll();
        if(map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                final Character character;
                character = new Gson().
                        fromJson(entry.getValue().toString(), Character.class);
                charactersList.add(character);
            }
            displayItems();
        }
    }

    private void initRecyclerView() {
        charactersList = new ArrayList<>();
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
    }
}
