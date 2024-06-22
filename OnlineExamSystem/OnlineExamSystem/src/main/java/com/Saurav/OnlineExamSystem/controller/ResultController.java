package com.Saurav.OnlineExamSystem.controller;

import com.Saurav.OnlineExamSystem.entity.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ResultController {
    @Autowired
    SessionFactory factory;
    @PostMapping("saveResult")
    public boolean saveResult(@RequestBody Result result)
    {
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        session.persist(result);

        tx.commit();

        return true;

    }
    @GetMapping("getResult/{subject}/{username}")
    public Result getResult(@PathVariable String subject, @PathVariable String username){

        Session session=factory.openSession();


        Query<Result> query =session.createQuery("from Result where subject=:subject and username=:username", Result.class);
        query.setParameter("subject",subject);
        query.setParameter("username",username);

        return query.uniqueResult();

    }
    @RequestMapping("getRecordsCounts/{subject}")
    public int getRecordsCounts(@PathVariable String subject)
    {
        Session session=factory.openSession();

        Query query=session.createQuery("from Result where subject=:subject");

        query.setParameter("subject", subject);

        List<Result> list =query.list();

        return list.size();

    }
    @GetMapping("getResult2/{subject}/{pageno}")
    public List<Result> getResult2(String subject, int pageno){

        Session session =factory.openSession();
        Query<Long> query =session.createQuery("from Result count(subject) where subject=:subject",Long.class);
        query.setParameter("subject",subject);

        long noOfRecords =query.uniqueResult();
        int pageNumber =1;

        while(pageNumber*3<noOfRecords){

            pageNumber++;
        }
        int [] index =new int[pageNumber];

        for(int i=0,count =0;i<pageno;i++,count =count+3){

            index[i] =count;
        }
        Query<Result> query2 =session.createQuery("from Result where subject=:subject",Result.class);
        query2.setParameter("subject",subject);

        int startIndex =index[pageno-1];

        query2.setMaxResults(3);
        query2.setFirstResult(startIndex);
        List<Result> list =query2.list();

        return list;



    }


}
