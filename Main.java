
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
public class Main{
    private SessionFactory sessionFactory;

    public Main() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void searchCustomersByName(String nameFilter) {
        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery("FROM Customer WHERE name LIKE :filter", Customer.class);
        query.setParameter("filter", "%" + nameFilter + "%");
        List<Customer> customers = query.getResultList();

        session.close();

        if (customers.isEmpty()) {
            System.out.println("No records found");
        } else {
            for (Customer customer : customers) {
                System.out.println("Customer Name: " + customer.getName());
                System.out.println("Customer ID: " + customer.getId());
                System.out.println("Address: " + customer.getAddress().getStreet() + ", " +
                        customer.getAddress().getCity() + ", " + customer.getAddress().getState() +
                        " - " + customer.getAddress().getZipCode());
                System.out.println("Orders:");
                for (Order order : customer.getOrders()) {
                    System.out.println("Order Number: " + order.getNumber());
                    System.out.println("Order Date: " + order.getDate());
                    System.out.println("Order Item: " + order.getItem());
                    System.out.println("Order Price: " + order.getPrice());
                    System.out.println("-------------");
                }
            }
        }
    }

    public void addCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            System.out.println("Customer added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failure: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
            System.out.println("Customer updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failure: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void deleteCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
            System.out.println("Customer deleted");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failure: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    public void searchOrderByNumber(int orderNumber) {
        Session session = sessionFactory.openSession();
        Query<Orders> query = session.createQuery("FROM Orders WHERE number = :orderNumber", Orders.class);
        query.setParameter("orderNumber", orderNumber);
        List<Orders> orders = query.getResultList();

        session.close();

        if (orders.isEmpty()) {
            System.out.println("No records found");
        } else {
            for (Orders order : orders) {
                System.out.println("Order Number: " + order.getNumber());
                System.out.println("Order Date: " + order.getDate());
                System.out.println("Order Item: " + order.getItem());
                System.out.println("Order Price: " + order.getPrice());
                System.out.println("Customer ID: " + order.getCustomer().getId());
                System.out.println("-------------");
            }
        }
    }

    public void addOrder(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            System.out.println("Order added");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failure: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateOrder(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
            System.out.println("Order updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failure: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void deleteOrder(Orders order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
            System.out.println("Order deleted");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Failure: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}