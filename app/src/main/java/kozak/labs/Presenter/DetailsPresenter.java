package kozak.labs.Presenter;

import kozak.labs.ApplicationEx;
import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.CharacterDetailsContract;
import kozak.labs.Model.DetailsModel;

public class DetailsPresenter extends BasePresenter<CharacterDetailsContract.View>
        implements CharacterDetailsContract.Presenter {
    private CharacterDetailsContract.Model mModel;

    public DetailsPresenter() {
        this.mModel = new DetailsModel();
    }

    @Override
    public void makeFavorite() {
        mModel.setFavorite();
        Character character = ApplicationEx.getCharacter();
        mView.displayCharacter(character, mModel.checkFavorite());
    }

    @Override
    public void attachView(CharacterDetailsContract.View view) {
        super.attachView( view );
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onResume() {
        Character character = ApplicationEx.getCharacter();
        mView.displayCharacter(character, mModel.checkFavorite());
    }
}
