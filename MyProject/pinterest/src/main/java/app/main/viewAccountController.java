package app.main;

import java.io.IOException;
import app.main.SearchQueryHolder;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class viewAccountController implements javafx.fxml.Initializable {

    @FXML
    private Button createPost;

    @FXML
    private Label currentUser;

    @FXML
    private BorderPane displayContentPane;

    @FXML
    private Button homeButton;

    @FXML
    private BorderPane layout;

    @FXML
    private HBox logoContainer;

    @FXML
    private Button logoutButton;

    @FXML
    private Button mainButton;

    @FXML
    private Button messagesButton;

    @FXML
    private Button notifsButton;

    @FXML
    private VBox postColumn1;

    @FXML
    private VBox postColumn2;

    @FXML
    private VBox postColumn3;

    @FXML
    private VBox postColumn4;

    @FXML
    private VBox postColumn5;

    @FXML
    private VBox postColumn6;

    @FXML
    private VBox postColumn7;

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
    void onActionLogOut(ActionEvent event) throws IOException {
        App.setRoot("loginv2");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("[DEBUG] initialize: currentUser label: " + currentUser);
        System.out.println("[DEBUG] initialize: Loginv2Controller.user: " + Loginv2Controller.user);
        if (Loginv2Controller.user != null && currentUser != null) {
            System.out.println("[DEBUG] Setting label to: " + Loginv2Controller.user.getFullname());
            currentUser.setText(Loginv2Controller.user.getFullname());
        }
        // Clear columns
        VBox[] columns = {postColumn1, postColumn2, postColumn3, postColumn4, postColumn5, postColumn6, postColumn7};
        for (VBox col : columns) col.getChildren().clear();
        // Load saved posts
        int columnIndex = 0;
        String currentUsername = Loginv2Controller.user != null ? Loginv2Controller.user.getUsername() : null;
        if (currentUsername != null) {
            savedPostsRepository.loadFromFile(currentUsername);
            // Display saved posts
            for (app.main.Post post : savedPostsRepository.getSavedPosts(currentUsername)) {
                try {
                    javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(getClass().getResource("layout.fxml"));
                    VBox postBox = fxmlLoader.load();
                    PostController postController = fxmlLoader.getController();
                    postController.setData(post);
                    columns[columnIndex].getChildren().add(postBox);
                    columnIndex = (columnIndex + 1) % columns.length;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Display posts created by the user
            for (app.main.Post post : PostRepository.getAllPosts()) {
                if (currentUsername.equals(post.getAuthor())) {
                    try {
                        javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(getClass().getResource("layout.fxml"));
                        VBox postBox = fxmlLoader.load();
                        PostController postController = fxmlLoader.getController();
                        postController.setData(post);
                        columns[columnIndex].getChildren().add(postBox);
                        columnIndex = (columnIndex + 1) % columns.length;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
