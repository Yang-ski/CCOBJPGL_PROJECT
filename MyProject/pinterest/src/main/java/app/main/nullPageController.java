package app.main;

import java.io.IOException;
import app.main.SearchQueryHolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class nullPageController {

    @FXML
    private Button createPost;

    @FXML
    private BorderPane displayContentPane;

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

}
