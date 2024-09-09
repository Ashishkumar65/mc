import model.Expense;
import model.ExpenseType;
import model.User;
import repository.BalanceRepository;
import repository.UserRepository;
import service.BalanceService;
import service.ExpenseService;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        BalanceRepository balanceRepository = new BalanceRepository();
        ExpenseService expenseService = new ExpenseService(balanceRepository);
        BalanceService balanceService = new BalanceService(balanceRepository);

        // Create users
        userRepository.addUser(new User("u1", "User1", "user1@example.com", "1234567890"));
        userRepository.addUser(new User("u2", "User2", "user2@example.com", "0987654321"));
        userRepository.addUser(new User("u3", "User3", "user3@example.com", "1122334455"));
        userRepository.addUser(new User("u4", "User4", "user4@example.com", "5566778899"));

        // Initial balance check
        balanceService.showAllBalances();

        // Add an expense (EQUAL)
        expenseService.addExpense(new Expense("u1", 1000, Arrays.asList("u1", "u2", "u3", "u4"), ExpenseType.EQUAL, null));
        balanceService.showBalancesForUser("u1");

        // Add an expense (EXACT)
        expenseService.addExpense(new Expense("u1", 1250, Arrays.asList("u2", "u3"), ExpenseType.EXACT, Arrays.asList(370.0, 880.0)));
        balanceService.showAllBalances();

        // Add an expense (PERCENT)
        expenseService.addExpense(new Expense("u4", 1200, Arrays.asList("u1", "u2", "u3", "u4"), ExpenseType.PERCENT, Arrays.asList(40.0, 20.0, 20.0, 20.0)));
        balanceService.showBalancesForUser("u1");

        // Show final balances
        balanceService.showAllBalances();
    }
}
