package com.Saurav.OnlineExamSystem.controller;

import com.Saurav.OnlineExamSystem.dao.LoginDao;
import com.Saurav.OnlineExamSystem.entity.Answer;
import com.Saurav.OnlineExamSystem.entity.User;
import com.Saurav.OnlineExamSystem.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    public static HttpSession httpSession;
    @PostMapping("saveUser")
    public User saveUser(@RequestBody User user){

       return loginService.saveUser(user);
    }
    @PostMapping("updateUser")
    public String updateUser(@RequestBody User user){

        loginService.updateUser(user);
        return "Updated Sucessfuly";
    }
    @DeleteMapping("DeleteUser/{username}")
    public String DeleteUser(@PathVariable String username){

        loginService.DeleteUser(username);
       return "Deleted Succesfuly";
    }


    @PostMapping("validate")
    public  boolean validate(@RequestBody User userFromBrowser, HttpServletRequest httpServletRequest){


        User userfromdb =loginService.getUser(userFromBrowser.getUsername());

        boolean answer =userfromdb.getPassword().equals(userFromBrowser.getPassword());
        if(userfromdb == null) {
            return false;
        }
        httpSession = httpServletRequest.getSession();

        if(answer){

            httpSession.setAttribute("score",0);
            httpSession.setAttribute("questionIndex",0);
            HashMap<Integer, Answer> hashMap =new HashMap<>();
            httpSession.setAttribute("submittedDetails",hashMap);
        }
        return answer;


    }

}
