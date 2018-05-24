package afrique.cm.dla.zambou.snack.bean;

public class LoginDataMrc {

	private String name;
	private String password;
	private String username;
	private String matricule;
	private String type;
	private String Id;

	public LoginDataMrc() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMatricule() {
		return  matricule;
	}
	
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getId() {
		return Id;
	}
	
	public void setId(String Id) {
		this.Id = Id;
	}
}
