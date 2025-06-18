package app.main;

public class SelectedPostHolder {
    private static Postv2 selectedPost;

    public static void set(Postv2 post) {
        selectedPost = post;
    }

    public static Postv2 get() {
        return selectedPost;
    }
}