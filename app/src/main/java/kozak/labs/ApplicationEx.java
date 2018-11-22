package kozak.labs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import kozak.labs.Entity.Character;
import kozak.labs.FragmentNavigation.FragmentNavigation;
import kozak.labs.Model.DetailsModel;
import kozak.labs.Model.FavoritesModel;
import kozak.labs.Model.ListModel;

public class ApplicationEx extends Application {
    private Context mContext;
    private static Character mCharacter;
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

    public Context getContext() {
        return mContext;
    }

    public static Character getCharacter() {
        return mCharacter;
    }

    public static void setCharacter(Character character) {
        mCharacter = character;
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
        mContext = getApplicationContext();
        preferences = getContext().getSharedPreferences(Constants.FAVORITES, Context.MODE_PRIVATE);

        detailsModel = new DetailsModel(getSharedPreferences());
        favoritesModel = new FavoritesModel(getSharedPreferences());
        listModel = new ListModel();
    }
}
