package com.hemanth.demo.controller;



import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hemanth.demo.object.LoginRequest;
import com.hemanth.demo.object.object;
import com.hemanth.demo.objectrepo.objectrepo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class controller {
	
	@Autowired
	private AuthenticationManager authenticationManager;

    @Autowired
    objectrepo repo;

//    @RequestMapping("/")
//    public String Home() {
//        return "Home.jsp";
//    }
    @RequestMapping("/home")
    public String Homes() {
        return "Home.jsp";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("put")
    public void put1(@RequestBody object obj) {
        try {
            repo.save(obj);
            System.out.println("the data was saved in data base.");
        } catch (Exception e) {
            System.err.println("The Error was : " + e.getMessage());
        }
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginRequest lg) {
    	
    	System.out.print(lg);
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(lg.getEmail(), lg.getPassword())
            );

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Retrieve UserDetails
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // You can perform additional actions here if needed
            
            // Return success message or any response you want
            return "Login successful";
        } catch (Exception e) {
            // Handle authentication failure
            return "Login failed: " + e.getMessage();
        }
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getdata")
    public object getdata( String email) {
    	try {
    	object obj = repo.findByEmail(email);
    	System.out.print(obj);
    	return obj;
    	}
    	catch(Exception e) {
    		System.out.print("Error retreving the data  :"+e.getMessage());
    		return null;
    	}
		
    }
    
//    @RequestMapping("/login")
//    public String loginpage() {
//    	System.out.println("this is the page which we are calling right now.");
//    	return "Login.jsp";
//    }
    
//    @RequestMapping("/logout-success")
//    public String logoutpage() {
//        return "Logout.jsp";
//    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate the user's session
        request.getSession().invalidate();
        return "Logout successful";
    }
}
