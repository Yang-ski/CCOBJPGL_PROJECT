package app.main;
public class Post {

    private String postImageSrc;
    private String postCaptionSrc;
    private String category;
    private String author; // username of creator

    public String getPostImageSrc() {
        return postImageSrc;
    }
    public void setPostImageSrc(String postImageSrc) {
        this.postImageSrc = postImageSrc;
    }
    public String getPostCaptionSrc() {
        return postCaptionSrc;
    }
    public void setPostCaptionSrc(String postCaptionSrc) {
        this.postCaptionSrc = postCaptionSrc;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

}

