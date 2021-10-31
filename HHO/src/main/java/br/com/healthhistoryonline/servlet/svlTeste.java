package br.com.healthhistoryonline.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/exemplo")
public class svlTeste extends HttpServlet {

	  public void init(ServletConfig config) throws ServletException {
		  
	  }
	  public void destroy() {
		  
	  }
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		  
	  }
	  public void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws ServletException, IOException {
	  }
	  
	  public void teste(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		  //Pega headers
		  Enumeration<String> h = request.getHeaderNames();
		  while (h.hasMoreElements()) {
		  	System.out.println("Elemento :" + h.nextElement());
		  }
		  //----------------------------------------------------
		  
		  //Pega dados do navegador
		  System.out.println(request.getHeader("user-agent"));
		  //----------------------------------------------------
		  
		  //Get Set
		  request.setAttribute("KEY","VALUE");
		  System.out.println("Atributo 1 :" + request.getAttribute("KEY"));
		  //----------------------------------------------------
		  
		  //Get cookie
		  Cookie[] cookie = request.getCookies();
		  
		  for(Cookie c : cookie){
			  System.out.println("Nome do Cookie :" + c.getName());
			  System.out.println("Nome do Cookie :" + c.getValue());
		  //----------------------------------------------------
		  }
		  
		  //Controle de sessão
		  HttpSession sessao = request.getSession();
		  //----------------------------------------------------
		  
		  //Set cookie
		  Cookie cookie1 = new Cookie("cookieTeste1", "Alexandre|Carlos" );
		  response.addCookie(cookie1);
		  //----------------------------------------------------
		  
		  //Set tipo de envio
		  response.setContentType("text/html");
		  //----------------------------------------------------
		  
		  //Get reponse
		  response.setContentType("text/html");
		  PrintWriter out = response.getWriter();
		  out.println("<html>");
		  out.println("<body>");
		  out.println("Exemplo Servlet");
		  out.println("</body>");
		  out.println("</html>");
		  //----------------------------------------------------
		  	  
		  String teste = request.getParameter("login");
		  
		  response.sendRedirect("outra pag ou region");
		  
		  //chamar oo jsp passando o response
		  request.setAttribute("KEY", "VALUE");
		  request.getRequestDispatcher("resposta.jsp").forward(request, response);
	  }
	  
	  
	  /*<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	  <!DOCTYPE html>
	  <html>
	  <head>
	  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	     <title>Resposta</title>
	  </head>
	  <body>
	  	<h1>Resultado da Operação</h1>
	  	<p> ${ KEY } </p> 
	  </body>
	  </html>*/
}