package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cart.CartItemVO;
import cart.CartService;
import cart.CartServiceImpl;
import cart.HashMapCartDAO;
import order.OrderItemVO;
import order.OrderService;
import order.OrderServiceImpl;
import order.OrderVO;
import order.file.ObjFileHashMapOrderDAO;
import product.JFProductService;
import product.ProductService;
import product.ProductVO;
import product.file.ObjFileHashMapProductDAO;
import user.JFUserService;
import user.UserService;
import user.UserVO;
import user.file.ObjFileHashMapUserDAO;

public class JikFarmConsoleApp {
	
	String[] startMenuList = {"종료", "상품 목록", "로그인", "회원 가입"};
	String[] adminMenuList = {"로그아웃", "상품 목록", "상품 등록", "상품 정보 수정", "상품 삭제", "회원 목록", "주문 목록"};
	String[] memberMenuList = {"로그아웃", "상품 목록", "상품 주문", "주문 목록", "장바구니 상품 담기", "장바구니 보기", "내 정보"};
	String[] cartMenuList = {"돌아가기", "상품 주문", "상품 삭제", "장바구니 비우기"};
	String[] myInfoMenuList = {"돌아가기", "비밀번호 변경", "회원 탈퇴"};
	
	final String ADMIN_ID = "admin";
	final String ADMIN_PWD = "1234";
	final String ADMIN_NAME = "관리자";
	
	final String CONFIRM = "yes";
	
	ProductService ps = new JFProductService(new ObjFileHashMapProductDAO());
	UserService us = new JFUserService(new ObjFileHashMapUserDAO());
	OrderService os = new OrderServiceImpl(new ObjFileHashMapOrderDAO(), ps);
	CartService cs = new CartServiceImpl(new HashMapCartDAO());
	UserVO loggedUser;
	
	MyAppReader input = new MyAppReader();
	
	public static void main(String[] args) {
		JikFarmConsoleApp app = new JikFarmConsoleApp();
		app.displayWelcome();
		app.controlStartMenu();
	}

	private void displayWelcome() {
		System.out.println("********************************");
		System.out.println("*           Jik Farm           *");
		System.out.println("********************************");
	}

	private void controlStartMenu() {
		int menu;
		do {
			menu = selectMenu(startMenuList);
			
			switch (menu) {
			case 1: menuProductList(); break;
			case 2: menuLogin(); break;
			case 3: menuSignUp(); break;
			case 0: menuExit(); break;
			default : menuWrongNumber();
			}
			
		} while (menu != 0);
		
	}

	private void menuWrongNumber() {
		System.out.println("없는 메뉴입니다.");
		
	}

	private void menuExit() {
		System.out.println("JikFarm을 이용해주셔서 감사합니다.");
		
	}

	// 1. displayProductList를 어떤 리스트든 출력할 수 있도록 수정합니다.
	//  (기존에는 스스로 ps.listProducts()를 호출했지만, 이제는 매개변수로 리스트를 받습니다.)
	private void displayProductList(List<ProductVO> productList) {
		System.out.println("---------------------------------------");
		if (productList == null || productList.isEmpty()) { // null 체크 추가
			System.out.println("등록된 상품이 없습니다.");
		} else {
			for (ProductVO product : productList) {
          System.out.println(product);
			}
		}
		System.out.println("---------------------------------------");	
	}


