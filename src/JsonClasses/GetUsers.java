package JsonClasses;

import java.util.ArrayList;

public class GetUsers implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private String overallID = "getUsers";
	ArrayList<UserInfo>  getUsers;

	public GetUsers(){
		getUsers = new ArrayList<UserInfo>();
	}
	
	public ArrayList<UserInfo> getUserArray() {
		return getUsers;
	}

	public void setUserArray(ArrayList<UserInfo> getUsers) {
		this.getUsers = getUsers;
	}
	
	
	
}
