package cart;

import java.util.List;

public interface CartDAO {
	boolean insertCartItem(CartItemVO item);
	CartItemVO selectCartItem(int productNo);
	List<CartItemVO> selectAllCartItem();
	boolean deleteCartItem(int productNo);
	boolean clear();
}
