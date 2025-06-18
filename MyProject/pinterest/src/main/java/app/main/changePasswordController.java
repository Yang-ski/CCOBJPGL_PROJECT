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
import javafx.scene.layout.StackPane;

public class changePasswordController {


    @FXML
    private Button createPost;

    @FXML
    private BorderPane displayContentPane;

    @FXML
    private Button editProfileButton;

    @FXML
    private Label forInvalidContactLabel;

    @FXML
    private StackPane forInvalidContactLabelContainer;

    @FXML
    private Label forInvalidEmailLabel;

    @FXML
    private StackPane forInvalidEmailLabelContainer;

    @FXML
    private Label forInvalidFnameLabel;

    @FXML
    private StackPane forInvalidFnameLabelContainer;

    @FXML
    private Label forInvalidPassLabel;

    @FXML
    private StackPane forInvalidPassLabelContainer;

    @FXML
    private Button homeButton;

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
    private TextField currentContact;

    @FXML
    private TextField currentEmail;

    @FXML
    private TextField currentFullName;

    @FXML
    void onActionSaveButton(ActionEvent event) throws IOException {
        boolean hasError = false;
        // Hide all error containers first
        forInvalidFnameLabelContainer.setVisible(false);
        forInvalidFnameLabelContainer.setManaged(false);
        forInvalidEmailLabelContainer.setVisible(false);
        forInvalidEmailLabelContainer.setManaged(false);
        forInvalidContactLabelContainer.setVisible(false);
        forInvalidContactLabelContainer.setManaged(false);
        forInvalidPassLabelContainer.setVisible(false);
        forInvalidPassLabelContainer.setManaged(false);

        // Check each field and show error if empty or invalid
        if (!InputValidator.isValidFullName(currentFullName.getText())) {
            forInvalidFnameLabelContainer.setVisible(true);
            forInvalidFnameLabelContainer.setManaged(true);
            hasError = true;
        }
        if (!InputValidator.isValidEmail(currentEmail.getText())) {
            forInvalidEmailLabelContainer.setVisible(true);
            forInvalidEmailLabelContainer.setManaged(true);
            hasError = true;
        }
        if (!InputValidator.isValidContact(currentContact.getText())) {
            forInvalidContactLabelContainer.setVisible(true);
            forInvalidContactLabelContainer.setManaged(true);
            hasError = true;
        }
        if (!InputValidator.isValidPassword(passField.getText())) {
            forInvalidPassLabelContainer.setVisible(true);
            forInvalidPassLabelContainer.setManaged(true);
            hasError = true;
        }

        // Only proceed if no errors
        if (!hasError) {
            java.nio.file.Path accountsPath = java.nio.file.Paths.get("Accounts.txt");
            java.util.List<String> lines = java.nio.file.Files.readAllLines(accountsPath);
            boolean foundMatch = false;
            int matchIndex = -1;
            String fname = currentFullName.getText().trim();
            String email = currentEmail.getText().trim();
            String contact = currentContact.getText().trim();
            String newPass = passField.getText();

            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                if (parts.length >= 5) {
                    String fileFname = parts[2].trim();
                    String fileEmail = parts[3].trim();
                    String fileContact = parts[4].trim();
                    if (fileFname.equals(fname) && fileEmail.equals(email) && fileContact.equals(contact)) {
                        foundMatch = true;
                        matchIndex = i;
                        break;
                    }
                }
            }

            // If no match, show the relevant invalid
            if (!foundMatch) {
                boolean fnameOk = false, emailOk = false, contactOk = false;
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        if (parts[2].trim().equals(fname)) fnameOk = true;
                        if (parts[3].trim().equals(email)) emailOk = true;
                        if (parts[4].trim().equals(contact)) contactOk = true;
                    }
                }
                if (!fnameOk) {
                    forInvalidFnameLabelContainer.setVisible(true);
                    forInvalidFnameLabelContainer.setManaged(true);
                }
                if (!emailOk) {
                    forInvalidEmailLabelContainer.setVisible(true);
                    forInvalidEmailLabelContainer.setManaged(true);
                }
                if (!contactOk) {
                    forInvalidContactLabelContainer.setVisible(true);
                    forInvalidContactLabelContainer.setManaged(true);
                }
                return;
            }

            // If all match, update password and save file
            if (matchIndex != -1) {
                String[] parts = lines.get(matchIndex).split(",");
                parts[1] = newPass;
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < parts.length; j++) {
                    sb.append(parts[j]);
                    if (j < parts.length - 1) sb.append(",");
                }
                lines.set(matchIndex, sb.toString());
                java.nio.file.Files.write(accountsPath, lines);
                // Optionally show a success message here
                App.setRoot("settings"); // Or wherever you want to redirect after password change
            }
        }
    }

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
    void onActionChangePassword(ActionEvent event) {

    }

}
