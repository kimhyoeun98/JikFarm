package product;

import java.util.List;

public interface ProductDAO {
	boolean insertProduct(ProductVO product); //상품 입력
	ProductVO selectProduct(int productNo); // 상품 선택
	List<ProductVO> selectAllProducts();  // 상품 전체 선택
	boolean updateProduct(ProductVO newProduct);  //새상품 업데이트
	boolean deleteProduct(int productNo);	 // 상품 삭제

}
