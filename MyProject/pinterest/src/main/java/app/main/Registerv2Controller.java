package app.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import app.main.InputValidator;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Registerv2Controller {

    @FXML
    private Pane filterBG;

    @FXML
    private StackPane invalidPassContainer;

    @FXML
    private StackPane regContactContainer;

    @FXML
    private TextField regContactField;

    @FXML
    private Label regContactLabel;

    @FXML
    private StackPane regContactLabelContainer;

    @FXML
    private StackPane regEmailContainer;

    @FXML
    private TextField regEmailField;

    @FXML
    private Label regEmailLabel;

    @FXML
    private StackPane regEmailLabelContainer;

    @FXML
    private Label regHeader;

    @FXML
    private StackPane regHeaderContainer;

    @FXML
    private StackPane regInvalidContactContainer;

    @FXML
    private Label regInvalidContactLabel;

    @FXML
    private StackPane regInvalidEmailContainer;

    @FXML
    private Label regInvalidEmailLabel;

    @FXML
    private StackPane regInvalidNameContainer;

    @FXML
    private Label regInvalidNameLabel;

    @FXML
    private Label regInvalidPassLabel;

    @FXML
    private StackPane regInvalidUserContainer;

    @FXML
    private Label regInvalidUserLabel;

    @FXML
    private Label regLameLabel;

    @FXML
    private ImageView regLogo;

    @FXML
    private StackPane regLogoContainer;

    @FXML
    private VBox regMainLayout;

    @FXML
    private StackPane regNameContainer;

    @FXML
    private TextField regNameField;

    @FXML
    private StackPane regNameLabelContainer;

    @FXML
    private StackPane regPassContainer;

    @FXML
    private PasswordField regPassField;

    @FXML
    private Label regPassLabel;

    @FXML
    private StackPane regPassLabelContainer;

    @FXML
    private Button regSignUpButton;

    @FXML
    private StackPane regSignUpContainer;

    @FXML
    private Button regSubSignInButton;

    @FXML
    private StackPane regSubSignInButtonContainer;

    @FXML
    private StackPane regTermsContainer;

    @FXML
    private Label regTermsLabel;

    @FXML
    private StackPane regUserContainer;

    @FXML
    private StackPane regUserExistsContainer;

    @FXML
    private Label regUserExistsLabel;

    @FXML
    private TextField regUserField;

    @FXML
    private Label regUserLabel;

    @FXML
    private StackPane regUserLabelContainer;

    @FXML
    void signInAction(ActionEvent event) throws IOException {
        App.setRoot("loginv2");
    }

    @FXML
    void signUpAction(ActionEvent event) throws IOException {
        System.out.println("=== Starting sign up process ===");
        // Get input values
        String username = regUserField.getText().trim();
        String password = regPassField.getText().trim();
        String fullname = regNameField.getText().trim();
        String email = regEmailField.getText().trim();
        String contact = regContactField.getText().trim();
        
        System.out.println("Input values - Username: " + username + ", Fullname: " + fullname + ", Email: " + email);
        
        // Clear previous error messages
        clearErrorMessages();
        
        // Validate inputs
        boolean isValid = true;
        System.out.println("Starting validation...");

        if (!InputValidator.isValidUsername(username)) {
            regInvalidUserLabel.setText(username.isEmpty() ? "Username cannot be empty" : "Username must not exceed 20 characters");
            regInvalidUserContainer.setVisible(true);
            isValid = false;
        } else if (usernameExists(username)) {
            regUserExistsLabel.setText("Username already taken");
            regUserExistsContainer.setVisible(true);
            isValid = false;
        }

        if (!InputValidator.isValidFullName(fullname)) {
            regInvalidNameLabel.setText(fullname.isEmpty() ? "Fullname cannot be empty" : "Fullname must not exceed 70 characters");
            regInvalidNameContainer.setVisible(true);
            isValid = false;
        }

        if (!InputValidator.isValidEmail(email)) {
            regInvalidEmailLabel.setText(email.isEmpty() ? "Email cannot be empty" : "Email must contain @ and end with .com");
            regInvalidEmailContainer.setVisible(true);
            isValid = false;
        }

        if (!InputValidator.isValidContact(contact)) {
            regInvalidContactLabel.setText(contact.isEmpty() ? "Contact number cannot be empty" : "Contact must be up to 11 digits");
            regInvalidContactContainer.setVisible(true);
            isValid = false;
        }

        if (!InputValidator.isValidPassword(password)) {
            regInvalidPassLabel.setText(password.isEmpty() ? "Password cannot be empty" : "Password must be at least 8 characters");
            invalidPassContainer.setVisible(true);
            isValid = false;
        }

        // If any validation failed, stop here
        if (!isValid) {
            System.out.println("Validation failed - not proceeding with registration");
            return;
        }
        
        System.out.println("All validations passed");
        
        // Save user data
        if (saveNewUser(username, password, fullname, email, contact)) {
            // Redirect to login on success
            App.setRoot("loginv2");
        } else {
            // Show error if saving failed
            regInvalidUserLabel.setText("Error saving user data");
            regInvalidUserContainer.setVisible(true);
        }
    }
    
    private boolean usernameExists(String username) {
        String filename = "Accounts.txt";
        File file = new File(filename);
        System.out.println("Checking if username exists in: " + file.getAbsolutePath());
        
        if (!file.exists()) {
            System.out.println("Accounts file does not exist yet");
            return false;
        }
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                System.out.println("Checking line: " + line);
                if (!line.isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length > 0 && parts[0].equals(username)) {
                        System.out.println("Found existing username: " + username);
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts file: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Username is available: " + username);
        return false;
    }
    
    private boolean saveNewUser(String username, String password, String fullname, String email, String contact) {
        String filename = "Accounts.txt";
        System.out.println("Attempting to save to: " + new File(filename).getAbsolutePath());
        
        try (FileWriter writer = new FileWriter(filename, true)) {
            // Format: username,password,fullname,email,contact
            String userData = String.format("%s,%s,%s,%s,%s%n", 
                username, password, fullname, email, contact);
            System.out.println("Writing data: " + userData.trim());
            writer.write(userData);
            writer.flush(); // Force write to disk
            System.out.println("Data written successfully");
            return true;
        } catch (IOException e) {
            System.err.println("Error saving user data: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    private void clearErrorMessages() {
        regInvalidUserContainer.setVisible(false);
        regUserExistsContainer.setVisible(false);
        regInvalidNameContainer.setVisible(false);
        regInvalidEmailContainer.setVisible(false);
        regInvalidContactContainer.setVisible(false);
        invalidPassContainer.setVisible(false);
    }

}
