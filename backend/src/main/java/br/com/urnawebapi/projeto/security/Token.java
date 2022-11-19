package br.com.urnawebapi.projeto.security;

public class Token {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Token(String token) {
        super();
        this.token = token;
    }
}
