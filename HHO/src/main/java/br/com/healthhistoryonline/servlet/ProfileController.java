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
import br.com.healthhistoryonline.dao.UserDao;
import br.com.healthhistoryonline.model.Measure;
import br.com.healthhistoryonline.model.Phone;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;

@WebServlet(description = "A Servlet how control the profile methods", urlPatterns = { "/Profile" })
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private MeasureDao measureDao;
	
	@Override
	public void init() {
		userDao = new UserDao();
		measureDao = new MeasureDao();
	}
	
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
		
		User sessionUser = (User)session.getAttribute("usuario");
		Measure sessionMeasure = (Measure)request.getAttribute("medida");
   
		try {
			sessionUser.setName(request.getParameter("nome").trim().length() > 0 
					? request.getParameter("nome").trim() 
					: sessionUser.getName());
			
			sessionUser.setLastName(request.getParameter("sobrenome").trim().length() > 0 
					? request.getParameter("sobrenome").trim() 
					: sessionUser.getLastName());
			
			sessionUser.setCpf(request.getParameter("cpf").trim().length() > 0 
					? Long.parseLong(request.getParameter("cpf"))
					: sessionUser.getCpf());
			
			sessionUser.setBirthDate(request.getParameter("aniversario").trim().length() > 0
					? new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("aniversario"))
					: sessionUser.getBirthDate());
			
			if (request.getParameter("telefone").trim().length() > 0 
					&& request.getParameter("ddd").trim().length() > 0
					&& request.getParameter("ddi").trim().length() > 0) {
				
				Phone phone = new Phone(Integer.parseInt(request.getParameter("ddi"))
						,Integer.parseInt(request.getParameter("ddd"))
						, Integer.parseInt(request.getParameter("telefone")));
				
				sessionUser.setPhone(phone);
			}
						
			sessionUser.setGender(request.getParameter("sM").equals("on") ? "M" : "F");
			
	    	sessionUser.getCredential().setMailAddress(request.getParameter("email").trim().length() > 0 
					? request.getParameter("email").trim() 
					: sessionUser.getCredential().getMailAddress());
    	    		
	    	if (request.getParameter("novaSenha").trim().length() > 0 
	    			&& request.getParameter("novaSenha") == request.getParameter("confirmarSenha")
	    			&& request.getParameter("senha") == sessionUser.getCredential().getPassword()) {
	    	
	    		sessionUser.getCredential().setPassword("novaSenha");
	    	}
	    	else if (request.getParameter("senha") == request.getParameter("novaSenha")) {
	    		request.setAttribute("message", new Pair<String, String>("W","Senha não alterada pois são iguais!"));
	    	}
	    	else {
	    		request.setAttribute("message", new Pair<String, String>("W","Senha de confirmação inválida ou vazia"));
	    	}
	    	
	    	sessionMeasure.getHeight().setHeight(request.getParameter("altura").trim().length() > 0 
	    			? Float.parseFloat(request.getParameter("altura").trim().replace(",", "."))   
	    			: sessionMeasure.getHeight().getHeight());
	   
	    	sessionMeasure.getWeight().setWeight(request.getParameter("peso").trim().length() > 0 
	    			? Float.parseFloat(request.getParameter("peso").trim().replace(",", "."))   
	    			: sessionMeasure.getWeight().getWeight());
	    	
	    	Pair<Boolean, String> incluseResponse = includeUser(sessionUser, sessionMeasure);
	    	
	    	if(!incluseResponse.getFirst()) {
	    		request.setAttribute("message", new Pair<String, String>("E", incluseResponse.getSecond()));
	    	}
	    	else {
	    		request.setAttribute("message", new Pair<String, String>("S", incluseResponse.getSecond()));
	    	}
    	}catch (Exception ex){
    		ex.printStackTrace();
    	}
    	
    	redirect(request, response, sessionUser.getCredential().getUserName());
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response, String userName) throws ServletException, IOException {
    	
		HttpSession session = request.getSession(true);
		
		session.removeAttribute("usuario");
		session.setAttribute("usuario", userDao.getUser(userName).getSecond());
		
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
    	
    	Pair<Boolean, String> updateH = measure.getHeight().getHeightCode() < 1 
    					? measureDao.insertMeasure(measure.getHeight(), user.getCredential().getUserName()) 
    					: measureDao.updateMeasure(measure.getHeight());
    	
    	if (!updateH.getFirst()) {
    		return new Pair<Boolean, String>(false, updateH.getSecond());
    	}
    	
    	Pair<Boolean, String> updateW = measure.getWeight().getWeightCode() < 1 
    						?  measureDao.insertMeasure(measure.getWeight(), user.getCredential().getUserName())
    						:  measureDao.updateMeasure(measure.getWeight());
    	
    	if (!updateW.getFirst()) {
    		return new Pair<Boolean, String>(false, updateW.getSecond());
    	}
    	
    	return new Pair<Boolean, String>(true, "Dados atualizados!");
	}
}