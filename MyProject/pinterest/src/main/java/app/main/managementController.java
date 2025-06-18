package app.main;

import java.io.IOException;
import app.main.SearchQueryHolder;
import app.main.InputValidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class managementController {

    @FXML
    private Button changePassword;

    @FXML
    private TextField contactField;

    @FXML
    private Button createPost;

    @FXML
    private BorderPane displayContentPane;

    @FXML
    private Button editProfileButton;

    @FXML
    private TextField emailField;

    @FXML
    private Button homeButton;

    @FXML
    private Label invalidContact;

    @FXML
    private Label invalidEmail;

    @FXML
    private BorderPane layout;

    @FXML
    private HBox logoContainer;

    @FXML
    private Button mainButton;

    @FXML
    private Button managementButton;

    @FXML
    private Button messagesButton;

    @FXML
    private Button notifsButton;

    @FXML
    private PasswordField passField;

    @FXML
    private Label passwordRequired;

    @FXML
    private AnchorPane root;

    @FXML
    private Button saveButton;

    @FXML
    private HBox searchBarLayout;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView searchLogo;

    @FXML
    private Button settingsButton;

    @FXML
    private Button viewAccountButton;

    @FXML
    void onActionSaveButton(ActionEvent event) throws IOException {
        // Hide all validation labels initially
        invalidEmail.setVisible(false);
        invalidContact.setVisible(false);
        passwordRequired.setVisible(false);

        String email = emailField.getText().trim();
        String contact = contactField.getText().trim();
        String password = passField.getText().trim();

        boolean emailFilled = !email.isEmpty();
        boolean contactFilled = !contact.isEmpty();
        boolean passwordFilled = !password.isEmpty();

        // Validate fields
        boolean valid = true;
        if (emailFilled && !InputValidator.isValidEmail(email)) {
            invalidEmail.setVisible(true);
            valid = false;
        }
        if (contactFilled && !InputValidator.isValidContact(contact)) {
            invalidContact.setVisible(true);
            valid = false;
        }
        if (passwordFilled && !InputValidator.isValidPassword(password)) {
            passwordRequired.setVisible(true);
            valid = false;
        }
        if (!valid) return;

        // If user wants to change email/contact but password is empty
        if ((emailFilled || contactFilled) && !passwordFilled) {
            if (emailFilled && !contactFilled) {
                invalidContact.setVisible(true);
            } else if (!emailFilled && contactFilled) {
                invalidEmail.setVisible(true);
            } else if (emailFilled && contactFilled) {
                passwordRequired.setVisible(true);
            }
            return;
        }

        // If all fields are filled, check password
        if ((emailFilled || contactFilled) && passwordFilled) {
            String[] userDetails = getCurrentUserDetails();
            if (userDetails == null) {
                // Handle error: user not found
                return;
            }
            String currentPassword = userDetails[1];
            if (!password.equals(currentPassword)) {
                passwordRequired.setVisible(true);
                return;
            }
            // Password matches: proceed to update email/contact
            // Update only the fields that are filled
            String username = userDetails[0];
            String newPassword = userDetails[1];
            String fullname = userDetails[2];
            String newEmail = emailFilled ? email : userDetails[3];
            String newContact = contactFilled ? contact : userDetails[4];

            // Read all lines, update this user, and write back
            java.nio.file.Path path = java.nio.file.Paths.get("Accounts.txt");
            java.util.List<String> lines = java.nio.file.Files.readAllLines(path);
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length >= 5 && parts[0].equals(username)) {
                    lines.set(i, String.join(",", username, newPassword, fullname, newEmail, newContact));
                    break;
                }
            }
            java.nio.file.Files.write(path, lines);
            // Optionally, show a success message here
        }
    }

    private String[] getCurrentUserDetails() throws IOException {
        // Assumes username is unique and matches Loginv2Controller.user.getUsername()
        String username = Loginv2Controller.user.getUsername();
        java.nio.file.Path path = java.nio.file.Paths.get("Accounts.txt");
        java.util.List<String> lines = java.nio.file.Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[0].equals(username)) {
                // [username, password, fullname, email, contact]
                return parts;
            }
        }
        return null;
    }

    @FXML
    void onActionCreatePostButton(ActionEvent event) throws IOException {
        App.setRoot("nullPage");
    }

    @FXML
    void onActionHomeButton(ActionEvent event) throws IOException {
        App.setRoot("homev3");
    }

    @FXML
    void onActionMainButton(ActionEvent event) throws IOException {
        App.setRoot("homev3");
    }

    @FXML
    void onActionMessagesButton(ActionEvent event) throws IOException {
        App.setRoot("nullPage");
    }

    @FXML
    void onActionNotifButton(ActionEvent event) throws IOException {
        App.setRoot("nullPage");
    }

    @FXML
    void onActionSearchButton(ActionEvent event) throws IOException {
        String query = searchField.getText();
        SearchQueryHolder.setQuery(query);
        App.setRoot("homev3");
    }

    @FXML
    void onActionSettingsButton(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    void onActionViewAccount(ActionEvent event) throws IOException {
        App.setRoot("account");
    }

    @FXML
    void onActionManagementButton(ActionEvent event) throws IOException {
        App.setRoot("management");
    }

    @FXML
    void onActionEditProfileButton(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }

    @FXML
    void onActionChangePassword(ActionEvent event) throws IOException {
        App.setRoot("changePassword");
    }

}
