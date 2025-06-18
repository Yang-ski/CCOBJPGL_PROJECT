package app.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostRepository {
    private static final List<Post> allPosts = new ArrayList<>();

    static {
        // Add demo/sample posts here
        addDemoPosts();
    }

    // Method to add demo/sample posts on startup
    private static void addDemoPosts() {
        Post post = new Post();
        post.setPostImageSrc("/app/main/image/post/1.jpg");
        post.setPostCaptionSrc("Harley Davidson Gas Tank");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/2.jpg");
        post.setPostCaptionSrc("Nirvana");
        post.setCategory("lifestyle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/3.jpg");
        post.setPostCaptionSrc("HUH!?");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/4.jpg");
        post.setPostCaptionSrc("No Smoking");
        post.setCategory("animal"); 
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/5.jpg");
        post.setPostCaptionSrc("Custom House 3d Model");
        post.setCategory("lifestyle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/6.jpg");
        post.setPostCaptionSrc("Suletta and Eri <3");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/7.jpg");
        post.setPostCaptionSrc("Ram");
        post.setCategory("cute");   
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/8.jpg");
        post.setPostCaptionSrc("Character Sprite Sheet");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/9.jpg");
        post.setPostCaptionSrc("Bottle of Tree Spirits Pin");
        post.setCategory("lifestyle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/10.jpg");
        post.setPostCaptionSrc("Cat matching profiles");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/11.jpg");
        post.setPostCaptionSrc("Character Emotion Sprite Sheet");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/12.jpg");
        post.setPostCaptionSrc("Minecraft Book");
        post.setCategory("lifestyle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/13.jpg");
        post.setPostCaptionSrc("Smug Cat");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/14.jpg");
        post.setPostCaptionSrc("Freiren");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/15.jpg");
        post.setPostCaptionSrc("Vroom");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/16.jpg");
        post.setPostCaptionSrc("Suzuki GSXR");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/17.jpg");
        post.setPostCaptionSrc("Sleeping with Snorlax");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/18.jpg");
        post.setPostCaptionSrc("Coding lifestyle");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/Media.jpg");
        post.setPostCaptionSrc("MARTINNNNN");
        post.setCategory("martin");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/19.jpg");
        post.setPostCaptionSrc("Tranquility in Death");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/20.jpg");
        post.setPostCaptionSrc("Salvation");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/21.jpg");
        post.setPostCaptionSrc("Longrides");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/22.jpg");
        post.setPostCaptionSrc("Betreyal often comes unexpected");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/23.jpg");
        post.setPostCaptionSrc("Slaylax");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/24.jpg");
        post.setPostCaptionSrc("Bike Photography");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/25.jpg");
        post.setPostCaptionSrc("The One Piece");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/26.jpg");
        post.setPostCaptionSrc("Arf arf Arf!");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/27.jpg");
        post.setPostCaptionSrc("Royal Enfield Continental GT 650");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/28.jpg");
        post.setPostCaptionSrc("Interceptor 650");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/29.jpg");
        post.setPostCaptionSrc("Meowlax");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/30.jpg");
        post.setPostCaptionSrc("Gencar");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/31.jpg");
        post.setPostCaptionSrc("Cats Passion");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/32.jpg");
        post.setPostCaptionSrc("Hi :3");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/33.jpg");
        post.setPostCaptionSrc("Bikers rally");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/34.jpg");
        post.setPostCaptionSrc("Enlightenment");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/35.jpg");
        post.setPostCaptionSrc("Depressed? nah I'm good");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/36.jpg");
        post.setPostCaptionSrc("Codehub");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/37.jpg");
        post.setPostCaptionSrc("Duck :3");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/38.jpg");
        post.setPostCaptionSrc("The Workshop");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/39.jpg");
        post.setPostCaptionSrc("Harley Davidson Sportster");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/40.jpg");
        post.setPostCaptionSrc("Duality");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/41.jpg");
        post.setPostCaptionSrc("do it.");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/42.jpg");
        post.setPostCaptionSrc("Freedom");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/43.jpg");
        post.setPostCaptionSrc("Blachold Mining");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/44.jpg");
        post.setPostCaptionSrc("Take the risk");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/45.jpg");
        post.setPostCaptionSrc("Collection");
        post.setCategory("lifestyle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/47.jpg");
        post.setPostCaptionSrc("Baymax");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/48.jpg");
        post.setPostCaptionSrc("Fall");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/49.jpg");
        post.setPostCaptionSrc("Schizo");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/50.jpg");
        post.setPostCaptionSrc("You're my friend now");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/51.jpg");
        post.setPostCaptionSrc("NOOOOOOOOO");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/52.jpg");
        post.setPostCaptionSrc("Gengar");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/53.jpg");
        post.setPostCaptionSrc("Capy-hand me the cash");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/54.jpg");
        post.setPostCaptionSrc("Rich Cat");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/55.jpg");
        post.setPostCaptionSrc("Nike");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/56.jpg");
        post.setPostCaptionSrc("Ducati Monster 900s");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/57.jpg");
        post.setPostCaptionSrc("Deal or Stay?");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/58.jpg");
        post.setPostCaptionSrc("Cawr");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/59.jpg");
        post.setPostCaptionSrc("Sup");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/60.jpg");
        post.setPostCaptionSrc("UY TUMBA");
        post.setCategory("motorcycle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/61.jpg");
        post.setPostCaptionSrc("Just as nature intended");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/62.jpg");
        post.setPostCaptionSrc("Alligustor");
        post.setCategory("cute");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/63.jpg");
        post.setPostCaptionSrc("To the moon and back.");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/64.jpg");
        post.setPostCaptionSrc("Alligathug");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/65.jpg");
        post.setPostCaptionSrc("Isn't this nice?");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/66.jpg");
        post.setPostCaptionSrc("YOU FOOL!");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/67.jpg");
        post.setPostCaptionSrc("shoe Collection");
        post.setCategory("lifestyle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/68.jpg");
        post.setPostCaptionSrc("Damn thats a lotta clothes");
        post.setCategory("lifestyle");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/69.jpg");
        post.setPostCaptionSrc("Life on a line");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/70.jpg");
        post.setPostCaptionSrc("TOKYOOOOOO drift");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/71.jpg");
        post.setPostCaptionSrc("No more limits.");
        post.setCategory("wallpaper");
        post.setAuthor("demo");
        allPosts.add(post);

        post = new Post();
        post.setPostImageSrc("/app/main/image/post/72.jpg");
        post.setPostCaptionSrc("Ninja Tortol");
        post.setCategory("animal");
        post.setAuthor("demo");
        allPosts.add(post);

        // ... Add more demo posts as needed
    }

    public static void addPost(Post post) {
        allPosts.add(post);
    }

    public static List<Post> getAllPosts() {
        return Collections.unmodifiableList(allPosts);
    }

    public static void clearAllPosts() {
        allPosts.clear();
    }

    public static void removePost(Post post) {
        allPosts.remove(post);
    }
}
