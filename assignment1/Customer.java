package assignment1;

public class Customer {
	private String custname;
	private Sale minquant;
	private Sale maxquant;
	private Sale minNY;
	private Sale maxNJ;
	private Sale minCT;
	private int sumquant;
	private int count;
	
	
	public Customer(String custname, Sale minquant, Sale maxquant) {
		this.custname = custname;
		this.minquant = minquant;
		this.maxquant = maxquant;
	}
	public Customer(){
		
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public Sale getMinquant() {
		return minquant;
	}
	public void setMinquant(Sale minquant) {
		this.minquant = minquant;
	}
	public Sale getMaxquant() {
		return maxquant;
	}
	public void setMaxquant(Sale maxquant) {
		this.maxquant = maxquant;
	}
	public Sale getMinNY() {
		return minNY;
	}
	public void setMinNY(Sale minNY) {
		this.minNY = minNY;
	}
	public Sale getMaxNJ() {
		return maxNJ;
	}
	public void setMaxNJ(Sale maxNJ) {
		this.maxNJ = maxNJ;
	}
	public Sale getMinCT() {
		return minCT;
	}
	public void setMinCT(Sale minCT) {
		this.minCT = minCT;
	}

	public int getSumquant() {
		return sumquant;
	}

	public void setSumquant(int sumquant) {
		this.sumquant = sumquant;
	}
	
	public int getAvg(){
		return sumquant/count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
