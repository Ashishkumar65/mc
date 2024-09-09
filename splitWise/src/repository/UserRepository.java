// UserRepository.java
package repository;

import model.User;
import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public Map<String, User> getAllUsers() {
        return users;
    }
}


