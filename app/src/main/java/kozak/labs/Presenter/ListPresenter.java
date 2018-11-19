package kozak.labs.Presenter;

import java.util.List;

import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.CharactersListContract;
import kozak.labs.Model.ListModel;

public class ListPresenter extends BasePresenter<CharactersListContract.View>
        implements CharactersListContract.Presenter {
    private CharactersListContract.Model mModel;

    public ListPresenter() {
        this.mModel = new ListModel();
    }

    @Override
    public void loadData() {
        List<Character> characterList = mModel.getCharactersList();
        if(characterList == null) {
            mView.noData();
        } else {
            mView.displayCharacters(characterList);
        }
    }

    @Override
    public void attachView(CharactersListContract.View view) {
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
