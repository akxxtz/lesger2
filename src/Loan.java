import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {
    private int loanId;
    private int userId;
    private BigDecimal principalAmount;
    private double interestRate;
    private int repaymentPeriod;
    private BigDecimal outstandingBalance;
    private String status;
    private LocalDate createdAt;

    public Loan(int loanId, int userId, BigDecimal principalAmount, double interestRate,
                int repaymentPeriod, BigDecimal outstandingBalance, String status, LocalDate createdAt) {
        this.loanId = loanId;
        this.userId = userId;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.repaymentPeriod = repaymentPeriod;
        this.outstandingBalance = outstandingBalance;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters
    public int getLoanId() { return loanId; }
    public int getUserId() { return userId; }
    public BigDecimal getPrincipalAmount() { return principalAmount; }
    public double getInterestRate() { return interestRate; }
    public int getRepaymentPeriod() { return repaymentPeriod; }
    public BigDecimal getOutstandingBalance() { return outstandingBalance; }
    public String getStatus() { return status; }
    public LocalDate getCreatedAt() { return createdAt; }

    // Setters
    public void setOutstandingBalance(BigDecimal outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
