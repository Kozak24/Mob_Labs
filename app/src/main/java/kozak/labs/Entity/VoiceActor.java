package kozak.labs.Entity;

import com.google.gson.annotations.SerializedName;

public class VoiceActor {

    @SerializedName("language")
    private String language;
    @SerializedName("name")
    private String actorName;
    @SerializedName("image_url")
    private String actorImageUrl;

    public String getLanguage() {
        return language;
    }

    public String getActorName() {
        return actorName;
    }

    public String getActorImageUrl() {
        return actorImageUrl;
    }
}
