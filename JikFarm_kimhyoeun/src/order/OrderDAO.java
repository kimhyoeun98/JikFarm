package order;

import java.util.List;

public interface OrderDAO {
	boolean insertOrder(OrderVO order);
	OrderVO selectProduct(int orderNo);
	List<OrderVO> selectOrdersOfUser(String userId);
	List<OrderVO> selectAllOrder();
}
