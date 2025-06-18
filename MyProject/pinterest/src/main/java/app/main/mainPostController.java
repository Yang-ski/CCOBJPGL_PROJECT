package app.main;

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
import java.io.IOException;
import java.util.List;
import java.net.URL;


public class mainPostController {

    @FXML
    public void initialize() {
        Postv2 post = SelectedPostHolder.get();
        if (post != null) {
            // Set image
            if (post.getPostImageSrc() != null && postImage != null) {
                java.io.InputStream imageStream = getClass().getResourceAsStream(post.getPostImageSrc());
                if (imageStream != null) {
                    javafx.scene.image.Image image = new javafx.scene.image.Image(imageStream);
                    postImage.setImage(image);
                    // --- Robust rounded corners logic ---
                    double arc = 30.0;
                    javafx.scene.shape.Rectangle initialClip = new javafx.scene.shape.Rectangle(
                        postImage.getLayoutBounds().getWidth(),
                        postImage.getLayoutBounds().getHeight()
                    );
                    initialClip.setArcWidth(arc);
                    initialClip.setArcHeight(arc);
                    postImage.setClip(initialClip);
                }
            }
            // Set caption
            if (Caption != null && post.getPostCaptionSrc() != null) {
                Caption.setText(post.getPostCaptionSrc());
            }
            // Set category
            if (Category != null && post.getCategory() != null) {
                Category.setText(post.getCategory());
            }
            // Populate columns with posts from the same category
            populateCategoryColumns(post.getCategory());
        }
    }

    private void populateCategoryColumns(String category) {
        // Clear columns
        postColumn1.getChildren().clear();
        postColumn2.getChildren().clear();
        postColumn3.getChildren().clear();
        VBox[] columns = {postColumn1, postColumn2, postColumn3};
        int columnIndex = 0;
        try {
            // Use Homev3Controller.data() to get all posts
            List<app.main.Post> allPosts = app.main.PostRepository.getAllPosts();
            for (app.main.Post post : allPosts) {
                if (post.getCategory() != null && post.getCategory().equals(category)) {
                    URL fxmlUrl = getClass().getResource("layoutv2.fxml");
                    if (fxmlUrl == null) {
                        System.err.println("layoutv2.fxml not found!");
                        continue;
                    }
                    javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(fxmlUrl);
                    javafx.scene.Parent postBox = fxmlLoader.load();

                    app.main.Postv2Controller postController = fxmlLoader.getController();
                    postController.setData(convertToPostv2(post));
                    columns[columnIndex].getChildren().add(postBox);
                    columnIndex = (columnIndex + 1) % columns.length;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper to convert Post to Postv2 (copied from Homev3Controller)
    private app.main.Postv2 convertToPostv2(app.main.Post post) {
        app.main.Postv2 postv2 = new app.main.Postv2();
        postv2.setPostImageSrc(post.getPostImageSrc());
        postv2.setPostCaptionSrc(post.getPostCaptionSrc());
        postv2.setCategory(post.getCategory());
        return postv2;
    }

    @FXML
    private Label Caption;

    @FXML
    private Label Category;

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
    private VBox postColumn1;

    @FXML
    private VBox postColumn2;

    @FXML
    private VBox postColumn3;

    @FXML
    private ImageView postImage;

    @FXML
    private Button returnButton;

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
    void onActionReturn(ActionEvent event) throws IOException {
        App.setRoot("homev3");
    }

    @FXML
    void onActionSearchButton(ActionEvent event) throws IOException {
        String query = searchField.getText();
        SearchQueryHolder.setQuery(query);
        App.setRoot("homev3");
    }

    @FXML
    void onActionSettingsButton(ActionEvent event) throws IOException {
        App.setRoot("nullPage");
    }

    @FXML
    void onActionViewAccount(ActionEvent event) throws IOException {
        App.setRoot("nullPage");

    }
    
}
