package kozak.labs.Presenter;

import kozak.labs.ApplicationEx;
import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.CharacterDetailsContract;

public class DetailsPresenter extends BasePresenter<CharacterDetailsContract.View>
        implements CharacterDetailsContract.Presenter {

    private CharacterDetailsContract.Model mModel;

    public DetailsPresenter(final ApplicationEx applicationEx) {
        super(applicationEx);
        this.mModel = applicationEx.getDetailsModel();
    }

    @Override
    public void makeFavorite() {
        Character character = getCurrentCharacter();
        mModel.setFavorite(character);
        mView.displayCharacter(character, mModel.checkFavorite(character));
    }

    @Override
    public Character getCurrentCharacter() {
        return application.getCurrentCharacter();
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
        Character character = getCurrentCharacter();

        mView.displayCharacter(character, mModel.checkFavorite(character));
    }
}
