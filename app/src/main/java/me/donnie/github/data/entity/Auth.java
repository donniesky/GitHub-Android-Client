package me.donnie.github.data.entity;

import java.util.List;

/**
 * @author donnieSky
 * @created_at 2017/4/6.
 * @description
 */
public class Auth {

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private List<String> scopes;

    private String state;

    private String note;

    private String noteUrl;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteUrl() {
        return noteUrl;
    }

    public void setNoteUrl(String noteUrl) {
        this.noteUrl = noteUrl;
    }

    /*public static Builder builder() {
        return new AutoValue_Auth.Builder();
    }

    public static TypeAdapter<Auth> typeAdapter(Gson gson) {
        return new AutoValue_Auth.GsonTypeAdapter(gson);
    }

    public abstract String clientId();

    public abstract String clientSecret();

    @Nullable
    public abstract String redirectUri();

    public abstract List<String> scopes();

    @Nullable
    public abstract String state();

    public abstract String note();

    public abstract String noteUrl();

    @Nullable
    public abstract String fingerprint();

    @Nullable
    @SerializedName("X-GitHub-OTP")
    public abstract String otpCode();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder clientId(String clientId);
        public abstract Builder clientSecret(String clientSecret);
        public abstract Builder redirectUri(String redirectUri);
        public abstract Builder scopes(List<String> scopes);
        public abstract Builder state(String state);
        public abstract Builder note(String note);
        public abstract Builder noteUrl(String noteUrlId);
        public abstract Builder otpCode(String otpCode);
        public abstract Builder fingerprint(String fingerprint);

        public abstract Auth build();
    }*/

}
