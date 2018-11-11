package kozak.labs.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Character implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("role")
    private String role;
    @SerializedName("mal_id")
    private String malID;
    @SerializedName("voice_actors")
    private List<VoiceActor> voiceActors;

    public String getName() { return name; }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getRole() { return role; }

    public String getMalID() { return malID; }

    public List<VoiceActor> getVoiceActors() {
        return voiceActors;
    }
}
