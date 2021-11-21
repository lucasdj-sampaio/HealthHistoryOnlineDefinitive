package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthhistoryonline.dao.UserDao;
import br.com.healthhistoryonline.model.Credential;
import br.com.healthhistoryonline.model.Phone;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;

@WebServlet("/SignUp")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
    
	 public void init() {
	        userDao = new UserDao();
	    }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException {
		 		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
 		try	{			
 			if (request.getParameter("senha").equals(request.getParameter("confirmarSenha"))) {
 				
	 			User user = new User(request.getParameter("nome"), request.getParameter("sobrenome")
	 				, request.getParameter("sexo").charAt(0), Long.parseLong(request.getParameter("cpf"))
	 				, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("datanasc")));
	 			
	 			user.setCredential(new Credential(request.getParameter("username")
	 					, request.getParameter("email")
	 					, request.getParameter("senha")));
	 			
	 			user.setPhone(new Phone(Integer.parseInt(request.getParameter("ddi"))
	 					, Integer.parseInt(request.getParameter("ddd"))
	 					, Integer.parseInt(request.getParameter("telefone"))));
	 			
	 			Pair<Boolean, String> userResponse = userDao.insertUser(user);
	 			
	 			if(!userResponse.getFirst()) {
		    		request.setAttribute("message", new Pair<String, String>("E", userResponse.getSecond()));
		    		
		 			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		 	    	rd.forward(request, response);
		    	}
	 			else {
	 				session.setAttribute("user"
	 						, userDao.getUser(user.getCredential().getUserName()).getSecond());
	 				
	 	 			RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
	 	 	    	rd.forward(request, response);
	 			}
 			}
 			else {
 				request.setAttribute("message", new Pair<String, String>("E", "As senhas informadas não coincidem!"));
	    		
	 			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	 	    	rd.forward(request, response);
 			}
 		}
 		catch (Exception ex){
 			ex.printStackTrace();
 		} 		
	 }
}