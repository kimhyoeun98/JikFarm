package order;

import java.io.Serializable;

public class OrderItemVO implements Serializable {
	private int productNo;
	private String productName;
	private int quantity;
	private int price;
	
	
	public OrderItemVO(int productNo, String productName, int quantity, int price) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}


	public int getProductNo() {
		return productNo;
	}


	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "\t***" + productNo + ", " + productName + ", " + quantity + "(개), " + price + "(원)";
	}
	
	
	

}
