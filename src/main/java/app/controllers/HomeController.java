package app.controllers;

import app.entities.Day;
import app.entities.Product;
import app.entities.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/getAllUsers")
    public String viewHomePage() {
        List<User> users = new ArrayList<User>();
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Day.class);
        cfg.configure();
        try (SessionFactory sf = cfg.buildSessionFactory()) {
            Session session = sf.openSession();
            session.beginTransaction();
            String hql = "SELECT u FROM User u JOIN FETCH u.days";
            users = session.createQuery(hql, User.class).getResultList();
            session.getTransaction().commit();
            session.close();
            List<User> result = new ArrayList<>(users);
            return result.toString();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUserPage(@RequestBody User user) {
        user.setId(0);
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Day.class);
        cfg.configure();
        try (SessionFactory sf = cfg.buildSessionFactory(); Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating user: " + e.getMessage());
        }
    }


    @DeleteMapping("/deleteUser/{name}")
    public String deleteUser(@PathVariable("name") String name) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Day.class);
        cfg.configure();
        String responseMessage;
        try (SessionFactory sf = cfg.buildSessionFactory(); Session session = sf.openSession()) {
            session.beginTransaction();
            String hql = "FROM User u WHERE u.name LIKE :name";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("name", name);
            User user = query.uniqueResult();
            if (user != null) {
                session.remove(user);
                responseMessage = "User removed successfully.";
            } else {
                responseMessage = "User not found.";
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage = "Error occurred while deleting user.";
        }
        return responseMessage; // Возвращение сообщения о результате операции
    }

    @PutMapping("/putUser/{id}")
    public String putUser(@PathVariable("id") long id, @RequestBody String name) {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(Product.class);
        cfg.addAnnotatedClass(Day.class);
        cfg.configure();
        String responseMessage;
        try (SessionFactory sf = cfg.buildSessionFactory(); Session session = sf.openSession()) {
            session.beginTransaction();
            String hql = "UPDATE User u SET u.name = :newName WHERE u.id = :id";
            int result = session.createQuery(hql)
                    .setParameter("newName", name)
                    .setParameter("id", id)
                    .executeUpdate();
            session.getTransaction().commit();
            responseMessage = "User updated successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage = "Error occurred while deleting user.";
        }
        return responseMessage; // Возвращение сообщения о результате операции
    }

}
