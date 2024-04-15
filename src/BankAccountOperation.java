import java.time.LocalDateTime;

public record BankAccountOperation(LocalDateTime date, BankAccountOperationType type, Double amount, Double balance) {
}
