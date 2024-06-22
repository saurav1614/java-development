package com.Saurav.OnlineExamSystem.dao;

import com.Saurav.OnlineExamSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Repository
public class LoginDao {
    @Autowired
    SessionFactory factory;

    public User saveUser(User user){

        Session session = factory.openSession();
        Transaction tx =session.beginTransaction();
        session.persist(user);

        tx.commit();

        return user;


    }

    public String updateUser(User user){

        Session session = factory.openSession();
        Transaction tx =session.beginTransaction();
        session.merge(user);

        tx.commit();

        return "updated Succesfuly";


    }

    public String DeleteUser(String username){

        Session session = factory.openSession();
        Transaction tx =session.beginTransaction();

        Query<User> query = session.createQuery("delete from User where username=:username");
        query.setParameter("username",username);
        query.executeUpdate();

        tx.commit();

        return "Deleted Succesfuly";


    }
    public User getUser(String username){
        Session session =factory.openSession();
        return session.get(User.class,username);

    }

}
