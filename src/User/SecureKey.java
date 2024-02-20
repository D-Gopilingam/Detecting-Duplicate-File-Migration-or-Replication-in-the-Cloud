package User;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class SecureKey
 */
@WebServlet("/SecureKey")
public class SecureKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecureKey() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		HttpSession session=request.getSession();
		
		String username=(String) session.getAttribute("Email");
		String key=request.getParameter("keyname");
		String fname=request.getParameter("fname");
		Login_Bean log=new Login_Bean();
		log.setemail(username);
		User I=new User_Implementations();
		String pass=I.getkey(log);
		
		if(key.equals(pass))
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String filename = fname; 
			String filepath = "D:\\Cloud\\"+username+"\\"; 
			response.setContentType("APPLICATION/OCTET-STREAM"); 
			response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\""); 

			java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filepath + filename);

			int i; 
			while ((i=fileInputStream.read()) != -1) {
			  out.write(i); 

			} 
			
			fileInputStream.close(); 
			out.close(); 

			//RequestDispatcher dispatcher=request.getRequestDispatcher("Home.jsp");
			//dispatcher.forward(request, response);
			
		}
		else
		{
			System.out.println("Not Ok");
			RequestDispatcher dispatcher=request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
