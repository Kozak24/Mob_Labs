package kozak.labs.Model;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kozak.labs.Entity.Character;
import kozak.labs.MVPInterfaces.FavoriteCharactersContract;

public class FavoritesModel implements FavoriteCharactersContract.Model {
    private SharedPreferences mPreferences;

    public FavoritesModel (SharedPreferences preferences) {
        this.mPreferences = preferences;
    }

    public List<Character> getFavoriteCharacters() {
        List<Character> charactersList = new ArrayList<>();
        Map<String, ?> map = mPreferences.getAll();
        if(map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                final Character character;
                character = new Gson().
                        fromJson(entry.getValue().toString(), Character.class);
                charactersList.add(character);
            }
        }
        return charactersList;
    }
}
