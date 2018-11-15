package kozak.labs.MVPInterfaces;

public interface ListItemFragmentContract {
    interface View {
        void setFavoriteImage(boolean isFavorite);
    }

    interface Presenter {
        void makeFavorite();
        void isFavorite();
    }

    interface Model {
        void setFavorite();
        boolean checkFavorite();
    }
}
