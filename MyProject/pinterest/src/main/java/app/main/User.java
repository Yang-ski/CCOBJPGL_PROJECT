package app.main;

public class User {
    private final String username;
    private final String password;
    private final String fullname;
    private final String email;
    private final String contact;

    public User(String username, String password, String fullname, String email, String contact) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.contact = contact;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
}
