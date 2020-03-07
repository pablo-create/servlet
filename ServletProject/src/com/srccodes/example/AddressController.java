package com.srccodes.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.AddressService;

/**
 * Servlet implementation class AddressController
 */
@WebServlet("/address/api/*")
public class AddressController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressController() {
        super();
    }
    private AddressService addressService = new AddressService();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addressService.select(request.getRequestURL(),response.getWriter(),response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 addressService.insert(response.getWriter(),request.getReader(),response);
	}
	

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 addressService.update(request.getRequestURL(),response.getWriter(),request.getReader());
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 addressService.delete(request.getRequestURL(),response.getWriter());
	}

}
