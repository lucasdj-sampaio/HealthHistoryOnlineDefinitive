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
import br.com.healthhistoryonline.sysmodel.Pair;

import br.com.healthhistoryonline.model.Pressure;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.dao.PressureDao;

@WebServlet(description = "A Servlet that controls the Pressure", urlPatterns = { "/Pressure" })
public class PressureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PressureDao pressureDao;
	
	@Override
	public void init() {
		pressureDao = new PressureDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String userName = request.getParameter("user").toString();
			
			request.setAttribute("Pressao", pressureDao.getAll(userName));
			
			RequestDispatcher rd = request.getRequestDispatcher("DEFINIR.jsp");
			rd.forward(request, response);
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession(true);			
			User sessionUser = (User)session.getAttribute("usuario");
			
			Pressure pressure = new Pressure(Integer.parseInt("Pressão Sistólica")
					, Integer.parseInt("Pressão Diastólica")
					, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			
			Pair<Boolean, String> insertResponse = pressureDao.insertPressure(pressure, sessionUser.getCredential().getUserName());
			
			if (!insertResponse.getFirst()) {
				request.setAttribute("mensagem", new Pair<String, String>("E", insertResponse.getSecond()));
			}
			
			request.setAttribute("message", new Pair<String, String>("S", insertResponse.getSecond()));
			
			RequestDispatcher rd = request.getRequestDispatcher("DEFINIR.jsp");
			rd.forward(request, response);
			
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}