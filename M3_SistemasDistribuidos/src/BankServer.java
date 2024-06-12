import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BankServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            BankAccountImpl account = new BankAccountImpl();
            Naming.rebind("rmi://localhost/BankAccount", account);
            System.out.println("Bank Account RMI server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}