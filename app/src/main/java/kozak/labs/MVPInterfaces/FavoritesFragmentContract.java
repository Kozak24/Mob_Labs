package kozak.labs.MVPInterfaces;

import java.util.List;

import kozak.labs.Entity.Character;

public interface FavoritesFragmentContract {
    interface View {
        void displayItems(List<Character> characterList);
    }

    interface Presenter {
        void loadData();
    }

    interface Model {
        List<Character> getPreferences();
    }
}
