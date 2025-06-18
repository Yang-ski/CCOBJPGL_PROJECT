package app.main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import app.main.SearchQueryHolder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.Parent;



public class Homev3Controller implements javafx.fxml.Initializable {
    @FXML private BorderPane layout;
    @FXML private HBox logoContainer;
    @FXML private AnchorPane root;
    @FXML private HBox searchBarLayout;
    @FXML private Button searchButton;
    @FXML private TextField searchField;
    @FXML private ImageView searchLogo;
    @FXML private Button settingsButton;
    @FXML private Button viewAccountButton;
    @FXML private Button messagesButton;
    @FXML private Button createPost;
    @FXML private BorderPane displayContentPane;
    @FXML private Button homeButton;
    @FXML private Button mainButton;
    @FXML private Button notifsButton;


    @FXML private VBox postColumn1;
    @FXML private VBox postColumn2;
    @FXML private VBox postColumn3;
    @FXML private VBox postColumn4;
    @FXML private VBox postColumn5;
    @FXML private VBox postColumn6;
    @FXML private VBox postColumn7;
    private List<Post> posts = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String query = SearchQueryHolder.getQuery();
        VBox[] columns = {postColumn1, postColumn2, postColumn3, postColumn4, postColumn5, postColumn6, postColumn7};
        for (VBox col : columns) col.getChildren().clear();
        if (query != null && !query.trim().isEmpty()) {
            posts = PostRepository.getAllPosts();
            query = query.trim().toLowerCase();
            List<Post> filtered = new ArrayList<>();
            for (Post post : posts) {
                if (post.getPostCaptionSrc().toLowerCase().contains(query) ||
                    (post.getCategory() != null && post.getCategory().toLowerCase().contains(query))) {
                    filtered.add(post);
                }
            }
            SearchQueryHolder.clear();
            if (filtered.isEmpty()) {
                try { App.setRoot("nullPage"); } catch (IOException e) { e.printStackTrace(); }
                return;
            }
            int columnIndex = 0;
            try {
                for (Post post : filtered) {
                    URL fxmlUrl = getClass().getResource("layoutv2.fxml");
                    if (fxmlUrl == null) {
                        System.err.println("layout.fxml not found!");
                        continue;
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    Parent postBox = fxmlLoader.load();
                    Postv2Controller postController = fxmlLoader.getController();
                    postController.setData(convertToPostv2(post));
                    columns[columnIndex].getChildren().add(postBox);
                    columnIndex = (columnIndex + 1) % columns.length;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            posts = PostRepository.getAllPosts();
            int columnIndex = 0;
            try {
                for (Post post : posts) {
                    URL fxmlUrl = getClass().getResource("layoutv2.fxml");
                    if (fxmlUrl == null) {
                        System.err.println("layout.fxml not found!");
                        continue;
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                    Parent postBox = fxmlLoader.load();
                    Postv2Controller postController = fxmlLoader.getController();
                    postController.setData(convertToPostv2(post));
                    columns[columnIndex].getChildren().add(postBox);
                    columnIndex = (columnIndex + 1) % columns.length;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private Postv2 convertToPostv2(Post post) {
        Postv2 postv2 = new Postv2();
        postv2.setPostImageSrc(post.getPostImageSrc());
        postv2.setPostCaptionSrc(post.getPostCaptionSrc());
        postv2.setCategory(post.getCategory());
        return postv2;
    }

    @FXML
    void onActionSearchButton(ActionEvent event) throws IOException {
        String query = searchField.getText().trim().toLowerCase();
        VBox[] columns = {postColumn1, postColumn2, postColumn3, postColumn4, postColumn5, postColumn6, postColumn7};
        // Clear all columns
        for (VBox col : columns) col.getChildren().clear();
        if (query.isEmpty()) {
            // If query is empty, show all posts
            posts = PostRepository.getAllPosts();
        }
        // Filter posts
        List<Post> filtered = new ArrayList<>();
        for (Post post : posts) {
            if (post.getPostCaptionSrc().toLowerCase().contains(query) ||
                (post.getCategory() != null && post.getCategory().toLowerCase().contains(query))) {
                filtered.add(post);
            }
        }
        // If no posts match, go to nullPage
        if (filtered.isEmpty()) {
            App.setRoot("nullPage");
            return;
        }
        // Display filtered posts
        int columnIndex = 0;
        try {
            for (Post post : filtered) {
                URL fxmlUrl = getClass().getResource("layoutv2.fxml");
                if (fxmlUrl == null) {
                    System.err.println("layout.fxml not found!");
                    continue;
                }
                FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                Parent postBox = fxmlLoader.load();
                Postv2Controller postController = fxmlLoader.getController();
                postController.setData(convertToPostv2(post));
                columns[columnIndex].getChildren().add(postBox);
                columnIndex = (columnIndex + 1) % columns.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionViewAccount(ActionEvent event) throws IOException {
        App.setRoot("account");
    }

    @FXML
    void onActionMessagesButton(ActionEvent event) throws IOException {
        App.setRoot("nullPage");
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
    void onActionNotifButton(ActionEvent event) throws IOException {
        App.setRoot("nullPage");
    }

    @FXML
    void onActionSettingsButton(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }
}
