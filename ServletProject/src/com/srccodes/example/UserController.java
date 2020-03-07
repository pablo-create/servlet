package com.srccodes.example;

import java.io.*;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;

import Service.UserService;

@WebServlet("/user/api/*")

public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
    }

	private UserService userService = new UserService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		userService.select(request.getRequestURL(),response.getWriter(),response);
	}		

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	 
		 userService.insert(response.getWriter(),request.getReader(),response);
	 }
	 
	 protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 userService.update(request.getRequestURL(),response.getWriter(),request.getReader());
	 }
	 
	 protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 userService.delete(request.getRequestURL(),response.getWriter());
	 }	
}


