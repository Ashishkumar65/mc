package controller;

import model.Post;
import model.User;
import service.PostService;
import service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialMediaController {
    private UserService userService;
    private PostService postService;

    public SocialMediaController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public Post createPost(String userId, String content) {
        return postService.createPost(userId, content, LocalDateTime.now());
    }

    public void deletePost(String postId) {
        postService.deletePost(postId);
    }

    public List<Post> getFeed(String userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return postService.getFeed(userId, user.getFollowing());
        }
        return List.of(); // Return empty list if user not found
    }

    public List<Post> getFeedPaginated(String userId, int page, int size) {
        User user = userService.getUser(userId);
        if (user != null) {
            return postService.getFeedPaginated(userId, user.getFollowing(), page, size);
        }
        return List.of(); // Return empty list if user not found
    }

    public void followUser(String followerId, String followeeId) {
        userService.followUser(followerId, followeeId);
    }

    public void unfollowUser(String followerId, String followeeId) {
        userService.unfollowUser(followerId, followeeId);
    }
}

