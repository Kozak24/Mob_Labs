package kozak.labs.MVPInterfaces;

import java.util.List;

import kozak.labs.Entity.Character;

public interface ListFragmentContract {
    interface View {
        void noData();
        void displayItems(List<Character> characterList);
    }

    interface Presenter {
        void onDetachView();
        void loadData();
    }

    interface Model {
        List<Character> makeCall();
    }
}
