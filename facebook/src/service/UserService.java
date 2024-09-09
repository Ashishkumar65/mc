package service;

import model.User;

import java.util.Map;
import java.util.UUID;

public class UserService {
    private Map<String, User> users;

    public UserService(Map<String, User> users) {
        this.users = users;
    }
    public User createUser(String name) {
      String userId = UUID.randomUUID().toString();
        User user = new User(name, userId);
        users.put(userId, user);
        return user;
    }

    public void followUser(String followerId, String followeeId) {
        User follower = users.get(followerId);
        if (follower != null) {
            follower.followUser(followeeId);
        }
    }

    public void unfollowUser(String followerId, String followeeId) {
        User follower = users.get(followerId);
        if (follower != null) {
            follower.unfollowUser(followeeId);
        }
    }
    public User getUser(String userId) {
        return users.get(userId);
    }

}