	//2. menuProductList를 검색/정렬 기능의 컨트롤 타워로 만듭니다.
	private void menuProductList() {
		System.out.println("*** 상품 목록 ***");
  
		// 먼저 전체 상품 목록을 DB에서 가져옵니다.
		final List<ProductVO> originalList = ps.listProducts();
		
		List<ProductVO> displayList = new ArrayList<>(originalList);
  
	    while(true) {
	        // '보여주기용' 리스트를 화면에 출력합니다.
	        displayProductList(displayList);
	      
	        System.out.println("1. 상품 이름으로 검색 | 2. 정렬 | 9. 전체 목록으로 초기화 | 0. 돌아가기");
	        int choice = input.readInt(">> 선택: ");
	      
	        switch (choice) {
	            case 1:
	                // ★★★ 핵심 변경점 ★★★
	                // 검색은 항상 '원본' 리스트(originalList)에서 수행하고,
	                // 그 결과를 '보여주기용' 리스트(displayList)에 덮어씁니다.
	                displayList = searchProducts(originalList);
	                break;
	            case 2:
	                // 정렬은 현재 '보여주고 있는' 리스트를 대상으로 수행합니다.
	                // (검색 결과만 따로 정렬하고 싶을 수도 있으므로)
	                sortProducts(displayList);
	                break;
	            case 9:
	                // '보여주기용' 리스트를 다시 원본 전체 목록으로 되돌립니다.
	                displayList = new ArrayList<>(originalList);
	                System.out.println("전체 상품 목록으로 초기화합니다.");
	                break;
	            case 0:
	                return; // 메소드 종료, 이전 메뉴로 돌아감
	            default:
	                menuWrongNumber();
	                break;
	        }
	    }
	}
	private void menuLogin() {
		System.out.println("*** 로그인 ***");
		String id = input.readString(">> id : ");
		String password = input.readString(">> password : ");
		
		// 관리자 -> 관리자 메뉴
		if (id.equals(ADMIN_ID) && password.equals(ADMIN_PWD)) {
			loggedUser = new UserVO(ADMIN_ID, ADMIN_PWD, ADMIN_NAME);
			System.out.println("관리자 모드로 변경합니다.");
			controlAdminMenu();
		} else {
			// 회원 -> 회원 메뉴
			loggedUser = us.login(id, password);
			
			if (loggedUser != null) {
				System.out.println("[로그인] " + loggedUser.getUserName() + "님 안녕하세요.");
				controlUserMenu();
			} else {
				// 아니면
				System.out.println("로그인을 하지 못했습니다.");
			}
		}
		
	}

	private void controlUserMenu() {
		int menu;
		do {
	        if (loggedUser == null) {
	            break;
	        }
			menu = selectMenu(memberMenuList);
			// "로그아웃", "상품 목록", "상품 주문", "주문 목록", "장바구니 상품 담기", "장바구니 보기", "내 정보"
			switch (menu) {
			case 1 : menuProductList(); break;
			case 2 : menuProductOrder(); break;
			case 3 : menuOrderList(); break;
			case 4 : menuAddProductToCart(); break;
			case 5 : menuCartView(); break;
			case 6 : menuMyInfo(); break;
			case 0 : menuLogout(); break;
			default : menuWrongNumber();
			}
		} while (menu != 0);

	}

	private void menuProductOrder() {
		System.out.println("*** 상품 주문 ***");
		displayAvailableProductList();
		int productNo = input.readInt(">> 상품 번호 : ");
		ProductVO product = ps.detailProductInfo(productNo);
		
		if (product == null) {
			System.out.println("없는 상품 입니다.");
			return;
		}
		
		int quantity = input.readInt(">> 주문량 (" + product.getInstock() + "개 이내) : ");
		if (quantity > product.getInstock()) {
			System.out.println("주문량이 재고량보다 큽니다.");
			return;
		}
		
		// 주문 상품 목록
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int price = product.getPrice() * quantity;
		orderItemList.add(new OrderItemVO(productNo, product.getProductName(), quantity, price));
		
		// 주문 정보 생성
		OrderVO order = new OrderVO(loggedUser.getId(), orderItemList, price);
		// 배송 정보 추가
		setDeliveryInfo();
		order.setMobile(loggedUser.getMobile());
		order.setAddress(loggedUser.getAddress());
		
		if (os.orderItems(order)) {
			System.out.println("주문이 완료되었습니다.");
			System.out.println("배송이 완료되었습니다.");
		} else {
			System.out.println("주문을 하지 못했습니다.");
		}
	}
	
	private void setDeliveryInfo() {
		if (loggedUser.getMobile() == null) {
			System.out.println("*** 배송 정보 입력 ***");
			
			String mobile = input.readString(">> 모바일 번호 : ");
			String email = input.readString(">> 이메일 주소 : ");
			String address = input.readString(">> 주소 : ");
			
			loggedUser.setMobile(mobile);
			loggedUser.setEmail(email);
			loggedUser.setAddress(address);
			
			us.addUserInfo(loggedUser.getId(), mobile, email, address);
			
		}
	}

	private void displayAvailableProductList() {
		List<ProductVO> productList = ps.listProducts();
		System.out.println("---------------------------------------");
		if (productList.isEmpty()) {
			System.out.println("주문 가능한 상품이 없습니다.");
		} else {
			int count = 0;
			for (ProductVO product : productList) {
				if (product.getInstock() > 0) {
					System.out.println(product);
					count++;
				}
			}
			if (count == 0) 
				System.out.println("주문 가능한 상품이 없습니다.");
		}
		System.out.println("---------------------------------------");	
		
	}

