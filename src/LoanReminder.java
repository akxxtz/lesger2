import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LoanReminder {
    private static final int WARNING_DAYS = 5;  // Days before payment to start warning

    public static void checkLoanStatus(List<Loan> loans, int userId) {
        loans.stream()
                .filter(loan -> loan.getUserId() == userId && loan.getStatus().equals("active"))
                .forEach(LoanReminder::processLoanReminder);
    }

    private static void processLoanReminder(Loan loan) {
        LocalDate now = LocalDate.now();
        LocalDate dueDate = loan.getCreatedAt().plusMonths(loan.getRepaymentPeriod());

        if (now.isAfter(dueDate)) {
            System.out.println("\n!!! LOAN OVERDUE ALERT !!!");
            System.out.printf("Your loan of $%.2f is overdue! Please make a payment immediately.%n",
                    loan.getOutstandingBalance());
            System.out.println("You cannot make new transactions until the loan is paid.");
            return;
        }

        long daysUntilDue = ChronoUnit.DAYS.between(now, dueDate);
        if (daysUntilDue <= WARNING_DAYS) {
            System.out.println("\n=== LOAN PAYMENT REMINDER ===");
            System.out.printf("Your loan payment of $%.2f is due in %d days!%n",
                    loan.getOutstandingBalance(),
                    daysUntilDue);

            // Calculate monthly payment suggestion
            BigDecimal monthlyPayment = loan.getOutstandingBalance()
                    .divide(BigDecimal.valueOf(loan.getRepaymentPeriod()), 2, BigDecimal.ROUND_HALF_UP);
            System.out.printf("Suggested monthly payment: $%.2f%n", monthlyPayment);
        }
    }
}
