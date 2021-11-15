package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.healthhistoryonline.dao.UserDao;
import br.com.healthhistoryonline.model.User;
import br.com.healthhistoryonline.sysmodel.Pair;

@WebServlet(description = "A Servlet how control the login method", urlPatterns = { "/SignIn" })
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession(true);
    	
    	User user = (User)session.getAttribute("usuario");
		
    	if (user.getCredential().getUserName() == null)
    	{
			UserDao access = new UserDao();
			
			String userName = request.getParameter("usuario").toString();
			
			Pair<Boolean, String> hasUser = access.validLogin(userName
					, request.getParameter("senha").toString());
			
			if (!hasUser.getFirst()) {		
		    	request.setAttribute("message", hasUser.getSecond());
				
		    	RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		    	rd.forward(request, response);
			}
			
			user = access.getUser(userName).getSecond();
			session.setAttribute("user", user);		
    	}
    		
		RequestDispatcher rd = request.getRequestDispatcher("DASH?user="+user.getCredential().getUserName());
    	rd.forward(request, response);
	}
}