	private void menuAddProductToCart() {
		System.out.println("*** 장바구니에 상품 담기 ***");
		
		displayAvailableProductList();
		int productNo = input.readInt(">> 상품 번호 : ");
		ProductVO product = ps.detailProductInfo(productNo);
		
		if (product == null) {
			System.out.println("없는 상품 입니다.");
			return;
		}
		
		int quantity = input.readInt(">> 주문량 (" + product.getInstock() + "개 이내) : ");
		if (quantity > product.getInstock()) {
			System.out.println("주문량이 재고량보다 큽니다.");
			return;
		}
		// 이미 장바구니에 있는지 확인
		// 없으면 장바구니에 넣기
		if (cs.getCartItemInfo(productNo) == null) {
			cs.addItemToCart(new CartItemVO(productNo, product.getProductName(), quantity));
			System.out.println("장바구니에 추가했습니다.");
		} else {
			System.out.println("이미 장바구니에 있는 상품입니다.");
		}
	}

	private void menuCartView() {
		System.out.println("*** 장바구니 보기 ***");
		displayCartItemList();
		
		if (!cs.isCartEmpty()) controlCartMenu();
	}
	private void displayCartItemList() {
		if (cs.isCartEmpty()) {
			System.out.println("장바구니가 비어 있습니다.");
		} else {
			System.out.println("-----------------------------");
			for (CartItemVO item : cs.listCartItems()) {
				System.out.println(item);
			}
			System.out.println("----------------------------");	
		}
	}
	private void controlCartMenu() {
		int menu;
		do {
			menu = selectMenu(cartMenuList);
			switch(menu) {
			case 1 : menuCartOrder(); break;
			case 2 : menuCartProductDelete(); break;
			case 3 : menuCartClear(); break;
			case 0 : break;
			default : menuWrongNumber();
			}
		} while (menu != 0 && !cs.isCartEmpty());
	}

	private void menuCartOrder() {
		System.out.println("*** 장바구니 상품 주문 ***");
		displayCartItemList();
		
		// 주문 상품 목록
		List<OrderItemVO> orderItemList = new ArrayList<>();
		int totalPrice = 0;
		for (CartItemVO item : cs.listCartItems()) {
			ProductVO product = ps.detailProductInfo(item.getProductNo());
			int price = product.getPrice() * item.getQuantity();
			totalPrice += price;
			orderItemList.add(new OrderItemVO(item.getProductNo(), item.getProductName(), item.getQuantity()
							  , product.getPrice() * item.getQuantity()));
		}
		
		// 주문 정보 생성
		OrderVO order = new OrderVO(loggedUser.getId(), orderItemList, totalPrice);
		
		// 배송 정보 추가
		setDeliveryInfo();
		order.setMobile(loggedUser.getMobile());
		order.setAddress(loggedUser.getAddress());
		
		displayOrderInfo(order);
		
		String confirm = input.readString(">> 위와 같은 내용을 주문 및 결제를 진행하겠습니까? ('" + CONFIRM + "'이면 주문실행) : ");
		
		if(confirm.equals(CONFIRM)) {		
			if (os.orderItems(order)) {
				cs.clearCart();
				System.out.println("주문이 완료되었습니다.");
				System.out.println("배송이 완료되었습니다.");
			} else {
				System.out.println("주문을 하지 못했습니다.");
			}
		}
	}

	private void displayOrderInfo(OrderVO order) {
		System.out.println(order);
	}

	private void menuCartProductDelete() {
		System.out.println("*** 장바구니 상품 삭제 *** ");
		displayCartItemList();
		int productNo = input.readInt(">> 상품 번호 : ");
		CartItemVO item = cs.getCartItemInfo(productNo);
		if (item == null) {
			System.out.println("없는 상품입니다.");
		} else {
			cs.removeCartItem(productNo);
			System.out.println("장바구니에서 상품 삭제하였습니다.");
		}
		displayCartItemList();
	}

	private void menuCartClear() {
		System.out.println("*** 장바구니 ***");
		String confirm = input.readString(">> 장바구니의 모든 상품을 삭제하시겠습니까? ('" + CONFIRM + "'이면 삭제) : ");
		if (confirm.equals(CONFIRM)) {
			cs.clearCart();
			System.out.println("장바구니의 모든 상품을 삭제하였습니다.");
		} else {
			System.out.println("장바구니 비우기가 취소되었습니다.");
		}
		
	}

