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
import br.com.healthhistoryonline.dao.MeasureDao;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;
import br.com.healthhistoryonline.sysmodel.Weight;

@WebServlet(description = "A Servlet how control the measure methods", urlPatterns = { "/Measures" })
public class MeasureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeasureDao measureDao;
	
	@Override
	public void init() {
		measureDao = new MeasureDao();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			
			String userName = request.getParameter("user").toString();
			
			request.setAttribute("pesos", measureDao.getAllWeight(userName));
			
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
			
			Weight wei = new Weight(Float.parseFloat(request.getParameter("peso")));
			wei.setInclusionDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			
			Pair<Boolean, String> insertResponse = measureDao.insertMeasure(wei, sessionUser.getCredential().getUserName());
			
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