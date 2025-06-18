package app.main;

import java.io.File;
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

public class Loginv2Controller {

    @FXML
    private Pane filterBG;

    @FXML
    private Button forgotPassButton;

    @FXML
    private StackPane forgotPassContainer;

    @FXML
    private Label header;

    @FXML
    private StackPane headerContainer;

    @FXML
    private Label invalidPassword;

    @FXML
    private StackPane invalidPasswordContainer;

    @FXML
    private Label invalidUser;

    @FXML
    private StackPane invalidUserContainer;

    @FXML
    private Button loginButton;

    @FXML
    private StackPane loginContainer;

    @FXML
    private ImageView logo;

    @FXML
    private StackPane logoContainer;

    @FXML
    private VBox mainLayout;

    @FXML
    private StackPane passContainer;

    @FXML
    private PasswordField passField;

    @FXML
    private Label passLabel;

    @FXML
    private StackPane passLabelContainer;

    @FXML
    private Button signUpButton;

    @FXML
    private Button subSignUpButton;

    @FXML
    private StackPane subSignUpContainer;

    @FXML
    private Label termsLabel;

    @FXML
    private StackPane textContainer;

    @FXML
    private StackPane userContainer;

    @FXML
    private TextField userField;

    @FXML
    private Label userLabel;

    @FXML
    private StackPane userLabelContainer;

    public static User user;

    @FXML
    public void logInAction(ActionEvent event) throws IOException {
        String username = userField.getText();
        String password = passField.getText();
        
        // Reset error messages
        invalidUserContainer.setVisible(false);
        invalidUserContainer.setManaged(false);
        invalidPasswordContainer.setVisible(false);
        invalidPasswordContainer.setManaged(false);
        
        // Validate input
        boolean valid = true;
        if (!InputValidator.isValidUsername(username)) {
            invalidUser.setText(username.isEmpty() ? "Username cannot be blank" : "Username must not exceed 20 characters");
            invalidUserContainer.setVisible(true);
            invalidUserContainer.setManaged(true);
            valid = false;
        }
        if (!InputValidator.isValidPassword(password)) {
            invalidPassword.setText(password.isEmpty() ? "Password cannot be blank" : "Password must be at least 8 characters");
            invalidPasswordContainer.setVisible(true);
            invalidPasswordContainer.setManaged(true);
            valid = false;
        }
        if (!valid) return;
        
        // Check credentials
        File accountsFile = new File("Accounts.txt");
        if (accountsFile.exists()) {
            try (Scanner scanner = new Scanner(accountsFile)) {
                boolean found = false;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length >= 5 && parts[0].trim().equals(username) && parts[1].trim().equals(password)) {
                        user = new User(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());
                        App.setRoot("homev3");
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    invalidUser.setText("Invalid username or password");
                    invalidUserContainer.setVisible(true);
                    invalidUserContainer.setManaged(true);
                    invalidPasswordContainer.setVisible(true);
                }
            } catch (IOException e) {
                System.err.println("Error reading accounts file: " + e.getMessage());
                invalidUser.setText("Error reading user data");
                invalidUserContainer.setVisible(true);
                invalidUserContainer.setManaged(true);
            }
        } else {
            invalidUser.setText("No accounts found. Please register first.");
            invalidUserContainer.setVisible(true);
            invalidUserContainer.setManaged(true);
        }
    }
    @FXML
    public void signUpAction(ActionEvent event) throws IOException {
        App.setRoot("registerv2");
    }
    @FXML
    public void subSignUpAction(ActionEvent event) throws IOException {
        App.setRoot("registerv2");
    }
    @FXML
    public void forgotPasswordAction(ActionEvent event) throws IOException {
        App.setRoot("forgotPass");
    }
}
