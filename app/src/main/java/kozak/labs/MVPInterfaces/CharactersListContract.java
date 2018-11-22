package kozak.labs.MVPInterfaces;

import java.util.List;

import kozak.labs.Entity.Character;

public interface CharactersListContract {
    interface View {
        void noData();
        void displayCharacters(final List<Character> characterList);
    }

    interface Presenter {
        void loadData();
        void characterSelected(Character character);
    }

    interface Model {
        List<Character> getCharactersList();
    }
}
