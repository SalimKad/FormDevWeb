package com.example.tp3;

public class Utilisateur {
    private String login;
    private String password;

    public Utilisateur(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public int verification(String id, String mdp){
        if(this.login.equals(id) && this.password.equals(mdp)){
            return 1;
        }
        return 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

