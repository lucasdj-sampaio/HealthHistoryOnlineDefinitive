package br.com.healthhistoryonline.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.healthhistoryonline.dao.UserDao;
import br.com.healthhistoryonline.model.Credential;
import br.com.healthhistoryonline.model.Phone;
import br.com.healthhistoryonline.model.User;

@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
    
	 public void init() {
	        userDao = new UserDao();
	    }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 		
	 		try	{
	 			User user = new User(request.getParameter("nome"), request.getParameter("sobrenome")
	 				, request.getParameter("sexo").charAt(0), Long.parseLong(request.getParameter("cpf"))
	 				, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("datanasc")));
	 			
	 			user.setCredential(new Credential(request.getParameter("username")
	 					, request.getParameter("email")
	 					, request.getParameter("password")));
	 			
	 			user.setPhone(new Phone(Integer.parseInt(request.getParameter("ddi"))
	 					, Integer.parseInt(request.getParameter("ddd"))
	 					, Integer.parseInt(request.getParameter("number"))));
	 			userDao.insertUser(user);
	 			
	 		}
	 		catch (Exception ex){
	 			ex.printStackTrace();
	 		} 		
	 		
	 }
}
