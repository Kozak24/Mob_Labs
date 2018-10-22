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

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorImageUrl() {
        return actorImageUrl;
    }

    public void setActorImageUrl(String actorImageUrl) { this.actorImageUrl = actorImageUrl; }
}
