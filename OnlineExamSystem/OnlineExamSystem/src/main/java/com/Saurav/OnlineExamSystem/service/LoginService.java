package com.Saurav.OnlineExamSystem.service;

import com.Saurav.OnlineExamSystem.dao.LoginDao;
import com.Saurav.OnlineExamSystem.entity.Answer;
import com.Saurav.OnlineExamSystem.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@Service
public class LoginService {
    @Autowired
    LoginDao loginDao;

    @Autowired
    static HttpSession httpSession;

    public User saveUser(User user){

        return loginDao.saveUser(user);
    }

    public String updateUser(User user){

        loginDao.updateUser(user);
        return "Updated Sucessfuly";
    }

    public String DeleteUser(String username){

        loginDao.DeleteUser(username);
        return "Deleted Succesfuly";
    }

    public User getUser(String username){

        return loginDao.getUser(username);

    }


    public  boolean validate(User userFromBrowser, HttpServletRequest httpServletRequest){


        User userfromdb =loginDao.getUser(userFromBrowser.getUsername());

        boolean answer =userfromdb.getPassword().equals(userFromBrowser.getPassword());
        if(userfromdb == null) {
            return false;
        }
        HttpSession httpSession = httpServletRequest.getSession();

        if(answer){

            httpSession.setAttribute("score",0);
            httpSession.setAttribute("questionIndex",0);
            HashMap<Integer, Answer> hashMap =new HashMap<>();
            httpSession.setAttribute("submittedDetails",hashMap);
        }
        return answer;


    }
}
