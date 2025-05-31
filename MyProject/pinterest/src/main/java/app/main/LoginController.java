package app.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LoginController {
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
    private ImageView imageBG;

    @FXML
    private StackPane layout;

    @FXML
    private Button loginButton;

    @FXML
    private StackPane loginContainer;

    @FXML
    private ImageView logo;

    @FXML
    private StackPane logoContainer;

    @FXML
    private Pane mainDimensions;

    @FXML
    private VBox mainLayout;

    @FXML
    private StackPane mainMirror;

    @FXML
    private StackPane passContainer;

    @FXML
    private PasswordField passField;

    @FXML
    private Label passLabel;

    @FXML
    private StackPane passLabelContainer;

    @FXML
    private AnchorPane root1;

    @FXML
    private Button signUpButton;

    @FXML
    private AnchorPane signUpButtonContainer;

    @FXML
    private Button subSignUpButton;

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public static User user;

    @FXML
    public void logInAction(ActionEvent event) throws IOException{
        System.out.println("Login button clicked!");

        // Retrieve username in text field
        String username = userField.getText();
        System.out.println("Username entered: " + username);

        // Retrieve password in password field
        String password = passField.getText();
        System.out.println("Password entered: " + password);

        // Create user object
        user = new User(username, password);

        // Create a file object
        File accountsfile = new File("C:/Users/Paolo/VSCODE/MyProject/pinterest/Accounts.txt");
        System.out.println("Checking if Accounts.txt exists: " + accountsfile.exists());

        // Check if file exists
        if (accountsfile.exists()) {
            Scanner filescanner;
            boolean found = false;
            try {
                filescanner = new Scanner(accountsfile);
                while (filescanner.hasNextLine()) {
                    String data = filescanner.nextLine();
                    String username_from_file = data.split(",")[0];
                    String password_from_file = data.split(",")[1];
                    System.out.println("Checking credentials: " + username_from_file + "," + password_from_file);
                    // if the user and password from the textfield and passwordfield matches any record in the accounts.txt file
                    if (username_from_file.equals(user.getUsername()) && password_from_file.equals(user.getPassword())) {
                        System.out.println("Login successful! Loading register.fxml...");
                        // Show alert box when login is successful!
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setContentText("PALDO!");  
                        alert.showAndWait();   
                        
                        // Load Home.fxml when login button is clicked
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
                        root = loader.load();

                        // Load stage and scene
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        found = true;
                        break;          
                    }
                }
                if (!found) {
                    System.out.println("Login failed: No matching credentials found.");
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
