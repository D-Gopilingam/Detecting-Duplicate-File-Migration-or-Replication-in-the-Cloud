package Interfaces;

import java.util.List;

import Bean.Login_Bean;

public interface User {
	public int register(Login_Bean bean);
	
	public List userlogin(Login_Bean log);
	
	public int fileupload(Login_Bean bean);
	
	public int fileupdateRab(Login_Bean bean);
	
	public int fileupdateRam(Login_Bean bean);
	
	public String getkey(Login_Bean bean);
}
