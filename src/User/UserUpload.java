package User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Bean.Login_Bean;
import Implementations.User_Implementations;
import Interfaces.User;

/**
 * Servlet implementation class UserUpload
 */


@WebServlet("/UserUpload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*20,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class UserUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "D:\\Cloud\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("Email");
		
	
		double bytes=0;
		  String fileName="";
		  String appPath = request.getServletContext().getRealPath("");
	        // constructs path of the directory to save uploaded file
	        String savePath = SAVE_DIR+username;
	         System.out.println(savePath);
	        // creates the save directory if it does not exists
	        File fileSaveDir = new File(savePath);
	       
	        if (!fileSaveDir.exists()) {
	            fileSaveDir.mkdir();
	        }
	         
	        for (Part part : request.getParts()) {
	            bytes=part.getSize();
	            fileName = extractFileName(part);
	            // refines the fileName in case it is an absolute path
	            fileName = new File(fileName).getName();
	            //bytes =  new File(fileName).length();
	             System.out.println(fileName);
	            
	            part.write(savePath + File.separator + fileName);
	        }
	    /*    request.setAttribute("message", "Upload has been done successfully!");
	        getServletContext().getRequestDispatcher("/message.jsp").forward(
	                request, response);
		
				*/
		
		String extension="";
	    try {
	    	extension=fileName.substring(fileName.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	    	extension="";
	    }
		Login_Bean reg=new Login_Bean();
		//docreg.setid(id);
		reg.setfilename(fileName);
		reg.setfilepath(savePath);
		int siz=(int) (bytes/1024);
		reg.setfilesize(String.valueOf(siz));
		reg.setfiletype(extension);
		reg.setemail(username);
		reg.setrab_chks(0);
		reg.setrab_dup(0);
		reg.setram_chks(0);
		reg.setra_dup(0);
		User i=new User_Implementations();
	    int status=i.fileupload(reg);
	    
	    if(status==1)
	    {
	    	response.sendRedirect("Home.jsp");
	    }
	    else
	    {
	        response.sendRedirect("error.jsp");
	    }
	}
	 private String extractFileName(Part part) {
		    
	        String contentDisp = part.getHeader("content-disposition");
	        String[] items = contentDisp.split(";");
	        for (String s : items) {
	            if (s.trim().startsWith("filename")) {
	                return s.substring(s.indexOf("=") + 2, s.length()-1);
	            }
	        }
	        return "";
	    }

}
