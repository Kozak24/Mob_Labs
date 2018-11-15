package kozak.labs.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import kozak.labs.ApplicationEx;
import kozak.labs.Constants;
import kozak.labs.MVPInterfaces.ListItemFragmentContract;

public class ListItemModel implements ListItemFragmentContract.Model {
    private SharedPreferences preferences;

    public ListItemModel() {
        preferences = ApplicationEx.getContext().getSharedPreferences( Constants.favorites,
                Context.MODE_PRIVATE);
    }

    public void setFavorite() {
        SharedPreferences.Editor prefEditor = preferences.edit();
        if(checkFavorite()) {
            prefEditor.remove(ApplicationEx.getCharacter().getName());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(ApplicationEx.getCharacter());
            prefEditor.putString(ApplicationEx.getCharacter().getName(), json);
            prefEditor.apply();
        }
    }

    public boolean checkFavorite() {
        return preferences.contains( ApplicationEx.getCharacter().getName() );
    }
}
