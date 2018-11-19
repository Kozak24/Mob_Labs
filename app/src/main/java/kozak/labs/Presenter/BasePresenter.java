package kozak.labs.Presenter;

public abstract class BasePresenter<V>{
    public V mView;

    public void attachView(V view) {
        this.mView = view;
    }

    public void detachView() {
        mView = null;
    }

    public abstract void onResume();
}
