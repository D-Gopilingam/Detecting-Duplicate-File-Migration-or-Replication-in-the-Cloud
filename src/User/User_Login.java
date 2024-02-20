package User;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Login_Bean;
import Implementations.User_Implementations;
import Interfaces.User;


/**
 * Servlet implementation class Doclogin
 */
@WebServlet("/User_Login")
public class User_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String id=request.getParameter("lemail");
		String password=request.getParameter("lpwd");
		
		if(id.equals("Admin@gmail.com")&&password.equals("Admin"))
		{
			RequestDispatcher dispatcher=request.getRequestDispatcher("Admin.jsp");
			dispatcher.forward(request, response);
			
		}
		else
		{
		Login_Bean log=new Login_Bean();
		
		log.setemail(id);
		log.setpassword(password);
		
		User I=new User_Implementations();
		
		List list=I.userlogin(log);
		HttpSession session=request.getSession();
		session.setAttribute("id", id);
		if(!list.isEmpty()){
			
			if(list!=null){
				
				
				id=list.get(0).toString().trim();
				password=list.get(1).toString().trim();
			
				session.setAttribute("Email",id);
				//session.setAttribute("key_pass",password);
			}
			
			if(id!=null){
			//	String drkey=I.getDrname(id);
			//	session.setAttribute("drkey",drkey);
				RequestDispatcher dispatcher=request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
				
			}
		
			else
			{
				RequestDispatcher dispatcher=request.getRequestDispatcher("error.jsp");
				dispatcher.forward(request, response);
				
			}
		}
		
		else{
			RequestDispatcher dispatcher=request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
		}
	}

}
