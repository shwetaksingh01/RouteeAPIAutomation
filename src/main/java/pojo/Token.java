package pojo;

public class Token {
    private String access_token;

    private String[] permissions;

    private String scope;

    private String token_type;

    private String expires_in;

    public Token(){

    }

    public Token(String access_token, String[] permissions, String scope, String token_type, String expires_in) {
        this.access_token = access_token;
        this.permissions = permissions;
        this.scope = scope;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }

    public  String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "ClassPojo [access_token = " + access_token + ", permissions = " + permissions + ", scope = " + scope + ", token_type = " + token_type + ", expires_in = " + expires_in + "]";
    }
}

