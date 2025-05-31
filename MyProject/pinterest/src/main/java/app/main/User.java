package app.main;

public class User {
    private final String username;
    private final String password;

    public User(String username, String password){
        this.username = new String(username);
        this.password = new String(password);
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
}
