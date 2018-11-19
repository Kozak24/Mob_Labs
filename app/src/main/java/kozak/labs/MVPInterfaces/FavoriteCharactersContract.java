package kozak.labs.MVPInterfaces;

import java.util.List;

import kozak.labs.Entity.Character;

public interface FavoriteCharactersContract {
    interface View {
        void displayFavoritesCharacters(final List<Character> characterList);
    }

    interface Presenter {
        void loadData();
    }

    interface Model {
        List<Character> getFavoriteCharacters();
    }
}
