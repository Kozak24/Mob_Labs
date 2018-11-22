package kozak.labs.Model;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import kozak.labs.ApplicationEx;
import kozak.labs.MVPInterfaces.CharacterDetailsContract;

public class DetailsModel implements CharacterDetailsContract.Model {
    private SharedPreferences mPreferences;

    public DetailsModel(SharedPreferences preferences) {
        /*preferences = ApplicationEx.getContext().getSharedPreferences( Constants.FAVORITES,
                Context.MODE_PRIVATE);*/
        this.mPreferences = preferences;

    }

    public void setFavorite() {
        SharedPreferences.Editor prefEditor = mPreferences.edit();
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
        return mPreferences.contains( ApplicationEx.getCharacter().getName() );
    }
}
