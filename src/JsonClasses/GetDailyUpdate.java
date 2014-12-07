package JsonClasses;

public class GetDailyUpdate implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private String overallID = "getDailyUpdate";
	private String quote;
    private String date;
    private String celsius;
	private String desc;
	
	
	
	public GetDailyUpdate(){
		
	}    
    
    public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCelsius() {
		return celsius;
	}
	public void setCelsius(String celsius) {
		this.celsius = celsius;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public long getSerialVersionUID() {
		return serialVersionUID;
	}

	
}
