package app.main;

import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.ColorAdjust;

public class PostController {


    @FXML
    private ImageView postImage;

    @FXML
    private Button saveButton;

    @FXML
    private Button unSaveButton;

    private Post currentPost;

    @FXML
    void onActionUnsave(ActionEvent event) {
        if (currentPost != null && Loginv2Controller.user != null) {
            String currentUser = Loginv2Controller.user.getUsername();
            if (currentUser.equals(currentPost.getAuthor())) {
                // Remove post from global repository ("my post" delete)
                PostRepository.removePost(currentPost);
                // Optional: refresh UI, e.g., go to home or reload posts
                try {
                    App.setRoot("homev3"); // Change to your main/home view if needed
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                savedPostsRepository.removePost(currentUser, currentPost);
            }
        }
    }



    public void setData(Post post) {
        this.currentPost = post;
        InputStream imageStream = getClass().getResourceAsStream(post.getPostImageSrc());
        if (imageStream == null) {
            System.err.println("Image not found: " + post.getPostImageSrc());
            // Optionally set a default/fallback image here
            return;
        }
        Image image = new Image(imageStream);
        postImage.setImage(image);


        // Set rounded corners for the ImageView (all corners, radius 30)
        double arc = 30.0;
        // Initial clip (in case image is already loaded)
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
            Rectangle clip = new Rectangle(
                newBounds.getWidth(), newBounds.getHeight()
            );
            clip.setArcWidth(arc);
            clip.setArcHeight(arc);
            postImage.setClip(clip);
        });

        // Consistent darken-on-hover effect
        ColorAdjust darken = new ColorAdjust();
        darken.setBrightness(-0.4); // Adjust for desired darkness
        postImage.setOnMouseEntered(e -> postImage.setEffect(darken));
        postImage.setOnMouseExited(e -> postImage.setEffect(null));
    }

    


}