package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import br.com.healthhistoryonline.sysmodel.SnackType;

@WebServlet(description = "A Servlet how control the meal methods", urlPatterns = { "/Meal" })
public class MealController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MealDao mealDao;
	
	@Override
	public void init() {
		mealDao = new MealDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			String userName = request.getParameter("user").toString();
			
			request.setAttribute("refeicoes", mealDao.getAll(userName));
			
			RequestDispatcher rd = request.getRequestDispatcher("???????????.jsp");
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
			
			Snack snack = new Snack();
			snack.setTypeFood(new SnackType(Integer.getInteger(request.getParameter("refeicao"))));
			snack.setCalories(Integer.getInteger(request.getParameter("caloria")));
			snack.setInclusionDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataRefeicao")));
			
			for (String alimento : Arrays.asList(request.getParameterValues("alimento"))) {
				snack.getMeal().add(new Meal(alimento));
			}
					
			Pair<Boolean, String> insertResponse = mealDao.insertMeal(snack, sessionUser.getCredential().getUserName());
			
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