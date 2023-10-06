//유저정보 클래스
public class User {  
	private String nickName;  //닉네임
	public String password;  //비밀번호
	
	//생성자
	public User(String nickName, String password) {
		this.nickName = nickName;
		this.password = password;
	}
	public User() {
		
	}
	//getter setter
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
