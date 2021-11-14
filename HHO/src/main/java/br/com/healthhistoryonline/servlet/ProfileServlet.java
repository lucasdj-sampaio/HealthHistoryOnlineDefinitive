package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.healthhistoryonline.dao.MeasureDao;
import br.com.healthhistoryonline.dao.UserDao;
import br.com.healthhistoryonline.model.Credential;
import br.com.healthhistoryonline.model.Measure;
import br.com.healthhistoryonline.model.Phone;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;

@WebServlet(description = "A Servlet how control the login method", urlPatterns = { "/Profile" })
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userName = request.getParameter("user").toString();
		
		redirect(request, response, userName);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
   
		try {
			User sessionUser = (User)session.getAttribute("usuario");
			Measure requestMeasure = (Measure)request.getAttribute("medida");
			
	    	Credential credential = new Credential(request.getParameter("usuario"), request.getParameter("email")
	    			, request.getParameter("senha"));
	    	
	    	String[] phoneData = request.getParameter("telefone").split(" ");
	    	
	    	Set<Phone> phone = new HashSet<Phone>();
	    	phone.add(new Phone (Integer.parseInt(phoneData[0])
	    			, Integer.parseInt(phoneData[1]), Integer.parseInt(phoneData[2])));
    	    		
	    	sessionUser = (request.getParameter("nome"), request.getParameter("sobrenome")
	    			, request.getParameter("sexo").charAt(0), Long.parseLong(request.getParameter("cpf"))
	    			, new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("aniversario")));
	    	
	    	user.setCredential(credential);
	    	user.setPhone(phone);
	    	
	    	Measure measure = new Measure(Float.parseFloat(request.getParameter("altura"))
	    			, Float.parseFloat(request.getParameter("peso")));
	    	
	    	Pair<Boolean, String> incluseResponse = includeUser(sessionUser, measure);
	    	
	    	if(!incluseResponse.getFirst()) {
	    		request.setAttribute("message", incluseResponse.getSecond());
	    	}
	    	
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
    	
    	redirect(request, response, request.getParameter("usuario"));
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response, String userName) throws ServletException, IOException {
    	UserDao userDao = new UserDao();
		MeasureDao measureDao = new MeasureDao();
		
		HttpSession session = request.getSession(true);
		
		session.removeAttribute("usuario");
		session.setAttribute("usuario", userDao.getUser(userName).getSecond());
		
		session.removeAttribute("medidas");
		request.setAttribute("medida", measureDao.getMeasure(userName));
		
		RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
    	rd.forward(request, response);
	}
	
	private Pair<Boolean, String> includeUser(User user, Measure measure){
    	UserDao userDao = new UserDao();
		MeasureDao measureDao = new MeasureDao();
		
		Pair<Boolean, String> updateCredential = userDao.updateUser(user.getCredential());
    	if (!updateCredential.getFirst()) {
    		return new Pair<Boolean, String>(false, updateCredential.getSecond());
    	}
    	
    	Pair<Boolean, String> updateUser = userDao.updateUser(user);
    	if (!updateUser.getFirst()) {
    		return new Pair<Boolean, String>(false, updateUser.getSecond());
    	}
    	
    	Pair<Boolean, String> updateW = measureDao.updateMeasure(measure.getHeight());
    	if (!updateW.getFirst()) {
    		return new Pair<Boolean, String>(false, updateW.getSecond());
    	}
    	
    	Pair<Boolean, String> updateH = measureDao.updateMeasure(measure.getHeight());
    	if (!updateH.getFirst()) {
    		return new Pair<Boolean, String>(false, updateH.getSecond());
    	}
    	
    	return new Pair<Boolean, String>(true, null);
	}
}