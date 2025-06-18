package app.main;

import java.util.*;
import java.io.*;

public class savedPostsRepository {
    private static final String FILE_PATH = "SavedPosts.txt";
    private static final Map<String, List<Post>> userSavedPosts = new HashMap<>();

    // Load saved posts for a user from file
    public static void loadFromFile(String username) {
        userSavedPosts.put(username, new ArrayList<>());
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                if (parts.length == 2 && parts[0].equals(username)) {
                    String[] posts = parts[1].split(";");
                    for (String postStr : posts) {
                        if (postStr.trim().isEmpty()) continue;
                        String[] fields = postStr.split(",", 3);
                        if (fields.length == 3) {
                            Post post = new Post();
                            post.setPostImageSrc(fields[0]);
                            post.setPostCaptionSrc(fields[1]);
                            post.setCategory(fields[2]);
                            userSavedPosts.get(username).add(post);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save all users' saved posts to file
    public static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, List<Post>> entry : userSavedPosts.entrySet()) {
                String username = entry.getKey();
                List<Post> posts = entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(username).append("|");
                for (Post post : posts) {
                    sb.append(post.getPostImageSrc()).append(",")
                      .append(post.getPostCaptionSrc()).append(",")
                      .append(post.getCategory()).append(";");
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove a post for a user
    public static void removePost(String username, Post post) {
        List<Post> posts = userSavedPosts.get(username);
        if (posts == null) return;
        Iterator<Post> iterator = posts.iterator();
        while (iterator.hasNext()) {
            Post p = iterator.next();
            if (p.getPostImageSrc().equals(post.getPostImageSrc()) &&
                p.getPostCaptionSrc().equals(post.getPostCaptionSrc())) {
                iterator.remove();
                saveToFile();
                return;
            }
        }
    }

    // Add a post for a user
    public static void addPost(String username, Post post) {
        userSavedPosts.computeIfAbsent(username, k -> new ArrayList<>());
        // Prevent duplicates
        for (Post p : userSavedPosts.get(username)) {
            if (p.getPostImageSrc().equals(post.getPostImageSrc()) &&
                p.getPostCaptionSrc().equals(post.getPostCaptionSrc())) {
                return; // Already saved
            }
        }
        userSavedPosts.get(username).add(post);
        saveToFile();
    }

    // Get saved posts for a user
    public static List<Post> getSavedPosts(String username) {
        return Collections.unmodifiableList(userSavedPosts.getOrDefault(username, new ArrayList<>()));
    }

    // Clear saved posts for a user
    public static void clear(String username) {
        userSavedPosts.remove(username);
        saveToFile();
    }
}
