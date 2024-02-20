package Bean;

public class Login_Bean {
	
	private String fname;
	private String lname;
	private String email;
	private String password;
	
	private String filename;
	private String path;
	private String size;
	private String filetype;
	
	private String key;
	private int rab_chks;
	private int rab_dup;
	private int ram_chks;
	private int ram_dup;
	
	
	public String getfilename() {
		return filename;
	}
	public void setfilename(String file_name) {
		this.filename = file_name;
	}
	
	public String getfilepath() {
		return path;
	}
	public void setfilepath(String file_path) {
		this.path = file_path;
	}
	
	public String getfilesize() {
		return size;
	}
	public void setfilesize(String file_size) {
		this.size = file_size;
	}
	

	public String getfiletype() {
		return filetype;
	}
	public void setfiletype(String file_type) {
		this.filetype = file_type;
	}
	
	
	public int getrabchks() {
		return rab_chks;
	}
	public void setrab_chks(int rabchks) {
		this.rab_chks= rabchks;
	}
	
	public int getramchks() {
		return ram_chks;
	}
	public void setram_chks(int ramchks) {
		this.ram_chks= ramchks;
	}
	
	public int getrabdup() {
		return rab_dup;
	}
	public void setrab_dup(int rabdup) {
		this.rab_dup= rabdup;
	}
	
	public int getramdup() {
		return ram_dup;
	}
	public void setra_dup(int ramdup) {
		this.ram_dup= ramdup;
	}
	
	public String getfname() {
		return fname;
	}
	public void setfname(String ft_name) {
		this.fname = ft_name;
	}

	public String getlname() {
		return lname;
	}
	public void setlname(String lt_name) {
		this.lname = lt_name;
	}
	
	public String getemail() {
		return email;
	}
	public void setemail(String Email) {
		this.email = Email;
	}
	
	public String getpassword() {
		return password;
	}
	public void setpassword(String pwd) {
		this.password = pwd;
	}

	public String getkeyvalue() {
		return key;
	}
	public void setkey(String pwd_key) {
		this.key = pwd_key;
	}
	
}
