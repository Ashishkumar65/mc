package repository;

import java.util.HashMap;
import java.util.Map;

public class BalanceRepository {
    // Maps User1 -> (User2 -> Balance between User1 and User2)
    private Map<String, Map<String, Double>> balances = new HashMap<>();

    public void updateBalance(String user1, String user2, double amount) {
        balances.putIfAbsent(user1, new HashMap<>());
        Map<String, Double> user1Balances = balances.get(user1);
        user1Balances.put(user2, user1Balances.getOrDefault(user2, 0.0) + amount);
    }

    public double getBalance(String user1, String user2) {
        return balances.getOrDefault(user1, new HashMap<>()).getOrDefault(user2, 0.0);
    }

    public Map<String, Double> getUserBalances(String userId) {
        return balances.getOrDefault(userId, new HashMap<>());
    }

    public Map<String, Map<String, Double>> getAllBalances() {
        return balances;
    }
}