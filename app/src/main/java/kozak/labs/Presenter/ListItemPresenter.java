package kozak.labs.Presenter;

import kozak.labs.MVPInterfaces.ListItemFragmentContract;
import kozak.labs.Model.ListItemModel;

public class ListItemPresenter implements ListItemFragmentContract.Presenter {
    private ListItemFragmentContract.View mView;
    private ListItemFragmentContract.Model mModel;

    public ListItemPresenter(ListItemFragmentContract.View mView) {
        this.mView = mView;
        this.mModel = new ListItemModel();
    }

    @Override
    public void makeFavorite() {
        mModel.setFavorite();
        isFavorite();
    }

    public void isFavorite() {
        mView.setFavoriteImage(mModel.checkFavorite());
    }
}
