package assignment2;


public class Sale {	
	private String cust;
	private String prod;
	private int day;
	private int month;
	private int year;
	private int quant;
	
	
	
	public Sale(String cust, String prod, int day, int month, int year, int quant, String state) {
		super();
		this.cust = cust;
		this.prod = prod;
		this.day = day;
		this.month = month;
		this.year = year;
		this.quant = quant;
		this.state = state;
	}
	
	
	
	public String getCust() {
		return cust;
	}
	public void setCust(String cust) {
		this.cust = cust;
	}
	public String getProd() {
		return prod;
	}
	public void setProd(String prod) {
		this.prod = prod;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getQuant() {
		return quant;
	}
	public void setQuant(int quant) {
		this.quant = quant;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private String state;

}
