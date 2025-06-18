package app.main;

public class InputValidator {
    public static boolean isValidUsername(String username) {
        return username != null && username.length() <= 20 && !username.isEmpty();
    }

    public static boolean isValidFullName(String fullname) {
        return fullname != null && fullname.length() <= 70 && !fullname.isEmpty();
    }

    public static boolean isValidContact(String contact) {
        return contact != null && contact.matches("\\d{1,11}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.endsWith(".com") && !email.isEmpty();
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
