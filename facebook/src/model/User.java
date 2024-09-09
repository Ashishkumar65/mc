package model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private String userId;

    private Set<String> following;

    public User(String name, String userId){
        this.name = name;
        this.userId = userId;
        this.following = new HashSet<>();
    }

    public String getUserName(){
        return name;
    }

    public String getUserId(){
        return userId;
    }

    public Set<String> getFollowing(){
        return following;
    }

    public void followUser(String userId){
        following.add(userId);
    }

    public void unfollowUser(String userId){
        if(following.contains(userId)){
            following.remove(userId);
        } else{
            System.out.println("User is not following this user");
        }

    }

}
