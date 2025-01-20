package org.example;


import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.BaseClass;
import org.example.entities.Day;
import org.example.entities.Product;
import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    static User user = new User();
    static Product product = new Product();
    static Day day = new Day();
    static Day day2 = new Day();
    static Day day3 = new Day();

    public static void main(String[] args) {
        create();
        List<User> entities = new ArrayList<User>();
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Day.class);
        cfg.addAnnotatedClass(BaseClass.class);
        cfg.configure();


        try (SessionFactory sf = cfg.buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();
            String hql = "SELECT u FROM User u JOIN FETCH u.days"; //joinfetch
            entities = session.createQuery(hql, User.class).getResultList();
            session.getTransaction().commit();
            System.out.println(entities.size());

            System.out.println(entities.get(0).getDays().get(0).getDefCalories());

            System.out.println("entitygraph");

            EntityManagerFactory emf = sf.unwrap(EntityManagerFactory.class);
            EntityManager entityManager = emf.createEntityManager();

            entityManager.getTransaction().begin();

            EntityGraph<User> entityGraph = entityManager.createEntityGraph(User.class);
            entityGraph.addAttributeNodes("days");

            Map<String, Object> properties = new HashMap<>();
            properties.put("javax.persistence.loadgraph", entityGraph);

            Long userId = 32L;
            User user = entityManager.find(User.class, userId, properties);

            entityManager.getTransaction().commit();

            if (user != null) {
                System.out.println(user.getDays().size());
                if (!user.getDays().isEmpty()) {
                    System.out.println(user.getDays().get(2).getDefCalories());
                }
            }
        }


    }

    public static void create() {
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


    }
}
