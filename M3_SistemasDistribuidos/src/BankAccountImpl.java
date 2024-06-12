import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.NumberFormat;
import java.util.Locale;

public class BankAccountImpl extends UnicastRemoteObject implements BankAccount {
    private double balance;
    private NumberFormat currencyFormat;

    protected BankAccountImpl() throws RemoteException {
        super();
        balance = 0.0;
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    }

    @Override
    public synchronized void deposit(double amount) throws RemoteException {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + currencyFormat.format(amount) + ", New Balance: " + currencyFormat.format(balance));
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }

    @Override
    public synchronized void withdraw(double amount) throws RemoteException {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + currencyFormat.format(amount) + ", New Balance: " + currencyFormat.format(balance));
        } else {
            System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
        }
    }

    @Override
    public synchronized double getBalance() throws RemoteException {
        return balance;
    }
}
