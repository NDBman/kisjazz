package hu.berryweb.kisjazz.http.types;

/**
 * Created by Nandi on 2018. 01. 28..
 */
public enum GrantType {
    
    CLIENT_CREDENTIALS("client_credentials"), REFRESH_TOKEN("refresh_token");

    private String value;

    GrantType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
