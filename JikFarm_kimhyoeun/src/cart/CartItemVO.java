package cart;

public class CartItemVO {

	private int productNo;
	private String productName;
	private int quantity;

	
	public CartItemVO(int productNo, String productName, int quantity) {
		this.productNo = productNo;
		this.productName = productName;
		this.quantity = quantity;

	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "[" + productNo + "-" + productName + ": " + quantity + "개]";
	}
	
	
}