package model;

import java.time.LocalDateTime;

public class Post {
    private String postId;
    private String userId;
    private String  content;
    private LocalDateTime createdAt;

    public Post(String postId, String userId, String content, LocalDateTime createdAt){
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
    }

    public String getPostId(){
        return postId;
    }

    public String getUserId(){
        return userId;
    }

    public String getContent(){
        return content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }


}
