package kozak.labs.Entity;

import com.google.gson.annotations.SerializedName;

public class Character {
    @SerializedName("name")
    private String name;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("role")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}
