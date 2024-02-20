package Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.Login_Bean;
import Connection.Database;
import Interfaces.User;
public class User_Implementations implements User{

	Connection conn=Database.con();
	PreparedStatement statement;
	String query;
	public int status;
	@Override
	public int register(Login_Bean bean) {
		
			int status=0;
		
	//	System.out.println(bean.getEmail());
		try
		{
			query="insert into register (fname,lname,email,password,key_value) values(?,?,?,?,?);" ;
			statement=conn.prepareStatement(query);
			statement.setString(1,bean.getfname());
			statement.setString(2,bean.getlname());
			
			statement.setString(3,bean.getemail());
			statement.setString(4,bean.getpassword());
			statement.setString(5,bean.getkeyvalue());
			status=statement.executeUpdate();
			status=1;
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			status=0;
		}
	    return status;
		
	}
	@Override
	public List userlogin(Login_Bean log) {
		
		List list=new ArrayList();
				String email="";
				String password="";
				try
				{
					
						//System.out.println("user namein login"+log.getid());
						//System.out.println("user namein login"+log.getPassword());
						 //query="SELECT Email,password FROM docregister d where Email=? and password=?";
						 query="SELECT email,password FROM register where email=? and password=?";
						PreparedStatement statement = conn.prepareStatement(query);
						statement.setString(1,log.getemail());
						statement.setString(2,log.getpassword());
						ResultSet resultSet = statement.executeQuery();
						
						

						if(resultSet.next())
						{
							email=resultSet.getString(1).toString().trim();
							password=resultSet.getString(2).toString().trim();
							list.add(email);
							list.add(password);
							System.out.println(list);
							
						}
						
				}
						catch (Exception e)
						{
							e.printStackTrace();
							System.out.println("SqlMethodsImplementation.login()");
						}
						return list;
	}
	
	@Override
	public int fileupload(Login_Bean bean) {
		// TODO Auto-generated method stub
		
		int status=0;
		
		//	System.out.println(bean.getEmail());
			try
			{
				query="insert into file_upload (Filename,Path,Size,Filetype,Username,Rab_Chks,Rab_dup,Ram_Chks,Ram_Dup) values(?,?,?,?,?,?,?,?,?);" ;
				statement=conn.prepareStatement(query);
				statement.setString(1,bean.getfilename());
				statement.setString(2,bean.getfilepath());
				statement.setString(3,bean.getfilesize());
				statement.setString(4,bean.getfiletype());
				statement.setString(5,bean.getemail());
				statement.setInt(6,bean.getrabchks());
				statement.setInt(7,bean.getrabdup());
				statement.setInt(8,bean.getramchks());
				statement.setInt(9,bean.getramdup());
				status=statement.executeUpdate();
				status=1;
				
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				status=0;
			}
		    return status;
	}
	@Override
	public int fileupdateRab(Login_Bean bean) {
		// TODO Auto-generated method stub
		int status=0;
		try
		{
			query="update file_upload set Rab_Chks=?,Rab_Dup=? where FileName=? and Username=?;;" ;
			statement=conn.prepareStatement(query);
			statement.setInt(1,bean.getrabchks());
			statement.setInt(2,bean.getrabdup());
			statement.setString(3,bean.getfilename());
			statement.setString(4,bean.getemail());
			status=statement.executeUpdate();
			status=1;
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			status=0;
		}
	    return status;
		
	}
	@Override
	public int fileupdateRam(Login_Bean bean) {
		// TODO Auto-generated method stub
		int status=0;
		try
		{
			query="update file_upload set Ram_Chks=?,Ram_Dup=? where FileName=? and Username=?;" ;
			statement=conn.prepareStatement(query);
			statement.setInt(1,bean.getramchks());
			statement.setInt(2,bean.getramdup());
			statement.setString(3,bean.getfilename());
			statement.setString(4,bean.getemail());
			status=statement.executeUpdate();
			status=1;
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			status=0;
		}
	    return status;
	}
	@Override
	public String getkey(Login_Bean bean) {
		// TODO Auto-generated method stub
		String status=null;
		try
		{
			    query="SELECT key_value FROM register where email=?";
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1,bean.getemail());
				ResultSet resultSet = statement.executeQuery();
				
				if(resultSet.next())
				{
					status=resultSet.getString(1).toString().trim();
					System.out.println(status);
				}
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			status="no";
		}
	    return status;
	}

}
