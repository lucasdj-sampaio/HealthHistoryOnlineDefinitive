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

@WebServlet(description = "A Servlet how control the measure methods", urlPatterns = { "/AlterMeasures" })
public class MeasureUDController extends HttpServlet {
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
			HttpSession session = request.getSession(true);
			
			User sessionUser = (User)session.getAttribute("user");
			
			Weight wei = new Weight();
			wei.setWeightCode(Integer.parseInt(request.getParameter("id")));
			
			Pair<Boolean, String> deleteResponse = measureDao.deleteMeasure(wei);
			
			if (!deleteResponse.getFirst()) {
				request.setAttribute("message", new Pair<String, String>("E", deleteResponse.getSecond()));
			}
			else {
				request.setAttribute("message", new Pair<String, String>("S", deleteResponse.getSecond()));
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("Measures?user="+sessionUser.getCredential().getUserName());
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
			
			Weight wei = new Weight();
			wei.setWeight(Float.parseFloat(request.getParameter("peso")));
			wei.setWeightCode(Integer.parseInt(request.getParameter("id")));
			wei.setInclusionDate(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataInclusao")));
			
			Pair<Boolean, String> updateResponse = measureDao.updateMeasure(wei);
			
			if (!updateResponse.getFirst()) {
				request.setAttribute("message", new Pair<String, String>("E", updateResponse.getSecond()));
			}
			
			request.setAttribute("message", new Pair<String, String>("S", updateResponse.getSecond()));
			
			response.sendRedirect("Measures?user="+sessionUser.getCredential().getUserName());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}	
	}
}