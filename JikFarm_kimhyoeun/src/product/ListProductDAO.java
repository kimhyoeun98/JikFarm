package product;

import java.util.LinkedList;
import java.util.List;

public class ListProductDAO implements ProductDAO {

	private List<ProductVO> productList = new LinkedList<ProductVO>();
	private int productSeq = 111; // 책번호 1씩 증가
	
	@Override
	public boolean insertProduct(ProductVO product) {
		product.setProductNo(productSeq++);
		productList.add(product);
		return true;
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		for (ProductVO product : productList) {
			if (product.getProductNo() == productNo)
				return product;
		}
		return null;
	}

	@Override
	public List<ProductVO> selectAllProducts() {
		return productList;
	}

	@Override
	public boolean updateProduct(ProductVO newProduct) {
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getProductNo() == newProduct.getProductNo()) {
				productList.set(i, newProduct);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteProduct(int productNo) {

		for (ProductVO product : productList) {
			if (product.getProductNo() == productNo) {
				productList.remove(product);
				return true;
			}
		}
		return false;
	}

}
