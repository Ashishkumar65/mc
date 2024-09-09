package service;

import model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class PostService {
    private List<Post> posts;

    public PostService(List<Post> posts){
        this.posts = posts;
    }

    public Post createPost(String userId, String content, LocalDateTime createdAt) {
        String postId = UUID.randomUUID().toString();
        Post post = new Post(postId, userId, content,createdAt);
        posts.add(post);
        return post;
    }

    public void deletePost(String postId) {
        if(posts.removeIf(post -> post.getPostId().equals(postId))){
            System.out.println("Post deleted successfully");
        } else{
            System.out.println("Post not found");
        }
    }

    public List<Post> getFeed(String userId, Set<String> following){
        return posts.stream()
                .filter(post->post.getUserId().equals(userId) || following.contains(post.getUserId()))
                .sorted((p1,p2)->p2.getCreatedAt().compareTo(p1.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public List<Post> getFeedPaginated(String userId, Set<String> following, int page, int size) {
        List<Post> fullFeed = getFeed(userId, following);
        int start = Math.min(page * size, fullFeed.size());
        int end = Math.min(start + size, fullFeed.size());
        return fullFeed.subList(start, end);
    }

}
