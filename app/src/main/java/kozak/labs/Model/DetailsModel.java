package kozak.labs.Model;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.CharacterDetailsContract;

public class DetailsModel implements CharacterDetailsContract.Model {
    private SharedPreferences mPreferences;

    public DetailsModel(SharedPreferences preferences) {
        this.mPreferences = preferences;

    }

    public void setFavorite(Character character) {
        SharedPreferences.Editor prefEditor = mPreferences.edit();
        if(checkFavorite(character)) {
            prefEditor.remove(character.getName());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(character);
            prefEditor.putString(character.getName(), json);
            prefEditor.apply();
        }
    }

    public boolean checkFavorite(Character character) {
        return mPreferences.contains( character.getName() );
    }
}
