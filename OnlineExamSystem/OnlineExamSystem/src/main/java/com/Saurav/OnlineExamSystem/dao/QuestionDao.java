package com.Saurav.OnlineExamSystem.dao;

import com.Saurav.OnlineExamSystem.controller.LoginController;
import com.Saurav.OnlineExamSystem.entity.Question;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Repository
public class QuestionDao {

    // These are Question Management dao
    @Autowired
    SessionFactory factory;

    public boolean addQuestion(Question question)
    {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        session.persist(question);

        tx.commit();

        return true;

    }

    public List<Question> getAllQuestion(String subject){
        Session session=factory.openSession();
        Transaction tx =session.beginTransaction();
        Query<Question> query =session.createQuery("from Question where subject=:subject", Question.class);
        query.setParameter("subject",subject);
        return query.list();
    }




    public boolean updateQuestion(@RequestBody Question question)
    {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(question);

        tx.commit();

        return true;

    }

    // @PathVariable indicate spring to copy path variable's value to method parameter


    public Question viewQuestion(Integer qno,String subject)
    {
        Session session = factory.openSession();

        Query<Question> query=session.createQuery("from Question where qno=:qno and subject=:subject");
        query.setParameter("qno",qno);
        query.setParameter("subject",subject);

        Question question=query.uniqueResult();

        return question;
    }



    public boolean deleteQuestion(Integer qno,String subject)
    {
        Session session = factory.openSession();

        Query<Question> query=session.createQuery("from Question where qno=:qno and subject=:subject");
        query.setParameter("qno",qno);
        query.setParameter("subject",subject);

        Question question=query.uniqueResult();

        Transaction tx=session.beginTransaction();

        session.remove(question);

        tx.commit();

        return true;

    }

}
