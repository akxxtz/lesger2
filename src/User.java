import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class User {
    private int userId;
    private String name;
    private String email;
    private String passwordHash;
    private BigDecimal balance;
    private BigDecimal savings;
    private BigDecimal loan;
    private int savingsPercentage;
    private boolean savingsActive;
    private LocalDate lastLoginDate;

    public User(int userId, String name, String email, String passwordHash) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.savings = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.loan = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        this.savingsPercentage = 0;
        this.savingsActive = false;
        this.lastLoginDate = LocalDate.now();
    }

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public BigDecimal getBalance() { return balance; }
    public BigDecimal getSavings() { return savings; }
    public BigDecimal getLoan() { return loan; }
    public int getSavingsPercentage() { return savingsPercentage; }
    public boolean isSavingsActive() { return savingsActive; }
    public LocalDate getLastLoginDate() { return lastLoginDate; }

    // Setters
    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }
    public void setSavings(BigDecimal savings) {
        this.savings = savings.setScale(2, RoundingMode.HALF_UP);
    }
    public void setLoan(BigDecimal loan) {
        this.loan = loan.setScale(2, RoundingMode.HALF_UP);
    }
    public void setSavingsPercentage(int percentage) {
        this.savingsPercentage = percentage;
    }
    public void setSavingsActive(boolean active) {
        this.savingsActive = active;
    }
    public void setLastLoginDate(LocalDate date) {
        this.lastLoginDate = date;
    }
}