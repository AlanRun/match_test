package helper;

public class UserInfo {
	
	public String name;
	public String pw;
	public String userId;
	public String token;
	public String level;
	
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 
	 * @param name
	 * @param userId
	 * @param token
	 */
	public UserInfo(String name, String userId, String token) {
		super();
		this.name = name;
		this.userId = userId;
		this.token = token;
	}
	
	/**
	 * 
	 * @param name
	 * @param pw
	 * @param userId
	 * @param token
	 */
	public UserInfo(String name, String pw, String userId, String token) {
		super();
		this.name = name;
		this.pw = pw;
		this.userId = userId;
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", userId=" + userId + ", token=" + token + "]";
	}
	
}
