package JsonClasses;

import java.util.ArrayList;

public class GetUsers implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private String overallID = "getUsers";
	
	
	ArrayList<UserInfo>  getUsers =	new ArrayList<UserInfo>();
	
	
	public String getOverallID() {
		return overallID;
	}

	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}

	public ArrayList<UserInfo> getUserArray() {
		return getUsers;
	}

	public void setUserArray(ArrayList<UserInfo> getUsers) {
		this.getUsers = getUsers;
	}
	
	
	
}
