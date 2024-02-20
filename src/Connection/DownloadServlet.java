package Connection;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DownloadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("Email");
		String name  = request.getParameter("name");
		String key  = request.getParameter("myField");
		System.out.println(key);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		 String filename = name; 
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
		
	}

}
