package JDBC05;

public class RentDetailDto {
	
	private String rentdate;
	private int numseq;
	private int booknum;
	private String subject;
	private int membernum;
	private String name;
	private int rentprice;
	private int discount;
	private int amount;

	
	public String getRentdate() {
		return rentdate;
	}
	public void setRentdate(String rentdate) {
		this.rentdate = rentdate;
	}
	public int getNumseq() {
		return numseq;
	}
	public void setNumseq(int numseq) {
		this.numseq = numseq;
	}
	public int getBooknum() {
		return booknum;
	}
	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getMembernum() {
		return membernum;
	}
	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getRentprice() {
		return rentprice;
	}
	public void setRentprice(int rentprice) {
		this.rentprice = rentprice;
	}

	

}
