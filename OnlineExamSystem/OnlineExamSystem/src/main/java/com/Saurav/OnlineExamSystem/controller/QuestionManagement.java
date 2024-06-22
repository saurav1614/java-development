package com.Saurav.OnlineExamSystem.controller;

import com.Saurav.OnlineExamSystem.entity.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
public class QuestionManagement {
    @Autowired
    SessionFactory factory;
    @PostMapping("addQuestion")
    public boolean addQuestion(@RequestBody Question question)
    {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        session.persist(question);

        tx.commit();

        return true;

    }
    @GetMapping("getAllQuestion")
    public List<Question> getAllQuestion(){
        Session session=factory.openSession();
        Transaction tx =session.beginTransaction();
        Query<Question> query =session.createQuery("from Question", Question.class);
        return query.list();
    }



    @PutMapping("updateQuestion")
    public boolean updateQuestion(@RequestBody Question question)
    {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        session.merge(question);

        tx.commit();

        return true;

    }

    // @PathVariable indicate spring to copy path variable's value to method parameter

    @GetMapping("viewQuestion/{qno}/{subject}")
    public Question viewQuestion(@PathVariable Integer qno, @PathVariable String subject)
    {
        Session session = factory.openSession();

        Query<Question> query=session.createQuery("from Question where qno=:qno and subject=:subject");
        query.setParameter("qno",qno);
        query.setParameter("subject",subject);

        Question question=query.uniqueResult();

        return question;
    }

    @CrossOrigin(origins ="http://localhost:4200")
    @DeleteMapping("deleteQuestion/{qno}/{subject}")
    public boolean deleteQuestion(@PathVariable Integer qno, @PathVariable String subject)
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
