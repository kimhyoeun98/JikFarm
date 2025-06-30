package cart;

import java.util.List;

public class CartServiceImpl implements CartService {

	private CartDAO cartDAO;
	
	
	public CartServiceImpl(CartDAO cartDAO) {
	
		this.cartDAO = cartDAO;
	}
	
	
	@Override
	public boolean addItemToCart(CartItemVO item) {
		
		return cartDAO.insertCartItem(item);
	}

	@Override
	public CartItemVO getCartItemInfo(int productNo) {
		
		return cartDAO.selectCartItem(productNo);
	}

	@Override
	public List<CartItemVO> listCartItems() {
		
		return cartDAO.selectAllCartItem();
	}

	@Override
	public boolean isCartEmpty() {
		
		return cartDAO.selectAllCartItem().isEmpty();
	}

	@Override
	public boolean removeCartItem(int productNo) {
		
		return cartDAO.deleteCartItem(productNo);
	}

	@Override
	public boolean clearCart() {
		
		return cartDAO.clear();
	}

}