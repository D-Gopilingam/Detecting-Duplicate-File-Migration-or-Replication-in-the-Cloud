package User;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
 * Servlet implementation class Docregister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String id="1";
		
		
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");
		for (int i = 0; i < 5; i++)
		{
		    sb.append(chars[rnd.nextInt(chars.length)]);
		}

		//System.out.println(sb.toString());
		
		
		
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String password =request.getParameter("pwd");
		
		int status=0;
		Login_Bean docreg=new Login_Bean();
		//docreg.setid(id);
		docreg.setfname(fname);
		docreg.setlname(lname);
		docreg.setemail(email);
		docreg.setpassword(password);
		docreg.setkey(sb.toString());

		
			
		User i=new User_Implementations();
	    status=i.register(docreg);
	    
	    System.out.println("stqtus  "+status);

	    if(status==1)
	    {
			HttpSession session=request.getSession();
			session.setAttribute("key_value",sb.toString());
		    System.out.println("stqtus  "+sb.toString());
			if(sb.toString()!=null)
			{			
			RequestDispatcher dispatcher=request.getRequestDispatcher("Keygen.jsp");
			dispatcher.forward(request, response);
			}
			else
			{
				response.sendRedirect("index.jsp");
				
			}
	    }
	    else
	    {
	        response.sendRedirect("doctorregister.jsp");
	    }
		
		
		
		
	}

}
