
public class Main {
    public static void main(String[] args) {
        final String accountNumber = "12345X";
        final BankAccountService bankAccountService = new BankAccountService();

        try {
            bankAccountService.deposit(accountNumber,100.D);
            bankAccountService.deposit(accountNumber, 120.D);
            bankAccountService.withdrawal(accountNumber, 110.D);
            bankAccountService.deposit(accountNumber, 700.D);
            bankAccountService.withdrawal(accountNumber, 199.99D);
            bankAccountService.deposit(accountNumber, 100.D);
            bankAccountService.printAccountStatement(accountNumber);
        }
        catch(final RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}