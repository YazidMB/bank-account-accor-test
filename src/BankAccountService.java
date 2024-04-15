import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BankAccountService {
    private final Map<String, BankAccount> ACCOUNTS = Map.of("12345X", new BankAccount("12345X"));

    public void deposit(final String accountNumber, final Double amount) {
        final BankAccount bankAccount = getBankAccount(accountNumber);
        bankAccount.deposit(amount);
    }

    public void withdrawal(final String accountNumber, final Double amount) {
        final BankAccount bankAccount = getBankAccount(accountNumber);
        final boolean withdrawalDone = bankAccount.withdrawal(amount);
        if(!withdrawalDone) {
            throw new RuntimeException("Insufficient balance to withdraw the amount " + amount);
        }
    }

    public void printAccountStatement(final String accountNumber) {
        final BankAccount bankAccount = getBankAccount(accountNumber);
        final List<BankAccountOperation> history = bankAccount.getHistory();

        System.out.print("""
                -------------------------- Bank account statement ---------------------------
                | Date \t\t\t\t\t\t\t\t| Type \t\t\t| Amount \t| Balance \t|
                -----------------------------------------------------------------------------
                """);
        history
                .stream()
                .sorted(Comparator.comparing(BankAccountOperation::date))
                .forEachOrdered(operation -> System.out.println(
                        "| " + operation.date() + " \t| " +
                                operation.type() + (BankAccountOperationType.DEPOSIT.equals(operation.type()) ? " \t\t| " : " \t| ") +
                                operation.amount() + " \t| " +
                                operation.balance() + " \t|"));
        System.out.println("-----------------------------------------------------------------------------");
    }

    private BankAccount getBankAccount(final String accountNumber) {
        return Optional.ofNullable(ACCOUNTS.get(accountNumber))
            .orElseThrow(() -> new RuntimeException("No account having the number " + accountNumber + " exists"));
    }
}
