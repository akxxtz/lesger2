
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DataVisualization {
    private static final int GRAPH_WIDTH = 50;
    private static final int GRAPH_HEIGHT = 10;

    public static void showSpendingTrends(List<Transaction> transactions) {
        System.out.println("\n=== Spending Trends ===");

        // Group transactions by month
        Map<String, BigDecimal> monthlySpending = transactions.stream()
                .filter(t -> t.getType().equals("credit"))
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getYear() + "-" + String.format("%02d", t.getDate().getMonthValue()),
                        Collectors.reducing(BigDecimal.ZERO,
                                Transaction::getAmount,
                                BigDecimal::add)));

        // Find max value for scaling
        BigDecimal maxSpending = monthlySpending.values().stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ONE);

        // Draw bar chart
        monthlySpending.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    int barLength = entry.getValue()
                            .multiply(BigDecimal.valueOf(GRAPH_WIDTH))
                            .divide(maxSpending, 0, BigDecimal.ROUND_DOWN)
                            .intValue();
                    System.out.printf("%s |%-" + GRAPH_WIDTH + "s| $%.2f%n",
                            entry.getKey(),
                            "=".repeat(barLength),
                            entry.getValue());
                });
    }

    public static void showSpendingDistribution(List<Transaction> transactions) {
        System.out.println("\n=== Spending Distribution ===");

        // Group transactions by description (category)
        Map<String, BigDecimal> categorySpending = transactions.stream()
                .filter(t -> t.getType().equals("credit"))
                .collect(Collectors.groupingBy(
                        Transaction::getDescription,
                        Collectors.reducing(BigDecimal.ZERO,
                                Transaction::getAmount,
                                BigDecimal::add)));

        BigDecimal totalSpending = categorySpending.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate and display percentages
        categorySpending.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(entry -> {
                    BigDecimal percentage = entry.getValue()
                            .multiply(BigDecimal.valueOf(100))
                            .divide(totalSpending, 2, BigDecimal.ROUND_HALF_UP);
                    int barLength = percentage
                            .multiply(BigDecimal.valueOf(GRAPH_WIDTH))
                            .divide(BigDecimal.valueOf(100), 0, BigDecimal.ROUND_DOWN)
                            .intValue();
                    System.out.printf("%-15s |%-" + GRAPH_WIDTH + "s| %.1f%% ($%.2f)%n",
                            entry.getKey(),
                            "=".repeat(barLength),
                            percentage,
                            entry.getValue());
                });
    }

    public static void showSavingsGrowth(BigDecimal currentSavings, int savingsPercentage) {
        System.out.println("\n=== Savings Growth Projection ===");
        BigDecimal monthlyDebit = BigDecimal.valueOf(1000); // Example average

        for (int month = 1; month <= 12; month++) {
            BigDecimal savingsIncrease = monthlyDebit
                    .multiply(BigDecimal.valueOf(savingsPercentage))
                    .divide(BigDecimal.valueOf(100));
            currentSavings = currentSavings.add(savingsIncrease);

            int barLength = currentSavings
                    .multiply(BigDecimal.valueOf(GRAPH_WIDTH))
                    .divide(monthlyDebit.multiply(BigDecimal.valueOf(12)), 0, BigDecimal.ROUND_DOWN)
                    .intValue();

            System.out.printf("Month %-2d |%-" + GRAPH_WIDTH + "s| $%.2f%n",
                    month,
                    "=".repeat(barLength),
                    currentSavings);
        }
    }

    public static void showLoanRepayment(Loan loan) {
        if (loan == null || loan.getStatus().equals("repaid")) {
            System.out.println("\nNo active loan to display.");
            return;
        }

        System.out.println("\n=== Loan Repayment Progress ===");
        BigDecimal totalAmount = loan.getPrincipalAmount()
                .multiply(BigDecimal.ONE.add(
                        BigDecimal.valueOf(loan.getInterestRate())
                                .divide(BigDecimal.valueOf(100))));
        BigDecimal remaining = loan.getOutstandingBalance();
        BigDecimal paid = totalAmount.subtract(remaining);

        int progressBarLength = paid
                .multiply(BigDecimal.valueOf(GRAPH_WIDTH))
                .divide(totalAmount, 0, BigDecimal.ROUND_DOWN)
                .intValue();

        System.out.printf("Progress |%-" + GRAPH_WIDTH + "s| %.1f%%%n",
                "=".repeat(progressBarLength),
                paid.multiply(BigDecimal.valueOf(100)).divide(totalAmount, 2, BigDecimal.ROUND_HALF_UP));
        System.out.printf("Paid: $%.2f | Remaining: $%.2f | Total: $%.2f%n",
                paid, remaining, totalAmount);
    }
}