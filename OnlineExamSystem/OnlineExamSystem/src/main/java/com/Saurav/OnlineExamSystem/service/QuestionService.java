package com.Saurav.OnlineExamSystem.service;

import com.Saurav.OnlineExamSystem.controller.LoginController;
import com.Saurav.OnlineExamSystem.dao.QuestionDao;
import com.Saurav.OnlineExamSystem.entity.Question;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
public class QuestionService {

    //These are Question Management Service
    @Autowired
    QuestionDao questionDao;


    public boolean addQuestion(Question question)
    {
        return questionDao.addQuestion(question);
    }

    public List<Question> getAllQuestion(String subject){
       return questionDao.getAllQuestion(subject);
    }

    public boolean updateQuestion(Question question)
    {
        return questionDao.updateQuestion(question);

    }

    public Question viewQuestion(Integer qno,String subject)
    {
        return questionDao.viewQuestion(qno,subject);
    }

    public boolean deleteQuestion(Integer qno,String subject)
    {
        return questionDao.deleteQuestion(qno,subject);
    }
    //These are Question service

}
