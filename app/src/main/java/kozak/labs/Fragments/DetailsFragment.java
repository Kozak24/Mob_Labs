package kozak.labs.Fragments;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kozak.labs.Adapter.DetailsRecyclerAdapter;
import kozak.labs.ApplicationEx;
import kozak.labs.Constants;
import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.CharacterDetailsContract;
import kozak.labs.Presenter.DetailsPresenter;
import kozak.labs.R;

public class DetailsFragment extends Fragment implements CharacterDetailsContract.View {

    private DetailsRecyclerAdapter adapter;
    private boolean isImageFitToScreen;

    private Character character;

    private DetailsPresenter mPresenter;

    @BindView(R.id.detail_char_name)
    protected TextView characterName;
    @BindView(R.id.detail_char_image)
    protected ImageView characterImage;
    @BindView(R.id.detail_char_role)
    protected TextView characterRole;
    @BindView(R.id.detail_char_id)
    protected TextView characterID;
    @BindView(R.id.detail_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.favorite)
    protected ImageView favorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_item, container, false);
        ButterKnife.bind(this, view);
        if(getArguments() != null) {
            character = (Character) getArguments().getSerializable(Constants.ARG_TITLE);
            ApplicationEx.setCharacter(character);
        }

        if (getActivity() != null) {
            ButterKnife.bind(this, view);

            initRecyclerView();
            displayItems();
        }

        mPresenter = new DetailsPresenter( (ApplicationEx) getContext().getApplicationContext() );
        mPresenter.attachView(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @OnClick(R.id.detail_char_image)
    void fullScreenImage() {
        if(isImageFitToScreen) {
            isImageFitToScreen=false;
            characterImage.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            characterImage.setAdjustViewBounds(true);
        } else {
            isImageFitToScreen=true;
            characterImage.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT));
            characterImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    @OnClick(R.id.favorite)
    public void onFavoritesClicked() {
        mPresenter.makeFavorite();
    }

    @Override
    public void displayCharacter(final Character character, final boolean isFavorite) {
        characterName.setText(character.getName());
        Picasso.get().load(character.getImageUrl()).into(characterImage);
        characterRole.setText(String.format("%s: %s", getString(R.string.char_role),
                character.getRole()));
        characterID.setText(String.format("%s: %s", getString(R.string.mal_id),
                character.getMalID()));

        if(isFavorite) {
            favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            favorite.setImageResource( R.drawable.ic_favorite_border_black_24dp);
        }
    }

    private void displayItems() {
        adapter.setItems(character.getVoiceActors());
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        adapter = new DetailsRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
