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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        cfg.addAnnotatedClass(BaseClass.class);

        cfg.configure();
        try (SessionFactory sf = cfg.buildSessionFactory()) {
            Session session = sf.openSession();
                session.beginTransaction();
                String hql = "SELECT u FROM Day u JOIN FETCH u.products";
                List<Day> entities = session.createQuery(hql, Day.class).getResultList();
                session.getTransaction().commit();
            System.out.println(entities.size());

//            entity---------------------------------------

            EntityManagerFactory emf = sf.unwrap(EntityManagerFactory.class);
            EntityManager entityManager = emf.createEntityManager();

            entityManager.getTransaction().begin();

            EntityGraph<Day> entityGraph = entityManager.createEntityGraph(Day.class);
            entityGraph.addAttributeNodes("products");

            Map<String, Object> properties = new HashMap<>();
            properties.put("javax.persistence.loadgraph", entityGraph);

            Long dayId = 38L;
            Day day4 = entityManager.find(Day.class, dayId, properties);
            if (day4 != null) {
                System.out.println(day4.getProducts().size());
                if (!day4.getProducts().isEmpty()) {
                    System.out.println(day4.getProducts().get(0).getName());
                }
            }
            String hql2 = "SELECT u FROM Day u";
            List<Day> days = entityManager.createQuery(hql2, Day.class).getResultList();
            entityManager.getTransaction().commit();
            for (Day day : days) {
                System.out.println(day.getDay());
            }

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


    }
}
