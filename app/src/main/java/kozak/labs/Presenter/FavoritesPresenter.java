package kozak.labs.Presenter;

import java.util.List;

import kozak.labs.Entity.Character;
import kozak.labs.Fragments.FavoritesFragment;
import kozak.labs.MVPInterfaces.FavoritesFragmentContract;
import kozak.labs.Model.FavoritesModel;

public class FavoritesPresenter implements FavoritesFragmentContract.Presenter {

    FavoritesFragmentContract.View mView;
    FavoritesFragmentContract.Model mModel;

    public FavoritesPresenter(FavoritesFragmentContract.View mView) {
        this.mView = mView;
        mModel = new FavoritesModel();
    }

    @Override
    public void loadData() {
        List<Character> characterList = mModel.getPreferences();
        mView.displayItems(characterList);
    }
}
