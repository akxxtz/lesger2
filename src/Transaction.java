import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private int userId;
    private String type;
    private BigDecimal amount;
    private String description;
    private LocalDate date;

    public Transaction(int transactionId, int userId, String type, BigDecimal amount,
                       String description, LocalDate date) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.type = type;
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.description = description;
        this.date = date;
    }

    // Getters
    public int getTransactionId() { return transactionId; }
    public int getUserId() { return userId; }
    public String getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
}