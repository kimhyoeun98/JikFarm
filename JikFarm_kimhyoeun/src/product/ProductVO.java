package product;

import java.io.Serializable;
import java.util.Date;

public class ProductVO implements Serializable {
	private int productNo;
	private String productName;
	private String producer;
	private int price;
	private int instock;
	private String origin;
	private Date regdate;

	
	public ProductVO(int productNo,String productName, String producer, int price, int instock, String origin, Date regdata) {
		this.productNo = productNo;
		this.productName = productName;
		this.producer = producer;
		this.price = price;
		this.instock = instock;
		this.regdate = regdate;
		this.origin = origin;
	}
	
	public ProductVO(String productName, String producer, String origin, int price, int instock) {
		this.productName = productName;
		this.producer = producer;
		this.origin = origin;
		this.price = price;
		this.instock = instock;
	}
	
	public String toString() {
		return "[" + productNo + ", " + productName + ", " + producer +  ", " + price + ", " + instock + ", " + origin + "]";
	}
	

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInstock() {
		return instock;
	}

	public void setInstock(int instock) {
		this.instock = instock;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	

}
