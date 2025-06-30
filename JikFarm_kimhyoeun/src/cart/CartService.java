package cart;

import java.util.List;

public interface CartService {

	boolean addItemToCart(CartItemVO item);
	CartItemVO getCartItemInfo(int prductNo);
	List<CartItemVO> listCartItems();
	boolean isCartEmpty();
	boolean removeCartItem(int productNo);
	boolean clearCart();
}

