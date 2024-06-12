import java.rmi.Naming;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {
        try {
            BankAccount account = (BankAccount) Naming.lookup("rmi://localhost/BankAccount");
            Scanner scanner = new Scanner(System.in);
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            int choice = -1;

            while (choice != 0) {
                System.out.println("Please select an operation:");
                System.out.println("0 - Exit");
                System.out.println("1 - Check Balance");
                System.out.println("2 - Deposit");
                System.out.println("3 - Withdraw");
                choice = scanner.nextInt();

                switch (choice) {
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    case 1:
                        System.out.println("Current Balance: " + currencyFormat.format(account.getBalance()));
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        System.out.println("Balance after deposit: " + currencyFormat.format(account.getBalance()));
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        System.out.println("Balance after withdrawal: " + currencyFormat.format(account.getBalance()));
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}