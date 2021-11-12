package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.healthhistoryonline.dao.UserDao;
import br.com.healthhistoryonline.sysmodel.Pair;

@WebServlet(description = "A Servlet how control the login method", urlPatterns = { "/AcessSytem" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao validLogin = new UserDao();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getAttribute("userName").toString();
		String password = request.getAttribute("password").toString();
		
		Pair<Boolean, String> hasUser = validLogin.validLogin(userName, password);
		
		if (hasUser.getFirst()) {
			response.sendRedirect("index.jsp");
		}
		else {
			//Sobe modal com erro
		}
	}
}