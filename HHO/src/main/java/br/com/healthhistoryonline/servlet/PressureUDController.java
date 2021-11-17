package br.com.healthhistoryonline.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.healthhistoryonline.dao.PressureDao;
import br.com.healthhistoryonline.model.Pressure;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;


@WebServlet(description = "A Servlet that updates/deletes the Pressure Method", urlPatterns = {"/PressureUDController"})
public class PressureUDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PressureDao pressureDao;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			User sessionUser = (User)session.getAttribute("usuario");
			
			Pressure pressure = new Pressure(Integer.parseInt("Pressão Sistólica")
					, Integer.parseInt("Pressão Diastólica")
					, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			
			Pair<Boolean, String> deleteResponse = pressureDao.deletePressure(pressure, null);
			
			if (!deleteResponse.getFirst()) {
				request.setAttribute("mensagem",  new Pair<String, String>("E", deleteResponse.getSecond()));
			}
			
			request.setAttribute("mensagem",  new Pair<String, String>("S", deleteResponse.getSecond()));
			RequestDispatcher rd = request.getRequestDispatcher("Pressure?user="+sessionUser.getCredential().getUserName());
			rd.forward(request, response);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			User sessionUser = (User)session.getAttribute("usuario");
			
			Pressure pressure = new Pressure(Integer.parseInt("Pressão Sistólica")
					, Integer.parseInt("Pressão Diastólica")
					, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			
			Pair<Boolean, String> updateResponse = pressureDao.updatePressure(pressure);
			
			if (!updateResponse.getFirst()) {
				request.setAttribute("mensagem",  new Pair<String, String>("E", updateResponse.getSecond()));
			}
			
			request.setAttribute("mensagem",  new Pair<String, String>("S", updateResponse.getSecond()));
			RequestDispatcher rd = request.getRequestDispatcher("Pressure?user="+sessionUser.getCredential().getUserName());
			rd.forward(request, response);
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
