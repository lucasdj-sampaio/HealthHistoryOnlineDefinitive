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

import br.com.healthhistoryonline.dao.ExerciseDao;
import br.com.healthhistoryonline.dao.MeasureDao;
import br.com.healthhistoryonline.model.Exercise;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;
import br.com.healthhistoryonline.sysmodel.Weight;

@WebServlet(description = "A Servlet that controls the Update/Delete methods", urlPatterns = { "/AlterExercise" })
public class ExerciseUDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExerciseDao exerciseDao;
	
	@Override
	public void init() {
		exerciseDao = new ExerciseDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			
			User sessionUser = (User)session.getAttribute("usuario");
			
			Exercise exer = new Exercise(Integer.parseInt("calorias"), Integer.parseInt("bpm")
					, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			exer.setActivityCode(Integer.parseInt(request.getParameter("id")));
			
			Pair<Boolean, String> deleteResponse = exerciseDao.deleteExercise(exer);
			
			if (!deleteResponse.getFirst()) {
				request.setAttribute("message", new Pair<String, String>("E", deleteResponse.getSecond()));
			}
			
			request.setAttribute("message", new Pair<String, String>("S", deleteResponse.getSecond()));
			
			RequestDispatcher rd = request.getRequestDispatcher("Exercise?user="+sessionUser.getCredential().getUserName());
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
			
			Exercise exer = new Exercise(Integer.parseInt("calorias"), Integer.parseInt("bpm")
					, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			exer.setActivityCode(Integer.parseInt(request.getParameter("id")));
			exer.getInclusionDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			
			Pair<Boolean, String> updateResponse = exerciseDao.updateExercise(exer);
			
			if (!updateResponse.getFirst()) {
				request.setAttribute("message", new Pair<String, String>("E", updateResponse.getSecond()));
			}
			
			request.setAttribute("message", new Pair<String, String>("S", updateResponse.getSecond()));
			
			RequestDispatcher rd = request.getRequestDispatcher("Exercise?user="+sessionUser.getCredential().getUserName());
	    	rd.forward(request, response);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}