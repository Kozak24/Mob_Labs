package kozak.labs.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import kozak.labs.ApplicationEx;
import kozak.labs.Constants;
import kozak.labs.MVPInterfaces.CharacterDetailsContract;

public class DetailsModel implements CharacterDetailsContract.Model {
    private SharedPreferences preferences;

    public DetailsModel() {
        preferences = ApplicationEx.getContext().getSharedPreferences( Constants.FAVORITES,
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
