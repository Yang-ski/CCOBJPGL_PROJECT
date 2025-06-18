package app.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException; 

public class Postv2Controller {

    @FXML
    private Label postCaption;

    @FXML
    private ImageView postImage;

    @FXML
    private Button saveButton;

    @FXML
    private Button downloadButton;

    @FXML
    private AnchorPane root; // Inject root AnchorPane for hover logic

    private Postv2 currentPost;

    @FXML
    void onActionSave(ActionEvent event) {
        if (currentPost != null) {
            // Only save if it's the saveButton
            if (event.getSource() == saveButton) {
                // Convert Postv2 to Post (assuming similar fields)
                Post post = new Post();
                post.setPostImageSrc(currentPost.getPostImageSrc());
                post.setPostCaptionSrc(currentPost.getPostCaptionSrc());
                post.setCategory(currentPost.getCategory());
                if (Loginv2Controller.user != null) {
                    savedPostsRepository.addPost(Loginv2Controller.user.getUsername(), post);
                }
                // Optionally, provide user feedback (e.g., disable button or show message)
                saveButton.setDisable(true);
            }
        }
    }

    @FXML
    void onMouseClickedPost(MouseEvent event) throws IOException {
        SelectedPostHolder.set(currentPost);
        App.setRoot("mainPost");
    }

    public void setData(Postv2 post) {
        this.currentPost = post;
        if (post == null) return;
        java.io.InputStream imageStream = getClass().getResourceAsStream(post.getPostImageSrc());
        if (imageStream == null) {
            System.err.println("Image not found: " + post.getPostImageSrc());
            // Optionally set a default/fallback image here
            return;
        }
        javafx.scene.image.Image image = new javafx.scene.image.Image(imageStream);
        postImage.setImage(image);
        postCaption.setText(post.getPostCaptionSrc());
        postCaption.setVisible(false); // Always hidden as per layout

        // Set rounded corners for the ImageView (all corners, radius 30)
        double arc = 30.0;
        javafx.scene.shape.Rectangle initialClip = new javafx.scene.shape.Rectangle(
            postImage.getFitWidth() > 0 ? postImage.getFitWidth() : postImage.getBoundsInLocal().getWidth(),
            postImage.getFitHeight() > 0 ? postImage.getFitHeight() : postImage.getBoundsInLocal().getHeight()
        );
        initialClip.setArcWidth(arc);
        initialClip.setArcHeight(arc);
        postImage.setClip(initialClip);

        // Update clip on image property change
        postImage.imageProperty().addListener((obs, oldImage, newImage) -> {
            if (newImage != null) {
                javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(
                    postImage.getBoundsInLocal().getWidth(),
                    postImage.getBoundsInLocal().getHeight()
                );
                clip.setArcWidth(arc);
                clip.setArcHeight(arc);
                postImage.setClip(clip);
            }
        });
        // Update clip on layout bounds change
        postImage.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(
                newBounds.getWidth(), newBounds.getHeight()
            );
            clip.setArcWidth(arc);
            clip.setArcHeight(arc);
            postImage.setClip(clip);
        });

        // Flicker-free hover effect: use root AnchorPane's hoverProperty
        javafx.scene.effect.ColorAdjust darken = new javafx.scene.effect.ColorAdjust();
        darken.setBrightness(-0.4);

        // Hide buttons by default
        saveButton.setVisible(false);
        downloadButton.setVisible(false);

        root.hoverProperty().addListener((obs, wasHovered, isNowHovered) -> {
            if (isNowHovered) {
                postImage.setEffect(darken);
                saveButton.setVisible(true);
                downloadButton.setVisible(true);
            } else {
                postImage.setEffect(null);
                saveButton.setVisible(false);
                downloadButton.setVisible(false);
            }
        });
    }
}