	private void menuMyInfo() {
		System.out.println("*** 내 정보 ***");
		System.out.println(loggedUser);
		
		controlMyInfoMenu();
	}

	private void controlMyInfoMenu() {
		int menu;
		do {
			menu = selectMenu(myInfoMenuList);
			// "돌아가기", "비밀번호 변경", "회원 탈퇴"
			switch (menu) {
			case 1 : menuUpatePassword(); break;
			case 2 : menuMemberExit(); break;
			case 0 : break;
			default : menuWrongNumber();
			}
		} while (menu != 0 && loggedUser != null);
		
	}

	private void menuUpatePassword() {
		System.out.println("*** 비밀번호 수정 ***");
		String oldPassword = input.readString(">> 기존 비밀번호 : ");
		String newPassword = input.readString(">> 새 비밀번호 : ");
		if (us.updatePassword(loggedUser.getId(), oldPassword, newPassword)) {
			System.out.println("[비밀번호 수정] 비밀번호를 수정했습니다.");
		} else {
			System.out.println("[비밀번호 수정 실패] 비밀번호 수정에 실패했습니다.");
		}
	}

	private void menuMemberExit() {
		System.out.println("*** 회원 탈퇴 ***");
		String password = input.readString(">> 비밀번호 : ");
		if (us.removeUser(loggedUser.getId(), password)) {
			System.out.println("[회원 탈퇴] 회원정보, 주문정보를 삭제하였습니다. 그동안 서비스를 이용해 주셔서 감사합니다.");
			loggedUser = null;
		} else {
			System.out.println("[회원 탈퇴 실패] 회원 탈퇴 처리에 실패했습니다.");
		}
		
	}

	private void controlAdminMenu() {
		int menu;
		do {
			menu = selectMenu(adminMenuList);
			// "로그아웃", "상품 목록", "상품 등록", "상품 정보 수정", "상품 삭제", "회원 목록", "주문 목록"
			switch (menu) {
			case 1: menuProductList(); break;
			case 2: menuProductRegist(); break;
			case 3: menuProductUpdate(); break;
			case 4: menuProductRemove(); break;
			case 5: menuUserList(); break;
			case 6: menuOrderList(); break;
			case 0: menuLogout(); break;
			default : menuWrongNumber();
			}
			
		} while (menu != 0 && loggedUser != null);
		
	}

	private void menuProductRegist() {
		
		System.out.println("*** 상품 등록 ***");
		String productName = input.readString(">> 상품 이름 : ");
		String producer = input.readString(">> 판매자 : ");
		String origin = input.readString(">> 원산지 : ");
		int price = input.readInt(">> 가격 : ");
		int instock = input.readInt(">> 재고량 : ");
		
		if (ps.registProduct(new ProductVO(productName, producer, origin, price, instock))) {
			System.out.println("상품을 등록했습니다.");
			displayProductList(ps.listProducts());
		} else {
			System.out.println("상품 등록에 실패했습니다.");
		}
		
	}

	private void menuProductUpdate() {
		System.out.println("*** 상품 정보 수정 ***");
		displayProductList(ps.listProducts());
		int productNo = input.readInt(">> 상품 번호 :");
		
		int select = input.readInt(">> 수정할 정보 선택 (1. 가격, 2. 재고량) : ");
		if (select == 1) { // 가격
			int price = input.readInt(">> 새 가격 : ");
			if (ps.updateProductPrice(productNo, price)) {
				System.out.println("[상품 정보 수정] 가격을 수정하였습니다.");
			} else {
				System.out.println("[상품 정보 수정 오류] 없는 상품입니다.");
			}
			
		} else if (select == 2) {// 재고량
			int instock = input.readInt(">> 새 재고량 :");
			if (ps.updateProductInstock(productNo, instock)) {
				System.out.println("[상품 정보 수정] 재고량을 수정하였습니다.");
			} else {
				System.out.println("[상품 정보 수정 오류] 없는 상품입니다.");
			}
		} else {
			System.out.println("[상품 정보 수정 취소] 지원하지 않는 기능입니다.");
		}
		
	}

