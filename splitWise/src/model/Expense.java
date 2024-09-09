package model;

import java.util.List;

public class Expense {
    private String payerId;
    private double amount;
    private List<String> involvedUsers;
    private ExpenseType type;
    private List<Double> splits; // For PERCENT or EXACT splits

    public Expense(String payerId, double amount, List<String> involvedUsers, ExpenseType type, List<Double> splits) {
        this.payerId = payerId;
        this.amount = amount;
        this.involvedUsers = involvedUsers;
        this.type = type;
        this.splits = splits;
    }

    public String getPayerId() { return payerId; }
    public double getAmount() { return amount; }
    public List<String> getInvolvedUsers() { return involvedUsers; }
    public ExpenseType getType() { return type; }
    public List<Double> getSplits() { return splits; }
}
