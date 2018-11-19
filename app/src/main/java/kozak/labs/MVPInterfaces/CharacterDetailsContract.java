package kozak.labs.MVPInterfaces;

import kozak.labs.Entity.Character;

public interface CharacterDetailsContract {
    interface View {
        void displayCharacter(final Character character, final boolean isFavorite);
    }

    interface Presenter {
        void makeFavorite();
    }

    interface Model {
        void setFavorite();
        boolean checkFavorite();
    }
}
