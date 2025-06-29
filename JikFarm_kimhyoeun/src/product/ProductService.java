package product;

import java.util.List;

public interface ProductService {
	boolean registProduct(ProductVO product); //상품 등록
	List<ProductVO> listProducts(); // 상품 리스트
	ProductVO detailProductInfo(int productNo); //상품 정보 조회
	boolean updateProductPrice(int productNo, int price);  //상품 가격 수정
	boolean updateProductInstock(int productNo, int instock); //상품 재고 수정
	boolean removeProduct(int productNo); //상품 삭제

}
