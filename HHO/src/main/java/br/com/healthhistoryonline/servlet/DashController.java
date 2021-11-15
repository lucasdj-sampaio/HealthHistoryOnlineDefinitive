package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.healthhistoryonline.dao.MeasureDao;
import br.com.healthhistoryonline.dao.PressureDao;
import br.com.healthhistoryonline.dao.UserDao;

@WebServlet(description = "A Servlet how control the home methods", urlPatterns = { "/Dash" })
public class DashController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private MeasureDao measureDao;
	private PressureDao pressureDao;
	
	@Override
	public void init() {
		userDao = new UserDao();
		measureDao = new MeasureDao();
		pressureDao = new PressureDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			
			String userName = request.getParameter("user").toString();
			
			session.removeAttribute("usuario");
			session.setAttribute("usuario", userDao.getUser(userName).getSecond());
			
			request.setAttribute("pressao", pressureDao.getAll(userName).getSecond());
			request.setAttribute("medida", measureDao.getMeasure(userName));
			
			RequestDispatcher rd = request.getRequestDispatcher("dash.jsp");
	    	rd.forward(request, response);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
}