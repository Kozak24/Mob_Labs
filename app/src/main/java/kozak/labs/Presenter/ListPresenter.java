package kozak.labs.Presenter;

import java.util.List;

import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.ListFragmentContract;
import kozak.labs.Model.ListModel;

public class ListPresenter implements ListFragmentContract.Presenter {
    private ListFragmentContract.View mView;
    private ListFragmentContract.Model mModel;
    private List<Character> characterList;

    public ListPresenter(ListFragmentContract.View mView) {
        this.mView = mView;
        this.mModel = new ListModel();
    }

    @Override
    public void loadData() {
        characterList = mModel.makeCall();
        if(characterList == null) {
            mView.noData();
        } else {
            mView.displayItems(characterList);
        }
    }

    @Override
    public void onDetachView() {
        mView = null;
    }
}
