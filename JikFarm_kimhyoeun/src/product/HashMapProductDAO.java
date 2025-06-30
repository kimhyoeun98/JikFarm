package product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapProductDAO implements ProductDAO {

	protected Map<Integer, ProductVO> productDB = new HashMap<>();
	protected int productSeq = 111;
	
	@Override
	public boolean insertProduct(ProductVO product) {
		product.setProductNo(productSeq++);
		product.setRegdate(new Date());
		productDB.put(product.getProductNo(), product);
		return true;
	}

	@Override
	public ProductVO selectProduct(int productNo) {
		return productDB.get(productNo);
	}

	@Override
	public List<ProductVO> selectAllProducts() {
		return new ArrayList<>(productDB.values());
	}

	@Override
	public boolean updateProduct(ProductVO newProduct) {
		productDB.put(newProduct.getProductNo(), newProduct);
		return true;
	}

	@Override
	public boolean deleteProduct(int productNo) {
		return productDB.remove(productNo) != null;
	}
}
