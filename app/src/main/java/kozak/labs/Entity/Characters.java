package kozak.labs.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Characters {

    @SerializedName("characters")
    private List<Character> characters;

    public List<Character> getCharacters() {
        return characters;
    }
}
