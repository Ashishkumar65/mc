import controller.SocialMediaController;
import model.Post;
import model.User;
import service.PostService;
import service.UserService;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, User> users = new HashMap<>();
        UserService userService = new UserService(users);

        List<Post> posts = new ArrayList<>();
        PostService postService = new PostService(posts);

        SocialMediaController controller = new SocialMediaController(userService, postService);

        // Create users
        User alice = userService.createUser("Alice");
        User bob = userService.createUser("Bob");

        // Follow users
        controller.followUser(alice.getUserId(), bob.getUserId());

        // Create posts
        controller.createPost(alice.getUserId(), "Alice's first post");
        controller.createPost(bob.getUserId(), "Bob's first post");

        // Get feeds
        System.out.println("Alice's Feed: " + controller.getFeed(alice.getUserId()));
        System.out.println("Alice's Paginated Feed (Page 0, Size 1): ");
        List<Post> paginatedFeed = controller.getFeedPaginated(alice.getUserId(), 0, 1);
        for(Post post : paginatedFeed) {
            System.out.println(post.getContent());
        }

        // Unfollow and delete post
        controller.unfollowUser(alice.getUserId(), bob.getUserId());
        controller.deletePost(bob.getUserId());

        // Get updated feeds
        System.out.println("Alice's Feed After Unfollow and Post Deletion: " + controller.getFeed(alice.getUserId()));
    }

}