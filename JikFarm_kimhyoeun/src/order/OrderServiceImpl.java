package order;

import java.util.Date;
import java.util.List;

import product.ProductService;
import product.ProductVO;

public class OrderServiceImpl implements OrderService {
	
	private OrderDAO orderDAO;
	private ProductService productService;
	
	private final int COMPLETE = 10;
	
	public OrderServiceImpl(OrderDAO orderDAO, ProductService productService) {
		this.orderDAO = orderDAO;
		this.productService = productService;
	}
	
	@Override
	public boolean orderItems(OrderVO order) {
	    // --- 1단계: 모든 상품의 재고를 먼저 확인 ---
	    for (OrderItemVO item : order.getOrderItemList()) {
	        ProductVO product = productService.detailProductInfo(item.getProductNo());
	        if (product.getInstock() < item.getQuantity()) {
	            System.out.println("재고 부족: " + product.getProductName());
	            return false; // 재고 부족 시 즉시 주문 실패 처리
	        }
	    }

	    // --- 2단계: 모든 재고가 확인되면, 그때 재고를 차감 ---
	    for (OrderItemVO item : order.getOrderItemList()) {
	        int productNo = item.getProductNo();
	        int currentInstock = productService.detailProductInfo(productNo).getInstock();
	        int newInstock = currentInstock - item.getQuantity();
	        productService.updateProductInstock(productNo, newInstock);
	    }

	    // 주문 정보 설정 및 DB 추가
	    order.setOrderDate(new Date());
	    order.setStatus(COMPLETE);
	    order.setDeliverDate(new Date());
	    orderDAO.insertOrder(order);
	    return true;
	}

	@Override
	public List<OrderVO> listMyOrders(String userId) {
		return orderDAO.selectOrdersOfUser(userId);
	}

	@Override
	public List<OrderVO> listAllOrder() {
		return orderDAO.selectAllOrder();
	}

}
