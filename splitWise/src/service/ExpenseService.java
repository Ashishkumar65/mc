// ExpenseService.java
package service;

import model.Expense;
import repository.BalanceRepository;

import java.util.List;

public class ExpenseService {
    private BalanceRepository balanceRepository;

    public ExpenseService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public void addExpense(Expense expense) {
        List<String> users = expense.getInvolvedUsers();
        double totalAmount = expense.getAmount();
        String payerId = expense.getPayerId();

        switch (expense.getType()) {
            case EQUAL:
                double equalSplit = Math.round((totalAmount / users.size()) * 100.0) / 100.0;
                for (String user : users) {
                    if (!user.equals(payerId)) {
                        balanceRepository.updateBalance(user, payerId, equalSplit);
                    }
                }
                break;

            case EXACT:
                List<Double> exactSplits = expense.getSplits();
                for (int i = 0; i < users.size(); i++) {
                    if (!users.get(i).equals(payerId)) {
                        balanceRepository.updateBalance(users.get(i), payerId, exactSplits.get(i));
                    }
                }
                break;

            case PERCENT:
                List<Double> percentages = expense.getSplits();
                for (int i = 0; i < users.size(); i++) {
                    if (!users.get(i).equals(payerId)) {
                        double share = (totalAmount * percentages.get(i)) / 100.0;
                        balanceRepository.updateBalance(users.get(i), payerId, Math.round(share * 100.0) / 100.0);
                    }
                }
                break;

            default:
                System.out.println("Invalid expense type");
        }
    }
}
