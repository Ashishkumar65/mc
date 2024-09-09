package service;

import repository.BalanceRepository;

import java.util.Map;

public class BalanceService {
    private BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public void showBalancesForUser(String userId) {
        Map<String, Double> balances = balanceRepository.getUserBalances(userId);
        if (balances.isEmpty()) {
            System.out.println("No balances");
        } else {
            for (Map.Entry<String, Double> entry : balances.entrySet()) {
                double amount = entry.getValue();
                if (amount > 0) {
                    System.out.println(userId + " owes " + entry.getKey() + ": " + amount);
                } else {
                    System.out.println(entry.getKey() + " owes " + userId + ": " + Math.abs(amount));
                }
            }
        }
    }

    public void showAllBalances() {
        Map<String, Map<String, Double>> allBalances = balanceRepository.getAllBalances();
        boolean hasBalances = false;

        for (Map.Entry<String, Map<String, Double>> userBalances : allBalances.entrySet()) {
            for (Map.Entry<String, Double> balance : userBalances.getValue().entrySet()) {
                double amount = balance.getValue();
                if (amount != 0) {
                    hasBalances = true;
                    if (amount > 0) {
                        System.out.println(userBalances.getKey() + " owes " + balance.getKey() + ": " + amount);
                    } else {
                        System.out.println(balance.getKey() + " owes " + userBalances.getKey() + ": " + Math.abs(amount));
                    }
                }
            }
        }

        if (!hasBalances) {
            System.out.println("No balances");
        }
    }
}
