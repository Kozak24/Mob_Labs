package kozak.labs;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import kozak.labs.Entity.Character;
import kozak.labs.FragmentNavigation.FragmentNavigation;
import kozak.labs.Model.DetailsModel;
import kozak.labs.Model.FavoritesModel;
import kozak.labs.Model.ListModel;

public class ApplicationEx extends Application {
    private Character currentCharacter;
    private FragmentNavigation mFragmentNavigation;
    private SharedPreferences preferences;

    private DetailsModel detailsModel;
    private FavoritesModel favoritesModel;
    private ListModel listModel;

    public FragmentNavigation getFragmentNavigation() {
        return mFragmentNavigation;
    }

    public void setFragmentNavigation(FragmentNavigation fragmentNavigation) {
        mFragmentNavigation = fragmentNavigation;
    }

    typo

    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    public void setCurrentCharacter(Character character) {
        currentCharacter = character;
    }

    public DetailsModel getDetailsModel() {
        return detailsModel;
    }

    public FavoritesModel getFavoritesModel() {
        return favoritesModel;
    }

    public ListModel getListModel() {
        return listModel;
    }

    public SharedPreferences getSharedPreferences() {
        return preferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getApplicationContext()
                .getSharedPreferences(Constants.FAVORITES, Context.MODE_PRIVATE);

        detailsModel = new DetailsModel(getSharedPreferences());
        favoritesModel = new FavoritesModel(getSharedPreferences());
        listModel = new ListModel();
    }
}
