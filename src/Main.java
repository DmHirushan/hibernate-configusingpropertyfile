import config.SessionFactoryConfig;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer(001, "Kamal", "Colombo", 56000);
        session.save(customer);
        transaction.commit();
        session.close();
    }
}