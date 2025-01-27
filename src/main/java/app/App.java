package app;

import app.entities.Day;
import app.entities.Product;
import app.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App
{
    static User user = new User();
    static Product product = new Product();
    static Day day = new Day();
    static Day day2 = new Day();
    static Day day3 = new Day();
    public static void main(String[] args){
        create();
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Day.class);
User user1 = new User();
        user1.setAge(110);
        user1.setName("John1");
        user1.setWeight(81);
        user1.setEmail("john@gmail.com");
        user1.setPassword("1234");
        cfg.configure();
        try (SessionFactory sf = cfg.buildSessionFactory()) {
            Session session = sf.openSession();
                session.beginTransaction();
                session.persist(user1);
//                session.persist(day);
//                session.persist(day2);
//                session.persist(day3);
//                session.persist(product);
                session.getTransaction().commit();
//            Integer  i = 17;
//            System.out.println(session.find(Day.class,  i));
        }
    }
    public static void create(){
        user.setAge(10);
        user.setName("John");
        user.setWeight(80);
        product.setPrice(80);
        product.setWeight(300);
        product.setName("Fish");
        product.setCalories(500);
        day.setDay(1);
        day.setDefCalories(1000);
        day.setProfCalories(1000);
        day.setUser(user);
        day.getProducts().add(product);
        day2.setDay(1);
        day2.setDefCalories(1000);
        day2.setProfCalories(2000);
        day2.setUser(user);
        day2.getProducts().add(product);
        day3.setDay(1);
        day3.setDefCalories(2000);
        day3.setProfCalories(1000);
        day3.setUser(user);
        day3.getProducts().add(product);
        user.getDays().add(day);
        product.getDays().add(day);
        day2.setDay(2);
        day3.setDay(3);
        product.getDays().add(day2);
        product.getDays().add(day3);
        user.setEmail("john@gmail.com");
        user.setPassword("1234");


    }
}
