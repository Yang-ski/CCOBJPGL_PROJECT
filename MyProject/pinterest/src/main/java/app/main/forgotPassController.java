package app.main;

import java.io.IOException;

import javafx.event.ActionEvent;
import app.main.InputValidator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class forgotPassController {
    
    @FXML
    private Pane filterBG;

    @FXML
    private StackPane forContactContainer;

    @FXML
    private TextField forContactField;

    @FXML
    private Label forContactLabel;

    @FXML
    private StackPane forContactLabelContainer;

    @FXML
    private StackPane forEmailContainer;

    @FXML
    private TextField forEmailField;

    @FXML
    private Label forEmailLabel;

    @FXML
    private StackPane forEmailLabelContainer;

    @FXML
    private StackPane forFnameContainer;

    @FXML
    private TextField forFnameField;

    @FXML
    private Label forFnameLabel;

    @FXML
    private StackPane forFnameLabelContainer;

    @FXML
    private Label forHeader;

    @FXML
    private StackPane forHeaderContainer;

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
    private StackPane forNewPassContainer;

    @FXML
    private TextField forNewPassField;

    @FXML
    private Label forNewPassLabel;

    @FXML
    private StackPane forNewPassLabelContainer;

    @FXML
    private Button forResetButton;

    @FXML
    private StackPane forResetContainer;

    @FXML
    private StackPane forTermsLabelContainer;

    @FXML
    private Label regTermsLabel;

    @FXML
    void forResetAction(ActionEvent event) throws IOException {
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

        // Validate each field and show error if invalid
        String fname = forFnameField.getText().trim();
        String email = forEmailField.getText().trim();
        String contact = forContactField.getText().trim();
        String newPass = forNewPassField.getText();

        if (!InputValidator.isValidFullName(fname)) {
            forInvalidFnameLabelContainer.setVisible(true);
            forInvalidFnameLabelContainer.setManaged(true);
            hasError = true;
        }
        if (!InputValidator.isValidEmail(email)) {
            forInvalidEmailLabelContainer.setVisible(true);
            forInvalidEmailLabelContainer.setManaged(true);
            hasError = true;
        }
        if (!InputValidator.isValidContact(contact)) {
            forInvalidContactLabelContainer.setVisible(true);
            forInvalidContactLabelContainer.setManaged(true);
            hasError = true;
        }
        if (!InputValidator.isValidPassword(newPass)) {
            forInvalidPassLabelContainer.setVisible(true);
            forInvalidPassLabelContainer.setManaged(true);
            hasError = true;
        }

        // Only proceed if no errors
        if (!hasError) {
            // Read Accounts.txt and check for a matching record
            java.nio.file.Path accountsPath = java.nio.file.Paths.get("Accounts.txt");
            java.util.List<String> lines = java.nio.file.Files.readAllLines(accountsPath);
            boolean foundMatch = false;
            int matchIndex = -1;
            // Use variables already defined above
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
                // Find which field mismatches
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
                App.setRoot("loginv2");
            }
        }
    }
}
