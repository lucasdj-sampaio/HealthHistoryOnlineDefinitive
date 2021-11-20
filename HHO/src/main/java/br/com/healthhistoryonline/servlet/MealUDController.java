package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.healthhistoryonline.dao.MealDao;
import br.com.healthhistoryonline.model.Meal;
import br.com.healthhistoryonline.model.Snack;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;

@WebServlet(description = "A Servlet how control the meal methods", urlPatterns = { "/AlterMeal" })
public class MealUDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MealDao mealDao;
	
	@Override
	public void init() {
		mealDao = new MealDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pair<Boolean, String> deleteResponse = new Pair<Boolean, String>(false, "Falha no processo de exclusão");
		
		try {
			request.setCharacterEncoding("UTF-8");
			
			HttpSession session = request.getSession(true);			
			User sessionUser = (User)session.getAttribute("user");
			
			switch(Integer.parseInt(request.getParameter("opcao"))) {
				case 1:
					deleteResponse = mealDao.deleteMeal(Integer.parseInt(request.getParameter("id")));
				case 2:
					Snack snack = new Snack();
					snack.setSnackCode(Integer.parseInt(request.getParameter("id")));
					
					deleteResponse = mealDao.deleteMeal(snack);				
			}
			
			if (!deleteResponse.getFirst()) {
				request.setAttribute("message", new Pair<String, String>("E", deleteResponse.getSecond()));
			}
			
			request.setAttribute("message", new Pair<String, String>("S", deleteResponse.getSecond()));				

			RequestDispatcher rd = request.getRequestDispatcher("Meal?user="+sessionUser.getCredential().getUserName());
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
			User sessionUser = (User)session.getAttribute("user");
			
			Meal meal = new Meal(request.getParameter("alimento"));
			meal.setMealCode(Integer.parseInt(request.getParameter("id")));
			
			Pair<Boolean, String> updateResponse = mealDao.updateMeal(meal);
			
			if (!updateResponse.getFirst()) {
				request.setAttribute("message", new Pair<String, String>("E", updateResponse.getSecond()));
			}
			
			request.setAttribute("message", new Pair<String, String>("S", updateResponse.getSecond()));				

			RequestDispatcher rd = request.getRequestDispatcher("Meal?user="+sessionUser.getCredential().getUserName());
	    	rd.forward(request, response);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}