	private void menuProductRemove() {
		System.out.println("*** 상품 삭제 ***");
		displayProductList(ps.listProducts());
		int productNo = input.readInt(">> 상품 번호 :");
		String confirm = input.readString("선택한 상품을 삭제하시겠습니까? ('" + CONFIRM + "'를 입력하면 실행) : ");
		if (confirm.equals(CONFIRM)) {
			if (ps.removeProduct(productNo)) {
				System.out.println("[상품 삭제] 상품을 삭제했습니다.");
			} else {
				System.out.println("[상품 삭제 오류] 없는 상품입니다.");
			}
		} else {
			System.out.println("[상품 삭제 취소] 상품 삭제를 취소했습니다.");
		}
	}

	private void menuUserList() {
		System.out.println("*** 회원 목록 ***");
		System.out.println("---------------------------------------");
		List<UserVO> userList = us.listUsers();
		if (userList.isEmpty()) {
			System.out.println("회원이 없습니다.");
		} else {
			for (UserVO user : userList) {
				System.out.println(user);
			}
		}
		System.out.println("---------------------------------------");
		
	}

	private void menuOrderList() {
		if (loggedUser.getId().equals(ADMIN_ID)) {
			System.out.println(os.listAllOrder());
		} else {
			System.out.println(os.listMyOrders(loggedUser.getId()));
		}
		
	}

	private void menuLogout() {
		
		System.out.println("[로그아웃] " + loggedUser.getUserName() + "님, 안녕히 가십시오.");
		loggedUser = null;
		
	}

	private void menuSignUp() {
		System.out.println("*** 회원 가입 ***");
		String id = input.readString(">> id : ");
		String password = input.readString(">> password : ");
		String userName = input.readString(">> username : ");
		
		if (us.registUser(new UserVO(id, password, userName))) {
			System.out.println("회원 가입이 완료되었습니다. 서비스 이용을 위한 로그인 해주세요.");
		} else {
			System.out.println("회원 가입에 실패하였습니다.");
		}
		
	}

	private int selectMenu(String[] menuList) {

		System.out.println("-------------------------------");
		for (int i = 1; i < menuList.length; i++) {
			System.out.println(i + ". " + menuList[i]);
		}
		System.out.println("0. " + menuList[0]);
		System.out.println("-------------------------------");
		return input.readInt(">> 메뉴 선택 : ");
	}
	
	private List<ProductVO> searchProducts(List<ProductVO> originalList) {
	    String keyword = input.readString(">> 검색할 상품 이름을 입력하세요: ");
	    
	    List<ProductVO> filteredList = new ArrayList<>();
	    
	    for (ProductVO product : originalList) {
	        // 상품 이름에 '키워드 전체'가 하나의 덩어리로 포함되어 있는지 확인합니다.
	        // 이 로직이 사용자께서 원하시는 동작을 정확히 수행합니다.
	        if (product.getProductName().contains(keyword)) {
	            filteredList.add(product);
	        }
	    }
	    
	    return filteredList;
	}
	/* stream API를 사용한 더 간결한 코드
	  private List<ProductVO> searchProducts(List<ProductVO> originalList) {
    String keyword = input.readString(">> 검색할 상품 이름을 입력하세요: ");

    return originalList.stream()
            .filter(p -> p.getProductName().contains(keyword))
            .collect(Collectors.toList());
}
	 */
	
	private void sortProducts(List<ProductVO> listToSort) {
	    System.out.println("--- 정렬 방식 선택 ---");
	    System.out.println("1. 가격 낮은 순 | 2. 가격 높은 순 | 3. 이름 순(가나다)");
	    int choice = input.readInt(">> 선택: ");
	    
	    switch (choice) {
	        case 1: // 가격 낮은 순 (오름차순)
	            Collections.sort(listToSort, Comparator.comparingInt(ProductVO::getPrice));
	            System.out.println("가격이 낮은 순으로 정렬했습니다.");
	            break;
	        case 2: // 가격 높은 순 (내림차순)
	            Collections.sort(listToSort, Comparator.comparingInt(ProductVO::getPrice).reversed());
	            System.out.println("가격이 높은 순으로 정렬했습니다.");
	            break;
	        case 3: // 이름 순 (오름차순)
	            Collections.sort(listToSort, Comparator.comparing(ProductVO::getProductName));
	            System.out.println("이름 순으로 정렬했습니다.");
	            break;
	        default:
	            System.out.println("잘못된 선택입니다. 정렬을 취소합니다.");
	            break;
	    }
	}
	
}
