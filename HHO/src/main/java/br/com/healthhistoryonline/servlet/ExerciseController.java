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
import br.com.healthhistoryonline.model.Exercise;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;

@WebServlet(description = "A Servlet that controls the Exercise methods", urlPatterns = { "/Exercise" })
public class ExerciseController extends HttpServlet {
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
			
			String userName = request.getParameter("user").toString();
			
			request.setAttribute("exercise", exerciseDao.getAll(userName));
			
			RequestDispatcher rd = request.getRequestDispatcher("?????????.jsp");
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
			
			Exercise exercise = new Exercise(Integer.parseInt("calorias"), Integer.parseInt("bpm")
						, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
						
			Pair<Boolean, String> insertResponse = exerciseDao.insertExercise(exercise, sessionUser.getCredential().getUserName());
			
			if (!insertResponse.getFirst()) {
				request.setAttribute("message", new Pair<String, String>("E", insertResponse.getSecond()));
			}
			
			request.setAttribute("message", new Pair<String, String>("S", insertResponse.getSecond()));
			
			RequestDispatcher rd = request.getRequestDispatcher("???????????.jsp");
	    	rd.forward(request, response);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}