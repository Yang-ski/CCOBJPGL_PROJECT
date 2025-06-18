package app.main;

import java.io.IOException;
import app.main.SearchQueryHolder;
import app.main.InputValidator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
public class settingsController {
    
    @FXML
    private Button createPost;

    @FXML
    private BorderPane displayContentPane;

    @FXML
    private TextField fullNameField;

    @FXML
    private Button homeButton;

    @FXML
    private BorderPane layout;

    @FXML
    private HBox logoContainer;

    @FXML
    private Button mainButton;

    @FXML
    private Button messagesButton;

    @FXML
    private Button notifsButton;

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
    private TextField usernameField;

    @FXML
    private Button viewAccountButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button managementButton;

    

    @FXML
    void onActionCreatePostButton(ActionEvent event) throws IOException {
        App.setRoot("newPost");
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
    void onActionSaveButton(ActionEvent event) throws IOException {
        // Get new values from fields
        String newFullName = fullNameField.getText().trim();
        String newUsername = usernameField.getText().trim();

        // Validate fields
        boolean valid = true;
        if (!InputValidator.isValidUsername(newUsername)) {
            System.out.println("Username must not exceed 20 characters and cannot be empty.");
            valid = false;
        }
        if (!InputValidator.isValidFullName(newFullName)) {
            System.out.println("Full name must not exceed 70 characters and cannot be empty.");
            valid = false;
        }
        if (!valid) return;
        
        // Get current user
        User currentUser = Loginv2Controller.user;
        if (currentUser == null) {
            System.out.println("No user is currently logged in.");
            return;
        }
        String oldUsername = currentUser.getUsername();
        String password = currentUser.getPassword();

        java.io.File accountsFile = new java.io.File("Accounts.txt");
        java.util.List<String> lines = new java.util.ArrayList<>();
        boolean updated = false;
        if (accountsFile.exists()) {
            try (java.util.Scanner scanner = new java.util.Scanner(accountsFile)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length >= 5 && parts[0].trim().equals(oldUsername)) {
                        // Always use email/contact from file, not from User object
                        String email = parts[3];
                        String contact = parts[4];
                        String newLine = String.format("%s,%s,%s,%s,%s", newUsername, password, newFullName, email, contact);
                        lines.add(newLine);
                        updated = true;
                    } else {
                        lines.add(line);
                    }
                }
            }
            // Write back all lines
            try (java.io.FileWriter writer = new java.io.FileWriter(accountsFile, false)) {
                for (String l : lines) {
                    writer.write(l + System.lineSeparator());
                }
            }
            if (updated) {
                // Update static user reference (email/contact from last update)
                // Find the updated line to get email/contact
                String updatedEmail = "";
                String updatedContact = "";
                for (String l : lines) {
                    String[] parts = l.split(",");
                    if (parts.length >= 5 && parts[0].trim().equals(newUsername)) {
                        updatedEmail = parts[3];
                        updatedContact = parts[4];
                        break;
                    }
                }
                Loginv2Controller.user = new User(newUsername, password, newFullName, updatedEmail, updatedContact);
                System.out.println("User info updated successfully.");
            } else {
                System.out.println("Could not find the user to update.");
            }
        } else {
            System.out.println("Accounts file does not exist.");
        }
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
}
