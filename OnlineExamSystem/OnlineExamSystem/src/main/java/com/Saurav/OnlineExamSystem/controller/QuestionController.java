package com.Saurav.OnlineExamSystem.controller;

import com.Saurav.OnlineExamSystem.entity.Answer;
import com.Saurav.OnlineExamSystem.entity.Question;
import com.Saurav.OnlineExamSystem.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
public class QuestionController {

    //Boolean bool =LoginController.validate();
    @Autowired
    QuestionService questionService;
    @Autowired
    SessionFactory factory;
    @GetMapping("getFirstQuestion/{subject}")
    public Question getFirstQuestion(@PathVariable String subject){


        List<Question> list =questionService.getAllQuestion(subject);

        HttpSession httpSession =LoginController.httpSession;
        httpSession.setAttribute("AllQuestion",list);
        Question question =list.get(0);

        return question ;

    }
    @GetMapping("nextQuestion")
    public Question nextQuestion(){

        HttpSession httpSession =LoginController.httpSession;
        List<Question> list =(List<Question>)httpSession.getAttribute("AllQuestion");

        int index =(int)httpSession.getAttribute("questionIndex");
        System.out.println(index);

        if(index<list.size()){

            int newIndex =index+1;
            httpSession.setAttribute("questionIndex",newIndex);

            return list.get(newIndex);
        }
        return list.get(list.size()-1);

    }
    @GetMapping("previousQuestion")
    public Question previousQuestion(){

        HttpSession httpSession =LoginController.httpSession;
        List<Question> list =(List<Question>)httpSession.getAttribute("AllQuestion");

        int index =(int)httpSession.getAttribute("questionIndex");

        if(index>0){

            int newIndex =index-1;
            httpSession.setAttribute("questionIndex",newIndex);

            return list.get(newIndex);
        }
        return list.get(0);

    }
    @PostMapping("saveAnswer")
    public HashMap<Integer,Answer> saveAnswer(@RequestBody Answer answer){

        HttpSession httpSession =LoginController.httpSession;

        HashMap<Integer,Answer> hashMap =(HashMap<Integer, Answer>) httpSession.getAttribute("submittedDetails");
          hashMap.put(answer.qno,answer);
        return hashMap;



    }
    @PostMapping("calculateScore")
    public int calculateScore(){

        Session session =factory.openSession();
        HttpSession httpSession =LoginController.httpSession;
        HashMap<Integer,Answer> hashMap =(HashMap<Integer, Answer>) httpSession.getAttribute("submittedDetails");

        Collection<Answer> answers =hashMap.values();

        for(Answer answer:answers){

            if(answer.selectedAnswer.equals(answer.getCorrectAnswer())){

                httpSession.setAttribute("score",(int)httpSession.getAttribute("score")+1);
            }

        }
        return (int)httpSession.getAttribute("score");

    }



}
