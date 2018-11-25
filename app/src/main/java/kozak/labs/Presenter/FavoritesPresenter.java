package kozak.labs.Presenter;

import java.util.List;

import kozak.labs.ApplicationEx;
import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.FavoriteCharactersContract;
import kozak.labs.Model.FavoritesModel;

public class FavoritesPresenter extends BasePresenter<FavoriteCharactersContract.View>
        implements FavoriteCharactersContract.Presenter {

    FavoriteCharactersContract.Model mModel;

    public FavoritesPresenter(ApplicationEx applicationEx) {
        super(applicationEx);
        mModel = applicationEx.getFavoritesModel();
    }

    @Override
    public void loadData() {
        List<Character> characterList = mModel.getFavoriteCharacters();
        mView.displayFavoritesCharacters(characterList);
    }

    @Override
    public void characterSelected(Character character) {
        application.setCurrentCharacter(character);
        application.getFragmentNavigation().showDetailsFragment();
    }

    @Override
    public void attachView(FavoriteCharactersContract.View view) {
        super.attachView( view );
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onResume() {
        loadData();
    }
}
