import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String number;
    private Double balance = 0D;
    private List<BankAccountOperation> history = new ArrayList<>();

    public BankAccount(final String number) {
        this.number = number;
    }

    public void deposit(final Double amount) {
        balance += amount;
        history.add(new BankAccountOperation(LocalDateTime.now(), BankAccountOperationType.DEPOSIT, amount, balance));
    }

    public boolean withdrawal(final Double amount) {
        if(amount > balance) {
            return false;
        }

        balance -= amount;
        history.add(new BankAccountOperation(LocalDateTime.now(), BankAccountOperationType.WITHDRAWAL, amount, balance));
        return true;
    }

    public List<BankAccountOperation> getHistory() {
        return history;
    }
}
