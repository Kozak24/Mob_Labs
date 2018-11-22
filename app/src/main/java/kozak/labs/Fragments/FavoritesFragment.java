package kozak.labs.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kozak.labs.Adapter.OnCharacterClickListener;
import kozak.labs.Adapter.RecyclerViewAdapter;
import kozak.labs.ApplicationEx;
import kozak.labs.Entity.Character;
import kozak.labs.Constants;
import kozak.labs.MVPInterfaces.FavoriteCharactersContract;
import kozak.labs.MainActivity;
import kozak.labs.Presenter.FavoritesPresenter;
import kozak.labs.R;

public class FavoritesFragment extends Fragment implements FavoriteCharactersContract.View {

    private RecyclerViewAdapter adapter;
    private FavoritesPresenter mPresenter;

    @BindView(R.id.favorite_recycler_view)
    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        ButterKnife.bind(this, view);

        if(getActivity() != null) {
            initRecyclerView();
        }

        mPresenter = new FavoritesPresenter( (ApplicationEx) getContext().getApplicationContext() );
        mPresenter.attachView(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewAdapter();
        adapter.setOnCharacterClickListener( new OnCharacterClickListener() {
            @Override
            public void onCharacterClick(Character character) {
                mPresenter.characterSelected(character);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void displayFavoritesCharacters(final List<Character> charactersList) {
        adapter.setItems(charactersList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
