import java.io.Serializable; 

public  class  LoginMessage  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	

	/**
	 * Sent by a client who wants to connect to the server
	 */
	private final String username;

	
	private final String password;

	

	public LoginMessage(String username, String password) {
		this.username = username;
		this.password = password;
	}

	

	public String getUsername() {
		return username;
	}

	

	public String getPassword() {
		return password;
	}


}
