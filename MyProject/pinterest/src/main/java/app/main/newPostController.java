package app.main;

import javafx.scene.image.Image;
import app.main.SearchQueryHolder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.File;

import javafx.stage.FileChooser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class newPostController {

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
    private TextField newPostCaption;

    @FXML
    private TextField newPostCategory;

    @FXML
    private Button notifsButton;

    @FXML
    private ImageView previewPost;

    @FXML
    private Button publishButton;

    private File uploadedImageFile;

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
    private Button uploadButton;

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
    void onActionPublishPost(ActionEvent event) throws IOException {
        if (uploadedImageFile == null || newPostCaption.getText().isEmpty() || newPostCategory.getText().isEmpty()) {
            System.out.println("All fields (image, caption, category) are required.");
            return;
        }
        // Create new Post
        Post post = new Post();
        // Use resource path for loading in Homev3
        String imageResourcePath = "/app/main/image/post/" + uploadedImageFile.getName();
        post.setPostImageSrc(imageResourcePath);
        post.setPostCaptionSrc(newPostCaption.getText());
        post.setCategory(newPostCategory.getText());
        // Set author to current logged-in user
        if (Loginv2Controller.user != null) {
            post.setAuthor(Loginv2Controller.user.getUsername());
        }
        // Add to all-posts repository
        PostRepository.addPost(post);
        // Navigate to homev3
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
        App.setRoot("settings");
    }

    @FXML
    void onActionUploadButton(ActionEvent event) {
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Choose an Image");
        fileChooser.getExtensionFilters().addAll(
            new javafx.stage.FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        java.io.File selectedFile = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            // Copy file to resources image-post folder
            try {
                File destDir = new File("src/main/resources/app/main/image/post");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }
                File destFile = new File(destDir, selectedFile.getName());
                Files.copy(selectedFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied to: " + destFile.getAbsolutePath());
                // Display preview in previewPost
                javafx.scene.image.Image previewImage = new javafx.scene.image.Image(destFile.toURI().toString());
                System.out.println("Image URI: " + destFile.toURI().toString());
                previewPost.setImage(previewImage);
                // Save the uploaded file reference for publishing
                uploadedImageFile = destFile;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    void onActionViewAccount(ActionEvent event) throws IOException {
        App.setRoot("account");
    }

}
