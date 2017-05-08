package assignment1;


public class Sale {	
	private String cust;
	private String prod;
	private String day;
	private String month;
	private String year;
	private String quant;
	private String state;

	public Sale(String cust, String prod, String day, String month, String year, String quant, String state) {
		super();
		this.cust = cust;
		this.prod = prod;
		this.day = day;
		this.month = month;
		this.year = year;
		this.quant = quant;
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getQuant() {
		return quant;
	}

	public void setQuant(String quant) {
		this.quant = quant;
	}

}
